package com.giero.rom.dto;

import java.util.Collection;

public record CalculateOccupancyRequestDto(
        int availableEconomyRooms,
        int availablePremiumRooms,
        Collection<Integer> customerOffers) {
}
