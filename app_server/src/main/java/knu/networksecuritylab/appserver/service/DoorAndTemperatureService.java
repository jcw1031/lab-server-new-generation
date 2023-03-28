package knu.networksecuritylab.appserver.service;

import knu.networksecuritylab.appserver.controller.dto.TemperatureHumidityDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DoorAndTemperatureService implements IotService {

    private final Map<Long, TemperatureHumidityDto> map = new HashMap<>();

    @Override
    public void updateTemperatureAndHumidity(final TemperatureHumidityDto temperatureHumidityDto) {
        map.put(1L, temperatureHumidityDto);
    }

    @Override
    public TemperatureHumidityDto getTemperatureAndHumidity() {
        return map.get(1L);
    }
}
