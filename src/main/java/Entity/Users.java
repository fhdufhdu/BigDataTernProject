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
@Table(name="USERS")
@AllArgsConstructor
@NoArgsConstructor
public class Users extends CMbaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="USER_ID")
    private Integer userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tickets> tickets = new ArrayList<Tickets>();
}
