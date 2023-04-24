package de.datev.kundenmanagement.service;

import de.datev.kundenmanagement.util.LogHelper;
import de.datev.kundenmanagement.entity.Anschrift;
import de.datev.kundenmanagement.entity.Geschaeftspartner;
import de.datev.kundenmanagement.entity.GeschaeftspartnerMitarbeiter;
import de.datev.kundenmanagement.entity.Mitarbeiter;
import de.datev.kundenmanagement.entity.Standort;
import de.datev.kundenmanagement.generated.model.MitarbeiterModel;
import de.datev.kundenmanagement.generated.model.ZielStandorte;
import de.datev.kundenmanagement.mapper.MitarbeiterMapper;
import de.datev.kundenmanagement.repository.AnschriftRepository;
import de.datev.kundenmanagement.repository.GeschaeftspartnerMitarbeiterRepository;
import de.datev.kundenmanagement.repository.GeschaeftspartnerRepository;
import de.datev.kundenmanagement.repository.MitarbeiterRepository;
import de.datev.kundenmanagement.repository.StandortRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class MitarbeiterService {

    private final AnschriftRepository anschriftRepository;
    private final GeschaeftspartnerRepository geschaeftspartnerRepository;
    private final MitarbeiterRepository mitarbeiterRepository;
    private final GeschaeftspartnerMitarbeiterRepository gmRepository;
    private final MitarbeiterMapper mapper;
    private final StandortRepository standortRepository;


    public ResponseEntity<MitarbeiterModel> updateAMitarbeiterAnhandDessenId(Long id, MitarbeiterModel mitarbeiterModel) {
        return null;
    }

    public void mitarbeiterIdDelete(Long id) {
        mitarbeiterRepository.deleteById(id);
    }

    public MitarbeiterModel getMitarbeiterById(Long id) {
        return mitarbeiterRepository
                .findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(LogHelper.entityNichtGefunden(Mitarbeiter.class, id)));
    }

    public List<MitarbeiterModel> getAllMitarbeiter(Long geschaeftspartnerId, List<Long> standortIds) {
        List<Mitarbeiter> mitarbeiter;

        if (geschaeftspartnerId != null && standortIds != null) {
            mitarbeiter = mitarbeiterRepository.findAllByGeschaeftspartnerIdAndStandortList(geschaeftspartnerId,
                    standortIds);
        } else if (geschaeftspartnerId != null) {
            mitarbeiter = mitarbeiterRepository.findAllMitarbeiterByAktivGeschaeftspartnerId(geschaeftspartnerId);
        } else if (standortIds != null) {
            mitarbeiter = mitarbeiterRepository.findAllByStandortList(standortIds);
        } else {
            mitarbeiter = mitarbeiterRepository.findAll();
        }

        return mitarbeiter.stream()
                .map(mapper::toDto)
                .toList();
    }

    public MitarbeiterModel addMitarbeiter(MitarbeiterModel mitarbeiterModel) {
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname(mitarbeiterModel.getVorname());
        mitarbeiter.setNachname(mitarbeiterModel.getNachname());
        mitarbeiter.setTelefonnummer(mitarbeiterModel.getTelefonnummer());
        mitarbeiter.setEmail(mitarbeiterModel.getEmail());
        mitarbeiter.setGeburtsdatum(mitarbeiterModel.getGeburtsdatum());

        Anschrift anschrift = new Anschrift();
        anschrift.setStrasse(mitarbeiterModel.getAnschriftModel().getStrasse());
        anschrift.setHausnummer(mitarbeiterModel.getAnschriftModel().getHausnummer());
        anschrift.setPlz(mitarbeiterModel.getAnschriftModel().getPlz());
        anschrift.setOrt(mitarbeiterModel.getAnschriftModel().getOrt());
        anschrift.setLand(mitarbeiterModel.getAnschriftModel().getLand());
        anschriftRepository.save(anschrift);
        mitarbeiter.setAnschrift(anschrift);

        if (mitarbeiterModel.getGeschaeftspartnerId() != null) {
            Geschaeftspartner geschaeftspartner = geschaeftspartnerRepository.findById(mitarbeiterModel.getId())
                    .orElseThrow(() -> new EntityNotFoundException(LogHelper.entityNichtGefunden(Geschaeftspartner.class,
                            mitarbeiterModel.getGeschaeftspartnerId())));
            GeschaeftspartnerMitarbeiter gm = new GeschaeftspartnerMitarbeiter();
            gm.setMitarbeiter(mitarbeiter);
            gm.setGeschaeftspartner(geschaeftspartner);
            gm.setActive(true);
            mitarbeiter.getGeschaeftspartnerMitarbeiter().add(gm);
        }

        if (mitarbeiterModel.getStandortIds() != null) {
            Set<Standort> standorte = new HashSet<>();
            for (Long standortId : mitarbeiterModel.getStandortIds()) {
                Standort standort = new Standort();
                standort.setId(standortId);
                standorte.add(standort);
            }
            mitarbeiter.setStandorte(standorte);
        }

        Mitarbeiter entity = mitarbeiterRepository.save(mitarbeiter);
        return mapper.toDto(entity);
    }

    @Transactional
    public MitarbeiterModel wechsleMitarbeiterStandorte(Long id, ZielStandorte zielStandorte) {

        GeschaeftspartnerMitarbeiter gm = gmRepository.findByMitarbeiterIdAndActive(id, true);
        // Prüfung, ob der GM diese Standorte hat
        List<Long> standortIds = gm.getGeschaeftspartner().getStandorte()
                .stream()
                .map(Standort::getId)
                .toList();

        boolean korrekteZuweisung = new HashSet<>(standortIds).containsAll(zielStandorte.getStandorteIds());

        if (!korrekteZuweisung) {
            throw new IllegalStateException("einer oder mehrere Standorte gehören nicht dem aktuellen aktiven Geschaeftspartner");
        }
        List<Standort> relevanteStandorte = standortRepository.findAllByIds(zielStandorte.getStandorteIds());

        Mitarbeiter mitarbeiter = gm.getMitarbeiter();
        if (BooleanUtils.isTrue(zielStandorte.getRemoveOld()) || CollectionUtils.isEmpty(mitarbeiter.getStandorte())) {
            mitarbeiter.setStandorte(new HashSet<>(relevanteStandorte));
            mitarbeiter.getStandorte().forEach(standort -> standort.getMitarbeiter().remove(mitarbeiter));
        } else {
            mitarbeiter.getStandorte().addAll(relevanteStandorte);
        }

        Mitarbeiter entity = mitarbeiterRepository.save(mitarbeiter);
        return mapper.toDto(entity);
    }

    public MitarbeiterModel wechsleGeschaeftspartner(Long id, Long geschaeftspartnerId) {
        GeschaeftspartnerMitarbeiter gm = gmRepository.findByMitarbeiterIdAndActive(id, true);
        gm.setActive(false);
        gmRepository.save(gm);

        Mitarbeiter mitarbeiter = gm.getMitarbeiter();
        mitarbeiter.setStandorte(new HashSet<>());

        Geschaeftspartner geschaeftspartner = geschaeftspartnerRepository.findById(geschaeftspartnerId)
                .orElseThrow(() -> new EntityNotFoundException(LogHelper.entityNichtGefunden(Geschaeftspartner.class, geschaeftspartnerId)));
        GeschaeftspartnerMitarbeiter geschaeftspartnerMitarbeiter = new GeschaeftspartnerMitarbeiter();
        geschaeftspartnerMitarbeiter.setGeschaeftspartner(geschaeftspartner);
        geschaeftspartnerMitarbeiter.setMitarbeiter(mitarbeiter);
        geschaeftspartnerMitarbeiter.setActive(true);
        mitarbeiter.getGeschaeftspartnerMitarbeiter().add(geschaeftspartnerMitarbeiter);
        Mitarbeiter save = mitarbeiterRepository.save(mitarbeiter);

        return mapper.toDto(save);
    }
}
