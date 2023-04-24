package de.unternehmen.kundenmanagement.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mitarbeiter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mitarbeiter_generator")
    @SequenceGenerator(name = "mitarbeiter_generator", sequenceName = "mitarbeiter_seq")
    private Long id;
    private String vorname;
    private String nachname;
    private String telefonnummer;
    private String email;
    private LocalDate geburtsdatum;
    @OneToMany(mappedBy = "mitarbeiter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<GeschaeftspartnerMitarbeiter> geschaeftspartnerMitarbeiter = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "mitarbeiter_standort",
            joinColumns = @JoinColumn(name = "mitarbeiterId"),
            inverseJoinColumns = @JoinColumn(name = "standortId"))
    private Set<Standort> standorte = new HashSet<>();

    @OneToOne
    @NotNull(message = "Die Anschrift vom Mitarbeiter darf nicht null sein")
    private Anschrift anschrift;

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getVorname().hashCode();
        result = 31 * result + getNachname().hashCode();
        result = 31 * result + getTelefonnummer().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getGeburtsdatum().hashCode();
        result = 31 * result + getAnschrift().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mitarbeiter that)) {
            return false;
        }

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) {
            return false;
        }
        if (getVorname() != null ? !getVorname().equals(that.getVorname()) : that.getVorname() != null) {
            return false;
        }
        if (getNachname() != null ? !getNachname().equals(that.getNachname()) : that.getNachname() != null) {
            return false;
        }
        if (getTelefonnummer() != null ? !getTelefonnummer().equals(that.getTelefonnummer()) : that.getTelefonnummer() != null) {
            return false;
        }
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) {
            return false;
        }
        if (getGeburtsdatum() != null ? !getGeburtsdatum().equals(that.getGeburtsdatum()) : that.getGeburtsdatum() != null) {
            return false;
        }
        return getAnschrift() != null ? getAnschrift().equals(that.getAnschrift()) : that.getAnschrift() == null;
    }

}
