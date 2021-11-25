package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "theater")
    List<Seats> seats = new ArrayList<>();

}
