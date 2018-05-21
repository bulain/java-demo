package com.bulain.zk.vm;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;

import com.bulain.zk.model.CutmField;
import com.bulain.zk.model.CutmFieldItem;

@VariableResolver(DelegatingVariableResolver.class)
public class CutmVm implements Serializable {
    private static final long serialVersionUID = 2450800272813459294L;

    @Wire("#parent")
    private Component parent;
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws WrongValueException, ParseException {
        Selectors.wireComponents(view, this, false);

        composeUI();
    }

    private List<CutmField> fields;

    @Init
    public void init() {
        fields = new ArrayList<CutmField>();

        CutmField textbox = new CutmField();
        textbox.setId(1L);
        textbox.setDataType("varchar");
        textbox.setUiType("textbox");
        textbox.setLabelEn("textbox");
        textbox.setLabelCn("文本框");
        textbox.setMin(0L);
        textbox.setMax(50L);
        textbox.setValue("test");
        fields.add(textbox);

        CutmField longbox = new CutmField();
        longbox.setId(2L);
        longbox.setDataType("long");
        longbox.setUiType("longbox");
        longbox.setLabelEn("longbox");
        longbox.setLabelCn("长整型输入框");
        longbox.setMin(0L);
        longbox.setMax(50L);
        longbox.setValue("100");
        fields.add(longbox);

        CutmField intbox = new CutmField();
        intbox.setId(3L);
        intbox.setDataType("int");
        intbox.setUiType("intbox");
        intbox.setLabelEn("intbox");
        intbox.setLabelCn("整型输入框");
        intbox.setMin(0L);
        intbox.setMax(50L);
        intbox.setValue("100");
        fields.add(intbox);

        CutmField doublebox = new CutmField();
        doublebox.setId(4L);
        doublebox.setDataType("varchar");
        doublebox.setUiType("doublebox");
        doublebox.setLabelEn("doublebox");
        doublebox.setLabelCn("浮点型输入框");
        doublebox.setMin(0L);
        doublebox.setMax(50L);
        doublebox.setValue("100.10");
        fields.add(doublebox);

        CutmField datebox = new CutmField();
        datebox.setId(6L);
        datebox.setDataType("varchar");
        datebox.setUiType("datebox");
        datebox.setLabelEn("datebox");
        datebox.setLabelCn("日期输入框");
        datebox.setMin(0L);
        datebox.setMax(50L);
        datebox.setValue("20120403");
        fields.add(datebox);

        CutmField datetimebox = new CutmField();
        datetimebox.setId(7L);
        datetimebox.setDataType("varchar");
        datetimebox.setUiType("datetimebox");
        datetimebox.setLabelEn("datetimebox");
        datetimebox.setLabelCn("日期时间输入框");
        datetimebox.setMin(0L);
        datetimebox.setMax(50L);
        datetimebox.setValue("201204031200");
        fields.add(datetimebox);

        CutmField timebox = new CutmField();
        timebox.setId(8L);
        timebox.setDataType("varchar");
        timebox.setUiType("timebox");
        timebox.setLabelEn("timebox");
        timebox.setLabelCn("时间输入框");
        timebox.setMin(0L);
        timebox.setMax(50L);
        timebox.setValue("1200");
        fields.add(timebox);

        CutmField combobox = new CutmField();
        combobox.setId(5L);
        combobox.setDataType("varchar");
        combobox.setUiType("combobox");
        combobox.setLabelEn("combobox");
        combobox.setLabelCn("下拉列表框");
        combobox.setMin(0L);
        combobox.setMax(50L);
        combobox.setValue("code2");
        fields.add(combobox);
        List<CutmFieldItem> items = new ArrayList<CutmFieldItem>();
        for (int i = 0; i < 10; i++) {
            CutmFieldItem item = new CutmFieldItem();
            item.setCode("code" + i);
            item.setValueEn("english" + i);
            item.setValueCn("中文" + i);
            items.add(item);
        }
        combobox.setItems(items);

        CutmField checkbox = new CutmField();
        checkbox.setId(9L);
        checkbox.setDataType("varchar");
        checkbox.setUiType("checkbox");
        checkbox.setLabelEn("checkbox");
        checkbox.setLabelCn("多选框");
        checkbox.setMin(0L);
        checkbox.setMax(50L);
        checkbox.setValue("code0@#@code2");
        fields.add(checkbox);
        checkbox.setItems(items);

        CutmField radio = new CutmField();
        radio.setId(10L);
        radio.setDataType("varchar");
        radio.setUiType("radiobox");
        radio.setLabelEn("radiobox");
        radio.setLabelCn("单选框");
        radio.setMin(0L);
        radio.setMax(50L);
        radio.setValue("code2");
        fields.add(radio);
        radio.setItems(items);
    }

