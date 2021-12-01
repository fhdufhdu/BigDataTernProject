package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="MOVIES")
@AllArgsConstructor
@NoArgsConstructor

public class Movies extends CMbaseEntity{
    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="MOVIE_ID")
    private Integer movieId;
    private String name;
    @Column(name = "OPENING_DATE")
    private Date openingDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(name = "RUNNING_TIME")
    private Integer runningTime;

    @OneToMany(mappedBy = "movieWorkerMovie", fetch = FetchType.LAZY)
    private List<MovieWorker> workers = new ArrayList<>();
}
