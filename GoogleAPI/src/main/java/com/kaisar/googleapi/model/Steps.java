package com.kaisar.googleapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter


public class Steps {
    @JsonProperty("travel_mode")
    private String travelMode;
    @JsonProperty("start_location")
    private Location startLocation;
    @JsonProperty("end_location")
    private Location endLocation;
    private Duration duration;
    private String maneuver;

    private  Distance distance;
    @Data
    private  class Location {
        private double lat;
        private double lng;

    }
    @Data
    private  class Duration {
        private String text;
        private int value;
    }
    @Data
    private  class Distance {
        private String text;
        private int value;
    }
}
