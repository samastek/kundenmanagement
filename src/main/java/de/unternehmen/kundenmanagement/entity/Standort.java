package de.unternehmen.kundenmanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Standort {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "standort_generator")
    @SequenceGenerator(name = "standort_generator", sequenceName = "standort_seq")
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Geschaeftspartner geschaeftspartner;
    @OneToOne
    private Anschrift anschrift;
    @ManyToMany(mappedBy = "standorte", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Mitarbeiter> mitarbeiter;

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAnschrift().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Standort standort)) {
            return false;
        }

        if (!getId().equals(standort.getId())) {
            return false;
        }
        if (!getName().equals(standort.getName())) {
            return false;
        }
        return getAnschrift().equals(standort.getAnschrift());
    }

}
