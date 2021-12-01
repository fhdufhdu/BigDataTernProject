package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="SEATS")
@AllArgsConstructor
@NoArgsConstructor

public class Seats {
    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="SEAT_ID")
    private Integer seatId;
    @Column(nullable = false)
    private String seatRow;
    @Column(nullable = false)
    private String seatColumn;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="THEATER_ID", nullable = false)
    private Theaters theater;

    public void setTheater(Theaters theater) {
        if(this.theater != null){
            this.theater.getSeats().remove(this);
        }
        this.theater = theater;
        theater.getSeats().add(this);
    }
}
