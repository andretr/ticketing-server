package com.ridebeep.bdipticketingserver.model.enums;

public enum TimeUnit {
    NANOSECONDS("NANOSECONDS"),
    MICROSECONDS("MICROSECONDS"),
    MILLISECONDS("MILLISECONDS"),
    SECONDS("SECONDS"),
    MINUTES("MINUTES"),
    HOURS("MINUTES"),
    DAYS("DAYS");

    private String unit;

    TimeUnit(String timeUnit) {
        this.unit = timeUnit;
    }

    public String getUnit() {
        return unit;
    }
}
