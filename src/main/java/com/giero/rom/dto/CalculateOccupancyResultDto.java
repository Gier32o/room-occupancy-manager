package com.giero.rom.dto;

public record CalculateOccupancyResultDto(
        int occupiedEconomyRooms,
        int occupiedPremiumRooms,
        double economyRoomsIncome,
        double premiumRoomsIncome) {
}