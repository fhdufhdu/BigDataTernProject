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
@Table(name="SCREEN_SEAT")
@AllArgsConstructor
@NoArgsConstructor

public class ScreenSeat {
    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="SCREEN_SEAT_ID")
    private Integer screenSeatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SEAT_ID", nullable = false)
    private Seats seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TICKET_ID", nullable = false)
    private Tickets ticket;

    public void setTicket(Tickets ticket) {
        if(this.ticket != null){
            this.ticket.getScreenSeats().remove(this);
        }
        this.ticket = ticket;
        ticket.getScreenSeats().add(this);
    }
}
