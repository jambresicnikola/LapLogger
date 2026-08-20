package hr.tvz.laploggerapp.controller;

import hr.tvz.laploggerapp.command.CarCommand;
import hr.tvz.laploggerapp.command.CarPatchCommand;
import hr.tvz.laploggerapp.dto.CarDto;
import hr.tvz.laploggerapp.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> fetchAllCars(@RequestParam(required = false) String make) {
        if (make != null) {
            return ResponseEntity.ok(carService.fetchAllCarsByMake(make));
        }

        return ResponseEntity.ok(carService.fetchAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> fetchCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.fetchCarById(id));
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@Valid @RequestBody CarCommand carCommand) {
        CarDto createdCarDto = carService.createCar(carCommand);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCarDto.id())
                .toUri();

        return ResponseEntity.created(location).body(createdCarDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable Long id, @Valid @RequestBody CarCommand carCommand) {
        return ResponseEntity.ok(carService.updateCar(id, carCommand));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarDto> patchCar(@PathVariable Long id, @Valid @RequestBody CarPatchCommand carPatchCommand) {
        return ResponseEntity.ok(carService.patchCar(id, carPatchCommand));
    }
}
