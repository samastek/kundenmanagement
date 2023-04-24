package de.datev.kundenmanagement.repository;

import de.datev.kundenmanagement.entity.GeschaeftspartnerMitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeschaeftspartnerMitarbeiterRepository extends JpaRepository<GeschaeftspartnerMitarbeiter, Long> {

    GeschaeftspartnerMitarbeiter findByMitarbeiterIdAndActive(Long mitarbeiterId, Boolean active);
}
