package de.unternehmen.kundenmanagement.repository;

import de.unternehmen.kundenmanagement.entity.Anschrift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnschriftRepository extends JpaRepository<Anschrift, Long> {

}
