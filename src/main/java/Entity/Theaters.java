package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="THEATERS")
@AllArgsConstructor
@NoArgsConstructor

public class Theaters {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="THEATER_ID")
    private Integer theaterId;
    private String name;
    private String floor;
}
