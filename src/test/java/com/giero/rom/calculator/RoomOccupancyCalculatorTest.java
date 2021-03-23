package com.giero.rom.calculator;

import com.giero.rom.configuration.ApplicationProperties;
import com.giero.rom.dto.CalculateOccupancyRequestDto;
import com.giero.rom.dto.CalculateOccupancyResultDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomOccupancyCalculatorTest {

    private final ApplicationProperties applicationProperties = new ApplicationProperties(100);
    private final List<Integer> customerOffers = Arrays.asList(23, 45, 155, 374, 22, 99, 100, 101, 115, 209);
    private final RoomOccupancyCalculator calculator = new RoomOccupancyCalculator(applicationProperties);

    @Test
    public void calculateOccupancy_shouldCorrectlyOptimizeRoomDistribution_scenario1() {
        //given
        var requestDto = new CalculateOccupancyRequestDto(3, 3, customerOffers);
        var expectedResult = new CalculateOccupancyResultDto(3, 3, 167, 738);

        //when
        var actualResult = calculator.calculateOccupancy(requestDto);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateOccupancy_shouldCorrectlyOptimizeRoomDistribution_scenario2() {
        //given
        var requestDto = new CalculateOccupancyRequestDto(5, 7, customerOffers);
        var expectedResult = new CalculateOccupancyResultDto(4, 6, 189, 1054);

        //when
        var actualResult = calculator.calculateOccupancy(requestDto);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateOccupancy_shouldCorrectlyOptimizeRoomDistribution_scenario3() {
        //given
        var requestDto = new CalculateOccupancyRequestDto(7, 2, customerOffers);
        var expectedResult = new CalculateOccupancyResultDto(4, 2, 189, 583);

        //when
        var actualResult = calculator.calculateOccupancy(requestDto);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateOccupancy_shouldCorrectlyOptimizeRoomDistribution_scenario4() {
        //given
        var requestDto = new CalculateOccupancyRequestDto(1, 7, customerOffers);
        var expectedResult = new CalculateOccupancyResultDto(1, 7, 45, 1153);

        //when
        var actualResult = calculator.calculateOccupancy(requestDto);

        //then
        assertEquals(expectedResult, actualResult);
    }

}