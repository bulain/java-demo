package com.bulain.zk.validator.validators;

import org.zkoss.bind.ValidationContext;
import org.zkoss.util.resource.Labels;

public class DoubleRangeFieldValidator extends FieldValidator {

    private String maxInclusive = null;
    private String minInclusive = null;
    private String minExclusive = null;
    private String maxExclusive = null;

    private Double maxInclusiveValue = null;
    private Double minInclusiveValue = null;
    private Double minExclusiveValue = null;
    private Double maxExclusiveValue = null;

    public void validate(ValidationContext ctx) {
        Double value;
        try {
            Object obj = this.getFieldValue(ctx);
            if (obj == null) {
                return;
            }
            value = Double.valueOf(obj.toString());
        } catch (NumberFormatException e) {
            return;
        }

        parseParameterValues();
        if ((maxInclusiveValue != null && value.compareTo(maxInclusiveValue) > 0)
                || (minInclusiveValue != null && value.compareTo(minInclusiveValue) < 0)
                || (maxExclusiveValue != null && value.compareTo(maxExclusiveValue) >= 0)
                || (minExclusiveValue != null && value.compareTo(minExclusiveValue) <= 0)) {
            String key = getNamespace() + "." + getFieldName();
            String message = Labels.getLabel(getMessageKey(), new Object[]{Labels.getLabel(key), minInclusiveValue, maxInclusiveValue});
            addInvalidMessage(ctx, getFieldName(), message);
        }
    }
    
    protected String getDefaultMessageKey() {
        return "validate.double";
    }

    private void parseParameterValues() {
        this.minInclusiveValue = parseDouble(minInclusive);
        this.maxInclusiveValue = parseDouble(maxInclusive);
        this.minExclusiveValue = parseDouble(minExclusive);
        this.maxExclusiveValue = parseDouble(maxExclusive);
    }

    private Double parseDouble(String value) {
        if (value != null) {
            try {
                return Double.valueOf(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setMaxInclusive(String maxInclusive) {
        this.maxInclusive = maxInclusive;
    }

    public String getMaxInclusive() {
        return maxInclusive;
    }

    public void setMinInclusive(String minInclusive) {
        this.minInclusive = minInclusive;
    }

    public String getMinInclusive() {
        return minInclusive;
    }

    public String getMinExclusive() {
        return minExclusive;
    }

    public void setMinExclusive(String minExclusive) {
        this.minExclusive = minExclusive;
    }

    public String getMaxExclusive() {
        return maxExclusive;
    }

    public void setMaxExclusive(String maxExclusive) {
        this.maxExclusive = maxExclusive;
    }
}