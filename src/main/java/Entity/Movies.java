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
@Table(name="MOVIES")
@AllArgsConstructor
@NoArgsConstructor

public class Movies extends CMbaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="MOVIE_ID")
    private Integer movieId;
    private String name;
    @Column(name = "OPENING_DATE")
    private LocalDateTime openingDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(name = "RUNNING_TIME")
    private Integer runningTime;
}
