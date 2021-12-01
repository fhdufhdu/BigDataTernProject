package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="Directors")
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("director")
//@PrimaryKeyJoinColumn(name ="WORKER_ID")
public class Directors extends Workers{
    @Column(name = "BIRTH_PLACE", nullable = false)
    private String birthPlace;
}
