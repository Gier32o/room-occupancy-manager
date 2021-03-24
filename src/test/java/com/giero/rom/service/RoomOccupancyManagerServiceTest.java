package com.giero.rom.service;

import com.giero.rom.calculator.RoomOccupancyCalculator;
import com.giero.rom.dto.CalculateOccupancyRequestDto;
import com.giero.rom.dto.CalculateOccupancyResultDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomOccupancyManagerServiceTest {

    @Mock
    private RoomOccupancyCalculator roomOccupancyCalculator;

    private RoomOccupancyManagerService service;

    @BeforeEach
    public void setUp() {
        service = new RoomOccupancyManagerService(roomOccupancyCalculator);
    }

    @Test
    public void calculateOccupancy_shouldExecuteSuccessfully() {
        //given
        var requestDto = new CalculateOccupancyRequestDto(0, 0, emptyList());
        var resultDto = new CalculateOccupancyResultDto(0, 0, 0, 0);

        when(roomOccupancyCalculator.calculateOccupancy(eq(requestDto))).thenReturn(resultDto);

        //when
        var actualResultDto = service.calculateOccupancy(requestDto);

        //then
        assertEquals(resultDto, actualResultDto);
        verify(roomOccupancyCalculator).calculateOccupancy(eq(requestDto));
    }

}