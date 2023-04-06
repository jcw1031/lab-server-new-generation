package knu.networksecuritylab.appserver.controller.iot.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DoorStatusDto {

    private Boolean isDoorOpen;

    public DoorStatusDto(Boolean isDoorOpen) {
        this.isDoorOpen = isDoorOpen;
    }
}
