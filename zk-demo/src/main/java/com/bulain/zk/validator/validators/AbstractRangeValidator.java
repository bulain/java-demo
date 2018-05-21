package com.bulain.zk.validator.validators;

import org.zkoss.bind.ValidationContext;
import org.zkoss.util.resource.Labels;

public abstract class AbstractRangeValidator extends FieldValidator {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void validate(ValidationContext ctx) {
        Object obj = getFieldValue(ctx);
        Comparable<Object> value = (Comparable) obj;

        if (value == null) {
            return;
        }

        if ((getMinComparatorValue() != null) && (value.compareTo(getMinComparatorValue()) < 0)) {
            String key = getNamespace() + "." + getFieldName();
            String message = Labels.getLabel(getMessageKey(), new Object[]{Labels.getLabel(key), getMin(), getMax()});
            addInvalidMessage(ctx, getFieldName(), message);
        }

        if ((getMaxComparatorValue() != null) && (value.compareTo(getMaxComparatorValue()) > 0)) {
            String key = getNamespace() + "." + getFieldName();
            String message = Labels.getLabel(getMessageKey(), new Object[]{Labels.getLabel(key), getMin(), getMax()});
            addInvalidMessage(ctx, getFieldName(), message);
        }
    }

    protected abstract Comparable<?> getMaxComparatorValue();
    protected abstract Comparable<?> getMinComparatorValue();
    protected abstract Object getMin();
    protected abstract Object getMax();
}
