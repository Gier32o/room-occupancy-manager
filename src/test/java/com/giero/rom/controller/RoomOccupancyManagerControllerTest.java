package com.giero.rom.controller;

import com.giero.rom.dto.CalculateOccupancyRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.with;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class RoomOccupancyManagerControllerTest {

    @LocalServerPort
    private int port;

    private final List<Integer> customerOffers = Arrays.asList(23, 45, 155, 374, 22, 99, 100, 101, 115, 209);

    @Test
    public void calculateOccupancy_shouldExecuteSuccessfully_scenario1() {
        //@formatter:off
        with().body(new CalculateOccupancyRequestDto(3,3, customerOffers))
                .contentType("application/json")
                .given()
                    .port(port)
                .when()
                    .post("/room-management/calculate-occupancy")
                .then()
                    .statusCode(200)
                    .assertThat()
                        .body("occupiedEconomyRooms", equalTo(3))
                        .body("occupiedPremiumRooms", equalTo(3))
                        .body("economyRoomsIncome", equalTo(167.0f))
                        .body("premiumRoomsIncome", equalTo(738.0f));
        //@formatter:on
    }

    @Test
    public void calculateOccupancy_shouldExecuteSuccessfully_scenario2() {
        //@formatter:off
        with().body(new CalculateOccupancyRequestDto(5,7, customerOffers))
                .contentType("application/json")
                .given()
                    .port(port)
                .when()
                    .post("/room-management/calculate-occupancy")
                .then()
                    .statusCode(200)
                    .assertThat()
                        .body("occupiedEconomyRooms", equalTo(4))
                        .body("occupiedPremiumRooms", equalTo(6))
                        .body("economyRoomsIncome", equalTo(189.0f))
                        .body("premiumRoomsIncome", equalTo(1054.0f));
        //@formatter:on
    }

    @Test
    public void calculateOccupancy_shouldExecuteSuccessfully_scenario3() {
        //@formatter:off
        with().body(new CalculateOccupancyRequestDto(7,2, customerOffers))
                .contentType("application/json")
                .given()
                    .port(port)
                .when()
                    .post("/room-management/calculate-occupancy")
                .then()
                    .statusCode(200)
                    .assertThat()
                        .body("occupiedEconomyRooms", equalTo(4))
                        .body("occupiedPremiumRooms", equalTo(2))
                        .body("economyRoomsIncome", equalTo(189.0f))
                        .body("premiumRoomsIncome", equalTo(583.0f));
        //@formatter:on
    }

    @Test
    public void calculateOccupancy_shouldExecuteSuccessfully_scenario4() {
        //@formatter:off
        with().body(new CalculateOccupancyRequestDto(1,7, customerOffers))
                .contentType("application/json")
                .given()
                    .port(port)
                .when()
                    .post("/room-management/calculate-occupancy")
                .then()
                    .statusCode(200)
                    .assertThat()
                        .body("occupiedEconomyRooms", equalTo(1))
                        .body("occupiedPremiumRooms", equalTo(7))
                        .body("economyRoomsIncome", equalTo(45.0f))
                        .body("premiumRoomsIncome", equalTo(1153.0f));
        //@formatter:on
    }

    @Test
    public void calculateOccupancy_shouldResultInBadRequest_whenAvailableEconomyRoomsIsANegativeNumber() {
        //@formatter:off
        with().body(new CalculateOccupancyRequestDto(-1,1, customerOffers))
                .contentType("application/json")
                .given()
                    .port(port)
                .when()
                    .post("/room-management/calculate-occupancy")
                .then()
                    .statusCode(400);
        //@formatter:on
    }

    @Test
    public void calculateOccupancy_shouldResultInBadRequest_whenAvailablePremiumRoomsIsANegativeNumber() {
        //@formatter:off
        with().body(new CalculateOccupancyRequestDto(1,-1, customerOffers))
                .contentType("application/json")
                .given()
                    .port(port)
                .when()
                    .post("/room-management/calculate-occupancy")
                .then()
                    .statusCode(400);
        //@formatter:on
    }

    @Test
    public void calculateOccupancy_shouldResultInBadRequest_whenAtLeastOneCustomerOfferIsNotAPositiveNumber() {
        //@formatter:off
        with().body(new CalculateOccupancyRequestDto(1,1, Arrays.asList(1, 0)))
                .contentType("application/json")
                .given()
                    .port(port)
                .when()
                    .post("/room-management/calculate-occupancy")
                .then()
                    .statusCode(400);
        //@formatter:on
    }

    @Test
    public void calculateOccupancy_shouldResultInBadRequest_whenCustomerOffersIsEmpty() {
        //@formatter:off
        with().body(new CalculateOccupancyRequestDto(1,1, emptyList()))
                .contentType("application/json")
                .given()
                    .port(port)
                .when()
                    .post("/room-management/calculate-occupancy")
                .then()
                    .statusCode(400);
        //@formatter:on
    }

}