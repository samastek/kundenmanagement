package de.datev.kundenmanagement.repository;

import de.datev.kundenmanagement.entity.Mitarbeiter;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Long> {

    @Query("SELECT m FROM Mitarbeiter m JOIN m.geschaeftspartnerMitarbeiter gm JOIN gm.geschaeftspartner g JOIN g.standorte s WHERE g.id = :geschaeftspartnerId AND s.id IN :standortIds")
    List<Mitarbeiter> findAllByGeschaeftspartnerIdAndStandortList(@Param("geschaeftspartnerId") Long geschaeftspartnerId,
            @Param("standortIds") List<Long> standortIds);

    @Query("SELECT m FROM Mitarbeiter m JOIN m.standorte s WHERE s.id IN :standortIds")
    List<Mitarbeiter> findAllByStandortList(List<Long> standortIds);

    @Query("SELECT DISTINCT m FROM Mitarbeiter m JOIN FETCH m.geschaeftspartnerMitarbeiter gpm WHERE gpm.geschaeftspartner.id = :geschaeftspartnerId AND gpm.active = true")
    List<Mitarbeiter> findAllMitarbeiterByAktivGeschaeftspartnerId(@Param("geschaeftspartnerId") Long geschaeftspartnerId);

}
