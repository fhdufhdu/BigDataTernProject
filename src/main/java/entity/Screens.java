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
@Table(name="SCREENS")
@AllArgsConstructor
@NoArgsConstructor

public class Screens {
    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="SCREEN_ID", nullable = false)
    private Integer screenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="THEATER_ID", nullable = false)
    private Theaters theater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MOVIE_ID", nullable = false)
    private Movies screenMovie;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "screen")
    private Set<Tickets> tickets = new LinkedHashSet<>();

    @Embedded
    private Period period;

}
