package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="SEATS")
@AllArgsConstructor
@NoArgsConstructor

public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="SEAT_ID")
    private Integer seatId;
    @Column(nullable = false)
    private String seatRow;
    @Column(nullable = false)
    private String seatColumn;
    private String Status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="THEATER_ID", nullable = false)
    private Theaters theater;
}