    private void composeUI() throws WrongValueException, ParseException {
        for (CutmField field : fields) {
            String uiType = field.getUiType();
            String value = field.getValue();
            String labelCn = field.getLabelCn();
            Label label = new Label(labelCn);
            label.setParent(parent);
            if ("textbox".equals(uiType)) {
                Textbox textbox = new Textbox();
                textbox.setValue(value);
                textbox.setParent(parent);
            } else if ("longbox".equals(uiType)) {
                Longbox longbox = new Longbox();
                longbox.setValue(NumberUtils.toLong(value));
                longbox.setParent(parent);
            } else if ("intbox".equals(uiType)) {
                Intbox intbox = new Intbox();
                intbox.setValue(NumberUtils.toInt(value));
                intbox.setParent(parent);
            } else if ("doublebox".equals(uiType)) {
                Doublebox doublebox = new Doublebox();
                doublebox.setValue(NumberUtils.toDouble(value));
                doublebox.setParent(parent);
            } else if ("datebox".equals(uiType)) {
                Datebox datebox = new Datebox();
                datebox.setFormat("yyyy-MM-dd");
                datebox.setWidth("90px");
                datebox.setValue(DateUtils.parseDate(value, new String[]{"yyyyMMdd"}));
                datebox.setParent(parent);
            } else if ("datetimebox".equals(uiType)) {
                Datebox datetimebox = new Datebox();
                datetimebox.setFormat("yyyy-MM-dd HH:ss");
                datetimebox.setWidth("125px");
                datetimebox.setValue(DateUtils.parseDate(value, new String[]{"yyyyMMddHHss"}));
                datetimebox.setParent(parent);
            } else if ("timebox".equals(uiType)) {
                Timebox timebox = new Timebox();
                timebox.setFormat("HH:ss");
                timebox.setWidth("60px");
                timebox.setValue(DateUtils.parseDate(value, new String[]{"HHss"}));
                timebox.setParent(parent);
            } else if ("combobox".equals(uiType)) {
                Combobox combobox = new Combobox();
                combobox.setWidth("125px");
                combobox.setParent(parent);

                Comboitem selectedItem = null;
                List<CutmFieldItem> items = field.getItems();
                for (CutmFieldItem item : items) {
                    Comboitem comboitem = new Comboitem(item.getValueCn());
                    comboitem.setValue(item.getCode());
                    comboitem.setParent(combobox);
                    
                    if(value.equals(item.getCode())){
                        selectedItem = comboitem;
                    }
                }
                
                combobox.setSelectedItem(selectedItem);
            } else if ("checkbox".equals(uiType)) {
                Hlayout hlayout = new Hlayout();
                hlayout.setHflex("min");
                hlayout.setParent(parent);

                String[] split = value.split("@#@");
                List<String> list = Arrays.asList(split);
                List<CutmFieldItem> items = field.getItems();
                for (CutmFieldItem item : items) {
                    Checkbox checkbox = new Checkbox(item.getValueCn());
                    checkbox.setValue(item.getCode());
                    checkbox.setChecked(list.contains(item.getCode()));
                    checkbox.setParent(hlayout);
                }
            } else if ("radiobox".equals(uiType)) {
                Radiogroup radiogroup = new Radiogroup();
                radiogroup.setHflex("min");
                radiogroup.setParent(parent);

                List<CutmFieldItem> items = field.getItems();
                for (CutmFieldItem item : items) {
                    Radio radio = new Radio(item.getValueCn());
                    radio.setValue(item.getCode());
                    radio.setSelected(value.equals(item.getCode()));
                    radio.setParent(radiogroup);
                }
            }
        }

    }

