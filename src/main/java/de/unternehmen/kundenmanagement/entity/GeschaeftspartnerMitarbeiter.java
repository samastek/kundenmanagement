package de.unternehmen.kundenmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "geschaeftsparnter_mitarbeiter")
@Getter
@Setter
public class GeschaeftspartnerMitarbeiter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gm_generator")
    @SequenceGenerator(name = "gm_generator", sequenceName = "gm_seq")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Mitarbeiter mitarbeiter;
    @ManyToOne(cascade = CascadeType.ALL)
    private Geschaeftspartner geschaeftspartner;
    // TODO: ersetzen durch Austrittsdatum/Eintrittsdatum eines Mitarbeiters
    private Boolean active;
}
