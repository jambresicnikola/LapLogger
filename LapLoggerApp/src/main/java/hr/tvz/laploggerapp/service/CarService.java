package hr.tvz.laploggerapp.service;

import hr.tvz.laploggerapp.command.CarCommand;
import hr.tvz.laploggerapp.command.CarPatchCommand;
import hr.tvz.laploggerapp.dto.CarDto;

import java.util.List;

public interface CarService {
    List<CarDto> fetchAllCars();
    List<CarDto> fetchAllCarsByMake(String make);
    CarDto fetchCarById(Long id);
    CarDto createCar(CarCommand carCommand);
    void deleteCarById(Long id);
    CarDto updateCar(Long id, CarCommand carCommand);
    CarDto patchCar(Long id, CarPatchCommand carPatchCommand);
}