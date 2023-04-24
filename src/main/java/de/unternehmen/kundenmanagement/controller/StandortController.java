package de.unternehmen.kundenmanagement.controller;

import de.unternehmen.kundenmanagement.generated.api.StandorteApi;
import de.unternehmen.kundenmanagement.generated.model.StandortModel;
import de.unternehmen.kundenmanagement.service.StandortService;
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
public class StandortController implements StandorteApi {

    private final StandortService standortService;

    @Override
    public ResponseEntity<StandortModel> addStandort(StandortModel standortModel) {
        standortService.addStandort(standortModel);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<StandortModel> getStandortById(Long id) {
        return ResponseEntity.ok(standortService.getStandortById(id));
    }

    @Override
    public ResponseEntity<List<StandortModel>> getStandorte(Long mitarbeiterId, Long geschaeftspartnerId) {
        return ResponseEntity.ok(standortService.getStandorte(mitarbeiterId, geschaeftspartnerId));
    }

    @Override
    public ResponseEntity<String> standorteIdDelete(Long id) {
        standortService.standorteIdDelete(id);
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<String> uebernehmeStandort(Long id, Long geschaeftspartnerId) {
        standortService.uebernehmeStandort(id, geschaeftspartnerId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<StandortModel> updateStandort(Long id, StandortModel standortModel) {
        StandortModel standortModel1 = standortService.updateStandort(id, standortModel);
        return ResponseEntity.ok(standortModel1);
    }
}
