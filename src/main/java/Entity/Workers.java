package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
@Table(name="WORKERS")
@AllArgsConstructor
@NoArgsConstructor

public class Workers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="WORKER_ID")
    private Integer workerId;
    private String name;
    private Date birth;
}
