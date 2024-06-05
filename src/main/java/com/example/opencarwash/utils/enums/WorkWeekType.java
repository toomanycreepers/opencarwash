package com.example.opencarwash.utils.enums;

public enum WorkWeekType {
    FOURDAY(4),
    FIVEDAY(5),
    SIXDAY(6),
    FULLWEEK(7);

    private final Integer daysWorking;

    private WorkWeekType(Integer daysWorking)
    {
        this.daysWorking=daysWorking;
    }

    public Integer getDaysWorking() {
        return this.daysWorking;
    }
}
