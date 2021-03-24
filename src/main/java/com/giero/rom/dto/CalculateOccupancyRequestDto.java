package com.giero.rom.dto;

import java.util.Collection;
import java.util.Objects;

public class CalculateOccupancyRequestDto {
    private final int availableEconomyRooms;
    private final int availablePremiumRooms;
    private final Collection<Integer> customerOffers;

    public CalculateOccupancyRequestDto(int availableEconomyRooms, int availablePremiumRooms, Collection<Integer> customerOffers) {
        this.availableEconomyRooms = availableEconomyRooms;
        this.availablePremiumRooms = availablePremiumRooms;
        this.customerOffers = customerOffers;
    }

    public int getAvailableEconomyRooms() {
        return availableEconomyRooms;
    }

    public int getAvailablePremiumRooms() {
        return availablePremiumRooms;
    }

    public Collection<Integer> getCustomerOffers() {
        return customerOffers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculateOccupancyRequestDto that = (CalculateOccupancyRequestDto) o;
        return availableEconomyRooms == that.availableEconomyRooms && availablePremiumRooms == that.availablePremiumRooms && Objects.equals(customerOffers, that.customerOffers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(availableEconomyRooms, availablePremiumRooms, customerOffers);
    }
}
