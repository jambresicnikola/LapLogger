package hr.tvz.laploggerapp.service.impl;

import hr.tvz.laploggerapp.command.CarCommand;
import hr.tvz.laploggerapp.command.CarPatchCommand;
import hr.tvz.laploggerapp.dto.CarDto;
import hr.tvz.laploggerapp.exception.ResourceNotFoundException;
import hr.tvz.laploggerapp.mapper.CarMapper;
import hr.tvz.laploggerapp.model.Car;
import hr.tvz.laploggerapp.repository.CarRepository;
import hr.tvz.laploggerapp.service.CarService;
import hr.tvz.laploggerapp.util.ErrorMessage;
import hr.tvz.laploggerapp.util.RepositoryUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public List<CarDto> fetchAllCars() {
        return carMapper.toDto(carRepository.findAll());
    }

    @Override
    public List<CarDto> fetchAllCarsByMake(String make) {
        return carMapper.toDto(carRepository.findAllByMakeIgnoreCase(make));
    }

    @Override
    public CarDto fetchCarById(Long id) {
        return carRepository.findById(id).map(carMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.CAR_NOT_FOUND.getMessage() + id));
    }

    @Override
    public CarDto createCar(CarCommand carCommand) {
        Car car = carRepository.save(carMapper.toEntity(carCommand));

        return carMapper.toDto(car);
    }

    @Override
    public void deleteCarById(Long id) {
        if (!carRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorMessage.CAR_NOT_FOUND.getMessage() + id);
        }

        carRepository.deleteById(id);
    }

    @Override
    public CarDto updateCar(Long id, CarCommand carCommand) {
        Car car = RepositoryUtils.findOrThrow(
                carRepository.findById(id), ErrorMessage.CAR_NOT_FOUND.getMessage() + id);

        carMapper.updateCarFromCommand(carCommand, car);
        car = carRepository.save(car);

        return carMapper.toDto(car);
    }

    @Override
    public CarDto patchCar(Long id, CarPatchCommand carPatchCommand) {
        Car car = RepositoryUtils.findOrThrow(
                carRepository.findById(id), ErrorMessage.CAR_NOT_FOUND.getMessage() + id);

        carMapper.updateCarFromPatchCommand(carPatchCommand, car);
        car = carRepository.save(car);

        return carMapper.toDto(car);
    }
}
