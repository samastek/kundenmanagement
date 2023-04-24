package de.datev.kundenmanagement.service;

import de.datev.kundenmanagement.util.LogHelper;
import de.datev.kundenmanagement.entity.Geschaeftspartner;
import de.datev.kundenmanagement.generated.model.GeschaeftspartnerModel;
import de.datev.kundenmanagement.mapper.GeschaeftspartnerMapper;
import de.datev.kundenmanagement.repository.GeschaeftspartnerRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeschaeftspartnerService {

    private final GeschaeftspartnerRepository geschaeftspartnerRepository;
    private final GeschaeftspartnerMapper mapper;

    public GeschaeftspartnerModel updateGeschaeftspartner(Long id, GeschaeftspartnerModel geschaeftspartnerModel) {
        return null;
    }

    public GeschaeftspartnerModel getGeschaeftspartnerById(Long id) {
        return mapper.toDto(geschaeftspartnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(LogHelper.entityNichtGefunden(Geschaeftspartner.class, id))));
    }

    public GeschaeftspartnerModel addGeschaeftspartner(GeschaeftspartnerModel geschaeftspartnerModel) {
        Geschaeftspartner geschaeftspartner = mapper.toEntity(geschaeftspartnerModel);
        Geschaeftspartner entity = geschaeftspartnerRepository.save(geschaeftspartner);
        return mapper.toDto(entity);
    }

    public ResponseEntity<String> deleteGeschaeftspartner(Long id) {
        return null;
    }

    public List<GeschaeftspartnerModel> getAllGeschaeftspartner() {
        return geschaeftspartnerRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
