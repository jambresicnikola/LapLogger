package hr.tvz.laploggerapp.mapper;

import hr.tvz.laploggerapp.command.TrackCommand;
import hr.tvz.laploggerapp.command.TrackPatchCommand;
import hr.tvz.laploggerapp.dto.TrackDto;
import hr.tvz.laploggerapp.model.Track;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackMapper {
    List<TrackDto> toDto(List<Track> tracks);
    TrackDto toDto(Track track);
    Track toEntity(TrackCommand trackCommand);
    void updateTrackFromCommand(TrackCommand trackCommand, @MappingTarget Track track);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTrackFromPatchCommand(TrackPatchCommand trackPatchCommand, @MappingTarget Track track);
}
