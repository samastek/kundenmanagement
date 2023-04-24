package de.datev.kundenmanagement.controller;

import de.datev.kundenmanagement.generated.api.GeschaeftspartnerApi;
import de.datev.kundenmanagement.generated.model.GeschaeftspartnerModel;
import de.datev.kundenmanagement.service.GeschaeftspartnerService;
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
public class GeschaeftspartnerController implements GeschaeftspartnerApi {

    private final GeschaeftspartnerService geschaeftspartnerService;

    @Override
    public ResponseEntity<GeschaeftspartnerModel> addGeschaeftspartner(GeschaeftspartnerModel geschaeftspartnerModel) {
        return ResponseEntity.ok(geschaeftspartnerService.addGeschaeftspartner(geschaeftspartnerModel));
    }

    @Override
    public ResponseEntity<String> deleteGeschaeftspartner(Long id) {
        geschaeftspartnerService.deleteGeschaeftspartner(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<GeschaeftspartnerModel>> getAllGeschaeftspartner() {
        return ResponseEntity.ok(geschaeftspartnerService.getAllGeschaeftspartner());
    }

    @Override
    public ResponseEntity<GeschaeftspartnerModel> getGeschaeftspartnerById(Long id) {
        return ResponseEntity.ok(geschaeftspartnerService.getGeschaeftspartnerById(id));
    }

    @Override
    public ResponseEntity<GeschaeftspartnerModel> updateGeschaeftspartner(Long id, GeschaeftspartnerModel geschaeftspartnerModel) {
        return ResponseEntity.ok(geschaeftspartnerService.updateGeschaeftspartner(id, geschaeftspartnerModel));
    }

}
