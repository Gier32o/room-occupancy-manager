package com.giero.rom.service;

import com.giero.rom.calculator.RoomOccupancyCalculator;
import com.giero.rom.dto.CalculateOccupancyRequestDto;
import com.giero.rom.dto.CalculateOccupancyResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomOccupancyManagerService {

    private final RoomOccupancyCalculator roomOccupancyCalculator;

    @Autowired
    public RoomOccupancyManagerService(RoomOccupancyCalculator roomOccupancyCalculator) {
        this.roomOccupancyCalculator = roomOccupancyCalculator;
    }

    public CalculateOccupancyResultDto calculateOccupancy(CalculateOccupancyRequestDto request) {
        return roomOccupancyCalculator.calculateOccupancy(request);
    }
}
