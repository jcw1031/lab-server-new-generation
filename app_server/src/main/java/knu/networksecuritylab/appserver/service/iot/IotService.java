package knu.networksecuritylab.appserver.service.iot;

import knu.networksecuritylab.appserver.controller.iot.dto.TemperatureHumidityDto;

public interface IotService {

    void updateTemperatureAndHumidity(final TemperatureHumidityDto temperatureHumidityDto);

    TemperatureHumidityDto getTemperatureAndHumidity();
}
