package com.ridebeep.bdipticketingserver.model.enums;

public enum TimeUnit {
    DAYS("days"),
    HOURS("hours"),
    MINUTES("minutes"),
    SECONDS("seconds");

    private String unit;

    TimeUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
