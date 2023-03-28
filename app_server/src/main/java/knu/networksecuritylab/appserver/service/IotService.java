package knu.networksecuritylab.appserver.service;

import knu.networksecuritylab.appserver.controller.dto.TemperatureHumidityDto;

public interface IotService {

    void updateTemperatureAndHumidity(final TemperatureHumidityDto temperatureHumidityDto);

    TemperatureHumidityDto getTemperatureAndHumidity();
}
