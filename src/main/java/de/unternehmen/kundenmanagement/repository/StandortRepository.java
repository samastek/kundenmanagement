package de.unternehmen.kundenmanagement.repository;

import de.unternehmen.kundenmanagement.entity.Standort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandortRepository extends JpaRepository<Standort, Long> {

    @Query("SELECT s FROM Standort s JOIN s.mitarbeiter m WHERE m.id = :mitarbeiterId AND s.geschaeftspartner.id = :geschaeftspartnerId")
    List<Standort> findAllByMitarbeiterIdAndGeschaeftspartnerId(Long mitarbeiterId, Long geschaeftspartnerId);

    List<Standort> findAllByGeschaeftspartnerId(Long geschaeftspartnerId);

    @Query(value = "SELECT * FROM Standort s JOIN mitarbeiter_standort sm ON s.id = sm.mitarbeiterid WHERE sm.mitarbeiterid = :mitarbeiterId", nativeQuery = true)
    List<Standort> findAllByMitarbeiterId(@Param("mitarbeiterId") Long mitarbeiterId);

    @Query("SELECT s FROM Standort s WHERE s.id IN (:standorteIds)")
    List<Standort> findAllByIds(@Param("standorteIds") List<Long> standorteIds);
}
