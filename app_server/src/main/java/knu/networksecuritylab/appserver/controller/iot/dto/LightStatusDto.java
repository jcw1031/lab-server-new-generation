package knu.networksecuritylab.appserver.controller.iot.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LightStatusDto {

    @NotNull(message = "불 켜짐 여부는 null 값일 수 없습니다.")
    private Boolean isLightOn;

    public LightStatusDto(Boolean isLightOn) {
        this.isLightOn = isLightOn;
    }
}
