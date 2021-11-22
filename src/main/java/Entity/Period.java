package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Period {
    @Column(name ="START_TIME")
    private LocalDateTime startTime;

    @Column(name ="END_TIME")
    private LocalDateTime endTime;
}
