package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class CMbaseEntity {
    @Column(name ="CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name ="MODIFIED_DATE")
    private LocalDateTime modifiedDate;
}
