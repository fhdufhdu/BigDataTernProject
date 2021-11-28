package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="TICKETS")
@AllArgsConstructor
@NoArgsConstructor
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="TICKET_ID")
    private Integer ticketId;

    @Column(name ="IS_CANCELED", nullable = false)
    private Boolean isCanceled = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="SCREEN_ID", nullable = false)
    private Screens screen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="USER_ID", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ScreenSeat> screenSeats = new ArrayList<>();

    public void setUser(Users user) {
        if(this.user != null){
            this.user.getTickets().remove(this);
        }
        this.user = user;
        user.getTickets().add(this);
    }
}
