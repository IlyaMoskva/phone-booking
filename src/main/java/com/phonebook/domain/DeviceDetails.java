package com.phonebook.domain;

import java.util.Date;

public record DeviceDetails(
    int id,
    String brand,
    String device,
    String technology,
    String g2,
    String g3,
    String g4,
    String taken_by,
    Date taken_at
) {}
