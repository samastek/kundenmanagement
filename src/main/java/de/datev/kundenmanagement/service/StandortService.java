package de.datev.kundenmanagement.service;

import de.datev.kundenmanagement.util.LogHelper;
import de.datev.kundenmanagement.entity.Geschaeftspartner;
import de.datev.kundenmanagement.entity.Standort;
import de.datev.kundenmanagement.generated.model.StandortModel;
import de.datev.kundenmanagement.mapper.AnschriftMapper;
import de.datev.kundenmanagement.mapper.StandortMapper;
import de.datev.kundenmanagement.repository.GeschaeftspartnerRepository;
import de.datev.kundenmanagement.repository.StandortRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StandortService {

    private final StandortRepository standortRepository;
    private final StandortMapper mapper;
    private final GeschaeftspartnerRepository geschaeftspartnerRepository;
    private final AnschriftMapper anschriftMapper;

    public void uebernehmeStandort(Long id, Long geschaeftspartnerId) {
        Standort standort = standortRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(LogHelper.entityNichtGefunden(
                Standort.class, id)));

        standort.setGeschaeftspartner(geschaeftspartnerRepository.findById(geschaeftspartnerId)
                .orElseThrow(() -> new EntityNotFoundException(LogHelper.entityNichtGefunden(
                        Geschaeftspartner.class, geschaeftspartnerId))));
        standortRepository.save(standort);
    }

    public void standorteIdDelete(Long id) {
        standortRepository.deleteById(id);
    }

    public StandortModel updateStandort(Long id, StandortModel standortModel) {
        Standort standort = standortRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(LogHelper.entityNichtGefunden(
                Standort.class, id)));
        standort.setName(standort.getName());
        standort.setAnschrift(anschriftMapper.toEntity(standortModel.getAnschriftModel()));
        Standort entity = standortRepository.save(standort);
        return mapper.toDto(entity);
    }

    public StandortModel addStandort(StandortModel standortModel) {
        Standort standort = mapper.toEntity(standortModel);
        Standort entity = standortRepository.save(standort);
        return mapper.toDto(entity);
    }

    public StandortModel getStandortById(Long id) {
        return mapper.toDto(standortRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(LogHelper.entityNichtGefunden(
                        Standort.class, id))));
    }

    public List<StandortModel> getStandorte(Long mitarbeiterId, Long geschaeftspartnerId) {
        List<Standort> standortList;
        if (mitarbeiterId != null && geschaeftspartnerId != null) {
            standortList = standortRepository.findAllByMitarbeiterIdAndGeschaeftspartnerId(mitarbeiterId, geschaeftspartnerId);
        } else if (mitarbeiterId != null) {
            standortList = standortRepository.findAllByMitarbeiterId(mitarbeiterId);
        } else if (geschaeftspartnerId != null) {
            standortList = standortRepository.findAllByGeschaeftspartnerId(geschaeftspartnerId);
        } else {
            standortList = standortRepository.findAll();
        }
        return standortList
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
