package de.unternehmen.kundenmanagement.mapper;

import de.unternehmen.kundenmanagement.entity.Anschrift;
import de.unternehmen.kundenmanagement.generated.model.AnschriftModel;
import org.springframework.stereotype.Component;

@Component
public class AnschriftMapper {

    public AnschriftModel toDto(Anschrift entity) {
        return AnschriftModel.builder()
                .id(entity.getId())
                .hausnummer(entity.getHausnummer())
                .land(entity.getLand())
                .ort(entity.getOrt())
                .plz(entity.getPlz())
                .strasse(entity.getStrasse())
                .zusatz(entity.getZusatz())
                .build();
    }

    public Anschrift toEntity(AnschriftModel model) {
        return Anschrift.builder()
                .hausnummer(model.getHausnummer())
                .land(model.getLand())
                .ort(model.getOrt())
                .plz(model.getPlz())
                .strasse(model.getStrasse())
                .zusatz(model.getZusatz())
                .build();
    }
}
