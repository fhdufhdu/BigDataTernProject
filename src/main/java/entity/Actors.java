package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="ACTORS")
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("actor")
//@PrimaryKeyJoinColumn(name ="WORKER_ID")
public class Actors extends Workers{
    @Column(nullable = false)
    private Integer height;
    @Column(name = "INSTAGRAM_ID")
    private String instagramId;
}
