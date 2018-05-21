package com.bulain.zk.validator.validators;

public class LongRangeFieldValidator extends AbstractRangeValidator {
    private Long max;
    private Long min;

    protected String getDefaultMessageKey() {
        return "validate.long";
    }
    
    public Comparable<Long> getMaxComparatorValue() {
        return max;
    }
    public Comparable<Long> getMinComparatorValue() {
        return min;
    }

    public Long getMax() {
        return max;
    }
    public void setMax(Long max) {
        this.max = max;
    }
    public Long getMin() {
        return min;
    }
    public void setMin(Long min) {
        this.min = min;
    }
}
