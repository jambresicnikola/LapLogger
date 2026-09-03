package hr.tvz.laploggerapp.mapper;

import hr.tvz.laploggerapp.command.SessionCommand;
import hr.tvz.laploggerapp.command.SessionPatchCommand;
import hr.tvz.laploggerapp.dto.SessionDto;
import hr.tvz.laploggerapp.model.Session;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CarMapper.class, TrackMapper.class})
public interface SessionMapper {
    SessionDto toDto(Session session);
    List<SessionDto> toDtoList(List<Session> sessions);

    @Mapping(target = "car", ignore = true)
    @Mapping(target = "track", ignore = true)
    Session toEntity(SessionCommand sessionCommand);

    @Mapping(target = "car", ignore = true)
    @Mapping(target = "track", ignore = true)
    void updateSessionFromSessionCommand(SessionCommand sessionCommand, @MappingTarget Session session);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "car", ignore = true)
    @Mapping(target = "track", ignore = true)
    void updateSessionFromPatchCommand(SessionPatchCommand sessionPatchCommand, @MappingTarget Session session);
}
