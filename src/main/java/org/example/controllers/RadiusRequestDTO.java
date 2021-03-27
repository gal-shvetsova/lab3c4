package org.example.controllers;

import lombok.Data;

@Data
public class RadiusRequestDTO {
    private final double lat;
    private final double lon;
    private final double radius;
}
