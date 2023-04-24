package de.datev.kundenmanagement.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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

    private Boolean active;
}
