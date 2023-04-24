package de.datev.kundenmanagement.controller;

import de.datev.kundenmanagement.generated.api.MitarbeiterApi;
import de.datev.kundenmanagement.generated.model.MitarbeiterModel;
import de.datev.kundenmanagement.generated.model.ZielStandorte;
import de.datev.kundenmanagement.service.MitarbeiterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Transactional
public class MitarbeiterController implements MitarbeiterApi {

    private final MitarbeiterService mitarbeiterService;


    @Override
    public ResponseEntity<MitarbeiterModel> addMitarbeiter(MitarbeiterModel mitarbeiterModel) {
        return ResponseEntity.ok(mitarbeiterService.addMitarbeiter(mitarbeiterModel));
    }

    @Override
    public ResponseEntity<List<MitarbeiterModel>> getAllMitarbeiter(Long geschaeftspartnerId, List<Long> standortIds) {
        return ResponseEntity.ok(mitarbeiterService.getAllMitarbeiter(geschaeftspartnerId, standortIds));
    }

    @Override
    public ResponseEntity<MitarbeiterModel> getMitarbeiterById(Long id) {
        MitarbeiterModel mitarbeiterById = mitarbeiterService.getMitarbeiterById(id);
        return ResponseEntity.ok(mitarbeiterById);
    }

    @Override
    public ResponseEntity<String> mitarbeiterIdDelete(Long id) {
        mitarbeiterService.mitarbeiterIdDelete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<MitarbeiterModel> updateAMitarbeiterAnhandDessenId(Long id, MitarbeiterModel mitarbeiterModel) {
        return mitarbeiterService.updateAMitarbeiterAnhandDessenId(id, mitarbeiterModel);
    }

    @Override
    public ResponseEntity<MitarbeiterModel> wechsleGeschaeftspartner(Long id, Long geschaeftspartnerId) {
        return ResponseEntity.ok(mitarbeiterService.wechsleGeschaeftspartner(id, geschaeftspartnerId));
    }

    @Override
    public ResponseEntity<MitarbeiterModel> wechsleMitarbeiterStandorte(Long id, ZielStandorte zielStandorte) {
        MitarbeiterModel mitarbeiterModel = mitarbeiterService.wechsleMitarbeiterStandorte(id, zielStandorte);
        return ResponseEntity.ok(mitarbeiterModel);
    }


}
