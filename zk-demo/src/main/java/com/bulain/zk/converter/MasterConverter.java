package com.bulain.zk.converter;

import java.util.Iterator;
import java.util.Set;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ext.Selectable;

import com.bulain.common.pojo.Master;

@SuppressWarnings({"unchecked", "rawtypes"})
public class MasterConverter implements Converter {

    public Object coerceToUi(Object val, Component comp, BindContext ctx) {
        Combobox cbx = (Combobox) comp;
        final ListModel<?> model = cbx.getModel();
        if (model != null && !(model instanceof Selectable)) {
            throw new UiException("model doesn't implement Selectable");
        }

        if (val != null) {
            Object item = val instanceof Long ? new Master((Long) val, "") : val;
            if (model != null) {
                ((Selectable<Object>) model).addToSelection(item);
                return IGNORED_VALUE;
            } else {
                for (final Iterator<?> it = cbx.getItems().iterator(); it.hasNext();) {
                    final Comboitem ci = (Comboitem) it.next();

                    Object bean = ci.getValue();

                    if (val.equals(bean)) {
                        return ci;
                    }
                }
            }
        }

        if (model != null) {
            Set<Object> sels = ((Selectable<Object>) model).getSelection();
            if (sels != null && sels.size() > 0)
                ((Selectable<Object>) model).clearSelection();
            return IGNORED_VALUE;
        }
        return null;
    }

    public Object coerceToBean(Object val, Component comp, BindContext ctx) {
        if (val != null) {
            final Combobox lbx = (Combobox) comp;
            final ListModel<?> model = lbx.getModel();
            if (model != null && !(model instanceof Selectable)) {
                throw new UiException("model doesn't implement Selectable");
            }
            if (model != null) {
                Set<?> selection = ((Selectable<?>) model).getSelection();
                if (selection == null || selection.size() == 0)
                    return null;
                Master item = (Master) selection.iterator().next();
                return item.getKey();
            } else {// no model
                return ((Comboitem) val).getValue();
            }
        }
        return null;
    }

}
