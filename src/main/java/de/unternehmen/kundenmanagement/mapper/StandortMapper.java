package de.unternehmen.kundenmanagement.mapper;

import de.unternehmen.kundenmanagement.entity.Standort;
import de.unternehmen.kundenmanagement.generated.model.StandortModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StandortMapper {

    private final AnschriftMapper anschriftMapper;

    public StandortModel toDto(Standort entity) {
        return StandortModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .anschriftModel(anschriftMapper.toDto(entity.getAnschrift()))
                .geschaeftspartnerId(entity.getGeschaeftspartner().getId())
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
