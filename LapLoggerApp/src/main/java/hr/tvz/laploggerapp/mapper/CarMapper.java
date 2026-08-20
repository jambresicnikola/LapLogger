package hr.tvz.laploggerapp.mapper;

import hr.tvz.laploggerapp.command.CarCommand;
import hr.tvz.laploggerapp.command.CarPatchCommand;
import hr.tvz.laploggerapp.dto.CarDto;
import hr.tvz.laploggerapp.model.Car;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    List<CarDto> toDto(List<Car> cars);
    CarDto toDto(Car car);
    Car toEntity(CarCommand carCommand);
    void updateCarFromCommand(CarCommand carCommand, @MappingTarget Car car);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCarFromPatchCommand(CarPatchCommand command, @MappingTarget Car car);
}
