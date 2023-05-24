package knu.networksecuritylab.appserver.app.service.iot;

import knu.networksecuritylab.appserver.app.controller.iot.dto.TemperatureHumidityDto;

public interface IotService {

    void updateTemperatureAndHumidity(final TemperatureHumidityDto temperatureHumidityDto);

    TemperatureHumidityDto getTemperatureAndHumidity();
}
