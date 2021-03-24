package com.giero.rom.calculator;

import com.giero.rom.configuration.ApplicationProperties;
import com.giero.rom.dto.CalculateOccupancyRequestDto;
import com.giero.rom.dto.CalculateOccupancyResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        var premiumRoomsDistributionList = request.getCustomerOffers().stream()
                .filter(offer -> offer >= applicationProperties.getPremiumRoomThreshold())
                .sorted(reverseOrder())
                .limit(request.getAvailablePremiumRooms())
                .collect(toList());

        var remainingPremiumRooms = request.getAvailablePremiumRooms() - premiumRoomsDistributionList.size();
        var economyRoomsDeficiency = (request.getCustomerOffers().size() - premiumRoomsDistributionList.size()) - request.getAvailableEconomyRooms();
        var possibilityToUpgrade = remainingPremiumRooms > 0 && economyRoomsDeficiency > 0;

        if (possibilityToUpgrade) {
            premiumRoomsDistributionList.addAll(request.getCustomerOffers().stream()
                    .filter(offer -> offer < applicationProperties.getPremiumRoomThreshold())
                    .sorted(reverseOrder())
                    .limit(Math.min(economyRoomsDeficiency, remainingPremiumRooms))
                    .collect(toList()));
        }

        var economyRoomsDistributionList = request.getCustomerOffers().stream()
                .filter(offer -> offer < applicationProperties.getPremiumRoomThreshold())
                .sorted(reverseOrder())
                .skip(possibilityToUpgrade ? Math.min(economyRoomsDeficiency, remainingPremiumRooms) : 0)
                .limit(request.getAvailableEconomyRooms())
                .collect(toList());

        return new CalculateOccupancyResultDto(
                economyRoomsDistributionList.size(),
                premiumRoomsDistributionList.size(),
                economyRoomsDistributionList.stream().mapToInt(Integer::intValue).sum(),
                premiumRoomsDistributionList.stream().mapToInt(Integer::intValue).sum()
        );
    }
}