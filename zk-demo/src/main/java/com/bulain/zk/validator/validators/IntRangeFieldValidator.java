package com.bulain.zk.validator.validators;

public class IntRangeFieldValidator extends AbstractRangeValidator {
    private Integer max;
    private Integer min;
    
    protected String getDefaultMessageKey() {
        return "validate.int";
    }
    
    public Comparable<Integer> getMaxComparatorValue() {
        return max;
    }
    public Comparable<Integer> getMinComparatorValue() {
        return min;
    }
    
    public Integer getMax() {
        return max;
    }
    public void setMax(Integer max) {
        this.max = max;
    }
    public Integer getMin() {
        return min;
    }
    public void setMin(Integer min) {
        this.min = min;
    }
}
