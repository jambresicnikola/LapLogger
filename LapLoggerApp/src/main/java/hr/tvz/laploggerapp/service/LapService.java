package hr.tvz.laploggerapp.service;

import hr.tvz.laploggerapp.command.LapCommand;
import hr.tvz.laploggerapp.command.LapPatchCommand;
import hr.tvz.laploggerapp.dto.LapDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface LapService {
    LapDto fetchLapById(Long id);
    List<LapDto> fetchAllLapsBySessionId(Long sessionId);
    LapDto createLap(LapCommand lapCommand);
    void deleteLapById(Long id);
    LapDto updateLap(Long id, LapCommand lapCommand);
    LapDto patchLap(Long id, LapPatchCommand lapPatchCommand);
}
