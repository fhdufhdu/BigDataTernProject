package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

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
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="USER_ID")
    private Integer userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Tickets> tickets = new ArrayList<Tickets>();
}
