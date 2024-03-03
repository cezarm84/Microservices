package com.kaisar.googleapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Legs {
    @JsonProperty("start_address")
    private String startAddress;
    @JsonProperty("end_address")
    private String endAddress;
    @JsonProperty("start_location")
    private Location startLocation;
    @JsonProperty("end_location")
    private Location endLocation;
    private Distance distance;
    private Duration duration;
    private Steps[] steps;

    @Data
    private class Distance {
        private String text;

    }
    @Data
    private class Duration {
        private String text;


    }

}
