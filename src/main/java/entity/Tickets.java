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
@Table(name="TICKETS")
@AllArgsConstructor
@NoArgsConstructor
public class Tickets {
    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="TICKET_ID")
    private Integer ticketId;

    @Column(name ="STATUS", nullable = false)
    @Enumerated
    private TicketStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="SCREEN_ID", nullable = false)
    private Screens screen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="USER_ID", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<ScreenSeat> screenSeats = new LinkedHashSet<>();

    public void setUser(Users user) {
        if(this.user != null){
            this.user.getTickets().remove(this);
        }
        this.user = user;
        user.getTickets().add(this);
    }

    public void setScreen(Screens screen) {
        if(this.screen != null){
            this.screen.getTickets().remove(this);
        }
        this.screen = screen;
        screen.getTickets().add(this);
    }
}
