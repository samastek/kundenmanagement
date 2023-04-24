package de.datev.kundenmanagement.mapper;

import de.datev.kundenmanagement.entity.Standort;
import de.datev.kundenmanagement.generated.model.StandortModel;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StandortMapper {

    private final AnschriftMapper anschriftMapper;
    private final MitarbeiterMapper mitarbeiterMapper;

    public StandortModel toDto(Standort entity) {

        return StandortModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .anschriftModel(anschriftMapper.toDto(entity.getAnschrift()))
                .geschaeftspartnerId(entity.getGeschaeftspartner().getId())
                .mitarbeiter(Optional.ofNullable(entity.getMitarbeiter())
                        .orElse(Collections.emptySet())
                        .stream()
                        .map(mitarbeiterMapper::toDto)
                        .toList()
                )
                .build();
    }

    public Standort toEntity(StandortModel model) {
        if (model == null) {
            return null;
        }
        Standort standort = new Standort();
        standort.setAnschrift(anschriftMapper.toEntity(model.getAnschriftModel()));
        standort.setName(model.getName());

        return standort;
    }
}
