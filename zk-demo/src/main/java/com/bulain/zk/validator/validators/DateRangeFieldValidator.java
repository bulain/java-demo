package com.bulain.zk.validator.validators;

import java.util.Date;

public class DateRangeFieldValidator extends AbstractRangeValidator {
    private Date max;
    private Date min;

    protected String getDefaultMessageKey() {
        return "validate.date";
    }

    protected Comparable<Date> getMaxComparatorValue() {
        return max;
    }
    protected Comparable<Date> getMinComparatorValue() {
        return min;
    }

    public Date getMax() {
        return max;
    }
    public void setMax(Date max) {
        this.max = max;
    }
    public Date getMin() {
        return min;
    }
    public void setMin(Date min) {
        this.min = min;
    }
}
