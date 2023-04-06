package knu.networksecuritylab.appserver.controller.iot;

import knu.networksecuritylab.appserver.controller.iot.dto.DoorStatusDto;
import knu.networksecuritylab.appserver.controller.iot.dto.TemperatureHumidityDto;
import knu.networksecuritylab.appserver.entity.iot.Door;
import knu.networksecuritylab.appserver.repository.IotRepository;
import knu.networksecuritylab.appserver.service.iot.IotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/iot")
@RequiredArgsConstructor
public class IotController {

    private final IotService iotService;
    private final IotRepository iotRepository;

    @PostMapping("/door")
    public ResponseEntity<String> doorStatusUpdate(@RequestBody final DoorStatusDto doorStatusDto) {
        iotRepository.save(new Door(doorStatusDto.getIsDoorOpen()));
        return ResponseEntity.ok().body("The door status has been updated");
    }

    @GetMapping("/door")
    public ResponseEntity<DoorStatusDto> isDoorOpen() {
        Door doorStatus = iotRepository.findFirstByOrderByCreatedAtDesc();
        return ResponseEntity.ok().body(new DoorStatusDto(doorStatus.isDoorOpen()));
    }

    @PostMapping("/temperature-humidity")
    public ResponseEntity<String> temperatureAndHumidityUpdate(
            @RequestBody final TemperatureHumidityDto temperatureHumidityDto
    ) {
        iotService.updateTemperatureAndHumidity(temperatureHumidityDto);
        return ResponseEntity.ok().body("Temperature and humidity were updated");
    }

    @GetMapping("/temperature-humidity")
    public ResponseEntity<TemperatureHumidityDto> getTemperatureAndHumidity() {
        TemperatureHumidityDto temperatureAndHumidity = iotService.getTemperatureAndHumidity();
        return ResponseEntity.ok().body(temperatureAndHumidity);
    }
}
