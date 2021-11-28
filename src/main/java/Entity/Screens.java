package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="SCREENS")
@AllArgsConstructor
@NoArgsConstructor

public class Screens {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="SCREEN_ID", nullable = false)
    private Integer screenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="THEATER_ID", nullable = false)
    private Theaters theater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MOVIE_ID", nullable = false)
    private Movies screenMovie;

    @Embedded
    private Period period;

}
