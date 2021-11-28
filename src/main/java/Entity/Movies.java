package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "movieWorkerMovie")
    private List<MovieWorker> workers = new ArrayList<>();
}
