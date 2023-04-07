package knu.networksecuritylab.appserver.controller.iot;

import knu.networksecuritylab.appserver.controller.iot.dto.DoorStatusDto;
import knu.networksecuritylab.appserver.controller.iot.dto.LightStatusDto;
import knu.networksecuritylab.appserver.controller.iot.dto.TemperatureHumidityDto;
import knu.networksecuritylab.appserver.entity.iot.Door;
import knu.networksecuritylab.appserver.entity.iot.Light;
import knu.networksecuritylab.appserver.repository.DoorRepository;
import knu.networksecuritylab.appserver.repository.LightRepository;
import knu.networksecuritylab.appserver.service.iot.IotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/iot")
@RequiredArgsConstructor
public class IotController {

    private final IotService iotService;
    private final DoorRepository doorRepository;
    private final LightRepository lightRepository;

    @PostMapping("/door")
    public ResponseEntity<String> doorStatusUpdate(@RequestBody final DoorStatusDto doorStatusDto) {
        doorRepository.save(new Door(doorStatusDto.getIsDoorOpen()));
        return ResponseEntity.ok().body("The door status has been updated");
    }

    @GetMapping("/door")
    public ResponseEntity<DoorStatusDto> getDoorStatus() {
        Door doorStatus = doorRepository.findFirstByOrderByCreatedAtDesc();
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

    @PostMapping("/light")
    public ResponseEntity<String> lightStatusUpdate(@RequestBody final LightStatusDto lightStatusDto) {
        lightRepository.save(new Light(lightStatusDto.getIsLightOn()));
        return ResponseEntity.ok().body("The light status has been updated");
    }

    @GetMapping("/light")
    public ResponseEntity<LightStatusDto> getLightStatus() {
        Light lightStatus = lightRepository.findFirstByOrderByCreatedAtDesc();
        return ResponseEntity.ok().body(new LightStatusDto(lightStatus.isLightOn()));
    }
}
