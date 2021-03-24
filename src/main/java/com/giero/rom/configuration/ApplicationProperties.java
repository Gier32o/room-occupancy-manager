package com.giero.rom.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
    private int premiumRoomThreshold;

    public ApplicationProperties() {
    }

    public ApplicationProperties(int premiumRoomThreshold) {
        this.premiumRoomThreshold = premiumRoomThreshold;
    }

    public int getPremiumRoomThreshold() {
        return premiumRoomThreshold;
    }

    public void setPremiumRoomThreshold(int premiumRoomThreshold) {
        this.premiumRoomThreshold = premiumRoomThreshold;
    }
}
