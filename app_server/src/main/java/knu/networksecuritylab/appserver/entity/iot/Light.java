package knu.networksecuritylab.appserver.entity.iot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Light {

    @Id
    @CreatedDate
    private LocalDateTime createdAt;
    private boolean isLightOn;

    public Light(boolean isLightOn) {
        this.isLightOn = isLightOn;
    }
}
