package knu.networksecuritylab.appserver.app.service.iot;

import knu.networksecuritylab.appserver.app.controller.iot.dto.TemperatureHumidityDto;
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
