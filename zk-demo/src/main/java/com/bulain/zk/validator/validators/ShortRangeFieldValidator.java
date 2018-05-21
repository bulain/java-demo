package com.bulain.zk.validator.validators;

public class ShortRangeFieldValidator extends AbstractRangeValidator {
    private Short max;
    private Short min;

    protected String getDefaultMessageKey() {
        return "validate.short";
    }
    
    public Comparable<Short> getMaxComparatorValue() {
        return max;
    }
    public Comparable<Short> getMinComparatorValue() {
        return min;
    }

    public Short getMax() {
        return max;
    }
    public void setMax(Short max) {
        this.max = max;
    }
    public Short getMin() {
        return min;
    }
    public void setMin(Short min) {
        this.min = min;
    }
}
