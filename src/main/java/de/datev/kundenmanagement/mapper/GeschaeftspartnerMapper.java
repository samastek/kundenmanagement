package de.datev.kundenmanagement.mapper;

import de.datev.kundenmanagement.entity.Geschaeftspartner;
import de.datev.kundenmanagement.entity.GeschaeftspartnerMitarbeiter;
import de.datev.kundenmanagement.generated.model.GeschaeftspartnerModel;
import java.util.Collections;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GeschaeftspartnerMapper {

    private final MitarbeiterMapper mitarbeiterMapper;
    private final StandortMapper standortMapper;

    public GeschaeftspartnerModel toDto(Geschaeftspartner geschaeftspartner) {
        return GeschaeftspartnerModel.builder()
                .id(geschaeftspartner.getId())
                .mitarbeiter(geschaeftspartner.getGeschaeftspartnerMitarbeiter()
                        .stream()
                        .filter(GeschaeftspartnerMitarbeiter::getActive)
                        .map(GeschaeftspartnerMitarbeiter::getMitarbeiter)
                        .map(mitarbeiterMapper::toDto)
                        .toList())
                .standorte(Optional.ofNullable(geschaeftspartner.getStandorte())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(standortMapper::toDto)
                        .toList())
                .name(geschaeftspartner.getName())
                .build();
    }

    public Geschaeftspartner toEntity(GeschaeftspartnerModel model) {
        Geschaeftspartner.GeschaeftspartnerBuilder builder = Geschaeftspartner.builder();
        builder.id(model.getId());
        builder.name(model.getName());
        return builder.build();
    }
}
