package de.datev.kundenmanagement.repository;

import de.datev.kundenmanagement.entity.Geschaeftspartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeschaeftspartnerRepository extends JpaRepository<Geschaeftspartner, Long> {

}
