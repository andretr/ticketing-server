package com.ridebeep.bdipticketingserver.model.enums;

public enum TimeUnit {
    NANOSECONDS("NANOSECONDS"),
    MICROSECONDS("MICROSECONDS"),
    MILLISECONDS("MILLISECONDS"),
    SECONDS("SECONDS"),
    MINUTES("MINUTES"),
    HOURS("MINUTES"),
    DAYS("DAYS");

    private String timeUnit;

    TimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getTimeUnit() {
        return timeUnit;
    }
}
