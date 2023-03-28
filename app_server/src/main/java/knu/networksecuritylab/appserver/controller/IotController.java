package knu.networksecuritylab.appserver.controller;

import knu.networksecuritylab.appserver.controller.dto.DoorStatusDto;
import knu.networksecuritylab.appserver.entity.iot.Door;
import knu.networksecuritylab.appserver.repository.IotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/iot")
@RequiredArgsConstructor
public class IotController {

    private final IotRepository iotRepository;

    @PostMapping("/door")
    public ResponseEntity<String> doorStatus(@RequestBody DoorStatusDto doorStatusDto) {
        iotRepository.save(new Door(doorStatusDto.getIsDoorOpen()));
        return ResponseEntity.ok().body("Door status update complete");
    }
}
