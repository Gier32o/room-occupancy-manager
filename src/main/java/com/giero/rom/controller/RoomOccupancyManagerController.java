package com.giero.rom.controller;

import com.giero.rom.dto.CalculateOccupancyRequestDto;
import com.giero.rom.dto.CalculateOccupancyResultDto;
import com.giero.rom.service.RoomOccupancyManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/room-management")
public class RoomOccupancyManagerController {

    private final RoomOccupancyManagerService roomOccupancyManagerService;

    @Autowired
    public RoomOccupancyManagerController(RoomOccupancyManagerService roomOccupancyManagerService) {
        this.roomOccupancyManagerService = roomOccupancyManagerService;
    }

    @PostMapping("/calculate-occupancy")
    CalculateOccupancyResultDto calculateOccupancy(@RequestBody @Valid CalculateOccupancyRequestDto calculateOccupancyRequestDto) {
        return roomOccupancyManagerService.calculateOccupancy(calculateOccupancyRequestDto);
    }

}