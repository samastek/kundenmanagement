package de.datev.kundenmanagement.mapper;

import de.datev.kundenmanagement.entity.GeschaeftspartnerMitarbeiter;
import de.datev.kundenmanagement.entity.Mitarbeiter;
import de.datev.kundenmanagement.entity.Standort;
import de.datev.kundenmanagement.generated.model.MitarbeiterModel;
import de.datev.kundenmanagement.generated.model.MitarbeiterModel.MitarbeiterModelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@RequiredArgsConstructor
public class MitarbeiterMapper {

    private final AnschriftMapper anschriftMapper;

    public MitarbeiterModel toDto(Mitarbeiter entity) {
        MitarbeiterModelBuilder mitarbeiterModelBuilder = MitarbeiterModel.builder()
                .id(entity.getId())
                .anschriftModel(anschriftMapper.toDto(entity.getAnschrift()))
                .email(entity.getEmail())
                .geburtsdatum(entity.getGeburtsdatum())
                .nachname(entity.getNachname())
                .vorname(entity.getVorname())
                .telefonnummer(entity.getTelefonnummer());
        if (!CollectionUtils.isEmpty(entity.getGeschaeftspartnerMitarbeiter())) {
            GeschaeftspartnerMitarbeiter aktiveGeschaeftspartner = entity.getGeschaeftspartnerMitarbeiter().stream()
                    .filter(GeschaeftspartnerMitarbeiter::getActive).findAny().orElseThrow(() -> new RuntimeException("something is wrong"));
            mitarbeiterModelBuilder.geschaeftspartnerId(aktiveGeschaeftspartner.getId());
        }
        if (entity.getStandorte() != null) {
            mitarbeiterModelBuilder.standortIds(entity.getStandorte().stream().map(Standort::getId).toList());
        }
        return mitarbeiterModelBuilder.build();
    }
}
