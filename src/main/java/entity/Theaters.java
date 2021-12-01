package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="THEATERS")
@AllArgsConstructor
@NoArgsConstructor

public class Theaters {
    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="THEATER_ID")
    private Integer theaterId;
    private String name;
    private String floor;

    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    Set<Seats> seats = new LinkedHashSet<>();

}
