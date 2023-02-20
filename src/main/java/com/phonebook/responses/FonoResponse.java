package com.phonebook.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FonoResponse(
        @JsonProperty("Brand")
        String brand,
        @JsonProperty("DeviceName")
        String device,
        @JsonProperty("technology")
        String technology,
        @JsonProperty("_2g_bands")
        String g2,
        @JsonProperty("_3g_bands")
        String g3,
        @JsonProperty("_4g_bands")
        String g4) {
}
