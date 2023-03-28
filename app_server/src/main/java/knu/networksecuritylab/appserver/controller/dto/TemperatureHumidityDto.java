package knu.networksecuritylab.appserver.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemperatureHumidityDto {

    private float temperature;
    private float humidity;

    public TemperatureHumidityDto(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }
}