    @Command
    public void save() {
        List<Component> children = parent.getChildren();
        for (int i = 0; i < fields.size(); i++) {
            CutmField field = fields.get(i);
            Component comp = children.get(i * 2 + 1);

            String uiType = field.getUiType();
            if ("textbox".equals(uiType)) {
                Textbox textbox = (Textbox) comp;
                String value = textbox.getValue();
                field.setValue(value);
            } else if ("longbox".equals(uiType)) {
                Longbox longbox = (Longbox) comp;
                Long value = longbox.getValue();
                field.setValue(toString(value));
            } else if ("intbox".equals(uiType)) {
                Intbox intbox = (Intbox) comp;
                Integer value = intbox.getValue();
                field.setValue(toString(value));
            } else if ("doublebox".equals(uiType)) {
                Doublebox doublebox = (Doublebox) comp;
                Double value = doublebox.getValue();
                field.setValue(toString(value));
            } else if ("datebox".equals(uiType)) {
                Datebox datebox = (Datebox) comp;
                Date value = datebox.getValue();
                field.setValue(toString(value, "date"));
            } else if ("datetimebox".equals(uiType)) {
                Datebox datetimebox = (Datebox) comp;
                Date value = datetimebox.getValue();
                field.setValue(toString(value, "datetime"));
            } else if ("timebox".equals(uiType)) {
                Timebox timebox = (Timebox) comp;
                Date value = timebox.getValue();
                field.setValue(toString(value, "time"));
            } else if ("combobox".equals(uiType)) {
                Combobox combobox = (Combobox) comp;
                Comboitem selectedItem = combobox.getSelectedItem();
                Object value = selectedItem.getValue();
                field.setValue(toString(value));
            } else if ("checkbox".equals(uiType)) {
                Hlayout hlayout = (Hlayout) comp;
                String value = null;
                List<Component> checkboxs = hlayout.getChildren();
                for (Component c : checkboxs) {
                    Checkbox checkbox = (Checkbox) c;
                    if (checkbox.isChecked()) {
                        Object o = checkbox.getValue();
                        if (value == null) {
                            value = toString(o);
                        } else {
                            value += "@#@" + toString(o);
                        }
                    }
                }
                field.setValue(value);
            } else if ("radiobox".equals(uiType)) {
                Radiogroup radiogroup = (Radiogroup) comp;
                Radio radio = radiogroup.getSelectedItem();
                Object value = radio.getValue();
                field.setValue(toString(value));
            }

            System.out.println(field.getValue());
        }

    }
    private String toString(Object n, String... type) {
        String str = null;
        if (n instanceof Date) {
            DateFormat df = null;
            if (type.length > 0) {
                if ("date".equals(type[0])) {
                    df = new SimpleDateFormat("yyyyMMdd");
                } else if ("datetime".equals(type[0])) {
                    df = new SimpleDateFormat("yyyyMMddHHss");
                } else if ("time".equals(type[0])) {
                    df = new SimpleDateFormat("HHss");
                }
            }

            if (df == null) {
                df = new SimpleDateFormat("yyyyMMdd");
            }

            str = n != null ? df.format(n) : null;
        } else if (n instanceof Number) {
            str = n != null ? n.toString() : null;
        } else if (n instanceof String) {
            str = (String) n;
        }
        return str;
    }

    public List<CutmField> getFields() {
        return fields;
    }
    public void setFields(List<CutmField> fields) {
        this.fields = fields;
    }

}
