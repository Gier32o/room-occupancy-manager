package com.giero.rom.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Objects;

public class CalculateOccupancyRequestDto {
    @Min(0)
    private final int availableEconomyRooms;

    @Min(0)
    private final int availablePremiumRooms;

    @NotEmpty
    private final Collection<@Min(1) Integer> customerOffers;

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
