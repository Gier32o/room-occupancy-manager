package com.giero.rom.service;

import com.giero.rom.dto.CalculateOccupancyRequestDto;
import com.giero.rom.dto.CalculateOccupancyResultDto;
import org.springframework.stereotype.Service;

@Service
public class RoomOccupancyManagerService {

    public CalculateOccupancyResultDto calculateOccupancy(CalculateOccupancyRequestDto request) {
        return new CalculateOccupancyResultDto(0, 0, 0, 0);
    }

}
