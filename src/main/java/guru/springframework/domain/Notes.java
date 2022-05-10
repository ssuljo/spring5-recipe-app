package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Recipe recipe;

    @Lob
    private String recipeNotes;

    public Notes() {
    }
}
