package Entity;

import com.querydsl.core.Tuple;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="MOVIE_WORKER")
@AllArgsConstructor
@NoArgsConstructor

public class MovieWorker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="MOVIE_WORKER_ID")
    private Integer movieWorkerId;

    @Enumerated(EnumType.STRING)
    @Column(name ="ROLE_TYPE")
    private RoleType roleType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private Movies movieWorkerMovie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORKER_ID", nullable = false)
    private Workers worker;

    public void setMovieWorkerMovie(Movies movieWorkerMovie) {
        this.movieWorkerMovie = movieWorkerMovie;
        movieWorkerMovie.getWorkers().add(this);
    }
}