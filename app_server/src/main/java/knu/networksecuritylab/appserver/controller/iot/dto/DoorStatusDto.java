package knu.networksecuritylab.appserver.controller.iot.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DoorStatusDto {

    @NotNull(message = "문 열림 여부는 null 값일 수 업습니다.")
    private Boolean isDoorOpen;

    public DoorStatusDto(Boolean isDoorOpen) {
        this.isDoorOpen = isDoorOpen;
    }
}
