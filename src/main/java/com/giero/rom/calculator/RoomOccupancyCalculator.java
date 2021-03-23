package com.giero.rom.calculator;

import com.giero.rom.configuration.ApplicationProperties;
import com.giero.rom.dto.CalculateOccupancyRequestDto;
import com.giero.rom.dto.CalculateOccupancyResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

@Component
public class RoomOccupancyCalculator {

    private final ApplicationProperties applicationProperties;

    @Autowired
    public RoomOccupancyCalculator(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public CalculateOccupancyResultDto calculateOccupancy(CalculateOccupancyRequestDto request) {
        List<Integer> premiumRoomsDistributionList = request.customerOffers().stream()
                .filter(offer -> offer >= applicationProperties.premiumRoomThreshold())
                .sorted(reverseOrder())
                .limit(request.availablePremiumRooms())
                .collect(toList());

        int remainingPremiumRooms = request.availablePremiumRooms() - premiumRoomsDistributionList.size();
        int economyRoomsDeficiency = (request.customerOffers().size() - premiumRoomsDistributionList.size()) - request.availableEconomyRooms();
        boolean possibilityToUpgrade = remainingPremiumRooms > 0 && economyRoomsDeficiency > 0;

        if (possibilityToUpgrade) {
            premiumRoomsDistributionList.addAll(request.customerOffers().stream()
                    .filter(offer -> offer < applicationProperties.premiumRoomThreshold())
                    .sorted(reverseOrder())
                    .limit(Math.min(economyRoomsDeficiency, remainingPremiumRooms))
                    .collect(toList()));
        }

        List<Integer> economyRoomsDistributionList = request.customerOffers().stream()
                .filter(offer -> offer < applicationProperties.premiumRoomThreshold())
                .sorted(reverseOrder())
                .skip(possibilityToUpgrade ? Math.min(economyRoomsDeficiency, remainingPremiumRooms) : 0)
                .limit(request.availableEconomyRooms())
                .collect(toList());

        return new CalculateOccupancyResultDto(
                economyRoomsDistributionList.size(),
                premiumRoomsDistributionList.size(),
                economyRoomsDistributionList.stream().mapToInt(Integer::intValue).sum(),
                premiumRoomsDistributionList.stream().mapToInt(Integer::intValue).sum()
        );
    }
}