package de.unternehmen.kundenmanagement.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Geschaeftspartner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geschaeftspartner_generator")
    @SequenceGenerator(name = "geschaeftspartner_generator", sequenceName = "geschaeftspartner_seq")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "geschaeftspartner", fetch = FetchType.LAZY)
    private Set<GeschaeftspartnerMitarbeiter> geschaeftspartnerMitarbeiter;
    @OneToMany(mappedBy = "geschaeftspartner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Standort> standorte;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Geschaeftspartner that)) {
            return false;
        }

        if (!getId().equals(that.getId())) {
            return false;
        }
        return getName().equals(that.getName());
    }


    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
