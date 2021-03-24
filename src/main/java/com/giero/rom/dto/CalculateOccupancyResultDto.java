package com.giero.rom.dto;

import java.util.Objects;

public class CalculateOccupancyResultDto {
    private final int occupiedEconomyRooms;
    private final int occupiedPremiumRooms;
    private final double economyRoomsIncome;
    private final double premiumRoomsIncome;

    public CalculateOccupancyResultDto(int occupiedEconomyRooms, int occupiedPremiumRooms, double economyRoomsIncome, double premiumRoomsIncome) {
        this.occupiedEconomyRooms = occupiedEconomyRooms;
        this.occupiedPremiumRooms = occupiedPremiumRooms;
        this.economyRoomsIncome = economyRoomsIncome;
        this.premiumRoomsIncome = premiumRoomsIncome;
    }

    public int getOccupiedEconomyRooms() {
        return occupiedEconomyRooms;
    }

    public int getOccupiedPremiumRooms() {
        return occupiedPremiumRooms;
    }

    public double getEconomyRoomsIncome() {
        return economyRoomsIncome;
    }

    public double getPremiumRoomsIncome() {
        return premiumRoomsIncome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculateOccupancyResultDto that = (CalculateOccupancyResultDto) o;
        return occupiedEconomyRooms == that.occupiedEconomyRooms && occupiedPremiumRooms == that.occupiedPremiumRooms && Double.compare(that.economyRoomsIncome, economyRoomsIncome) == 0 && Double.compare(that.premiumRoomsIncome, premiumRoomsIncome) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(occupiedEconomyRooms, occupiedPremiumRooms, economyRoomsIncome, premiumRoomsIncome);
    }
}