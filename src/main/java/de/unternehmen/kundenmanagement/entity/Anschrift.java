package de.unternehmen.kundenmanagement.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Anschrift {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "anschrift_generator")
    @SequenceGenerator(name = "anschrift_generator", sequenceName = "anschrift_seq")
    private Long id;
    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;
    private String land;
    private String zusatz;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Anschrift anschrift)) {
            return false;
        }

        if (!getId().equals(anschrift.getId())) {
            return false;
        }
        if (!getStrasse().equals(anschrift.getStrasse())) {
            return false;
        }
        if (!getHausnummer().equals(anschrift.getHausnummer())) {
            return false;
        }
        if (!getPlz().equals(anschrift.getPlz())) {
            return false;
        }
        if (!getOrt().equals(anschrift.getOrt())) {
            return false;
        }
        if (!getLand().equals(anschrift.getLand())) {
            return false;
        }
        return getZusatz() != null ? getZusatz().equals(anschrift.getZusatz()) : anschrift.getZusatz() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getStrasse().hashCode();
        result = 31 * result + getHausnummer().hashCode();
        result = 31 * result + getPlz().hashCode();
        result = 31 * result + getOrt().hashCode();
        result = 31 * result + getLand().hashCode();
        result = 31 * result + (getZusatz() != null ? getZusatz().hashCode() : 0);
        return result;
    }
}
