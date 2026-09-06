package hr.tvz.laploggerapp.mapper;

import hr.tvz.laploggerapp.command.LapCommand;
import hr.tvz.laploggerapp.command.LapPatchCommand;
import hr.tvz.laploggerapp.dto.LapDto;
import hr.tvz.laploggerapp.model.Lap;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = SessionMapper.class)
public interface LapMapper {
    List<LapDto> toDtoList(List<Lap> laps);
    LapDto toDto(Lap lap);

    @Mapping(target = "session", ignore = true)
    Lap toEntity(LapCommand lapCommand);

    @Mapping(target = "session", ignore = true)
    void updateLapFromLapCommand(LapCommand lapCommand, @MappingTarget Lap lap);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "session", ignore = true)
    void updateLapFromPatchCommand(LapPatchCommand lapPatchCommand, @MappingTarget Lap lap);
}
