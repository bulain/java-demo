package com.bulain.gwt.form.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class HelloForm implements EntryPoint {

    public void onModuleLoad() {
        final DynamicForm form = new DynamicForm();
        form.setWidth(800);
        form.setNumCols(4);

        final SelectItem selectItemA = new SelectItem();
        selectItemA.setName("selectItemA");
        selectItemA.setTitle("Drop Down List");
        selectItemA.setAddUnknownValues(false);
        selectItemA.setRedrawOnChange(true);
        final MBeanServiceAsync mbeanService = GWT.create(MBeanService.class);
        mbeanService.queryMBeanInfo(new AsyncCallback<List<String>>() {
            public void onFailure(Throwable caught) {
                SC.say("MBeanServiceAsync.onFailure(Throwable)");
            }
            public void onSuccess(List<String> result) {
                selectItemA.setValueMap(result.toArray(new String[0]));
            }

        });

        final SelectItem selectItemB = new SelectItem();
        selectItemB.setName("selectItemB");
        selectItemB.setTitle("Drop Down List");
        selectItemB.setAddUnknownValues(false);
        selectItemB.setRedrawOnChange(true);

        List<FormItem> listItem = new ArrayList<FormItem>();
        listItem.add(selectItemA);
        listItem.add(selectItemB);

        for (int i = 1; i <= 10; i++) {
            final TextItem param = new TextItem();
            param.setName("param" + i);
            param.setTitle("param" + i);
            param.setRequired(false);
            param.setVisible(true);
            param.setDisabled(true);
            param.setRedrawOnChange(true);

            listItem.add(param);
        }

        ButtonItem button = new ButtonItem("Submit");
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                String objectName = (String) selectItemA.getValue();
                String operationName = (String) selectItemB.getValue();

                List<String> params = new ArrayList<String>();
                for (int i = 1; i <= 10; i++) {
                    FormItem item = form.getItem("param" + i);
                    if (!item.isDisabled()) {
                        params.add((String) item.getValue());
                    }
                }

                mbeanService.submitOperation(objectName, operationName, params.toArray(new String[0]),
                        new AsyncCallback<String>() {
                            public void onFailure(Throwable caught) {
                                SC.say("MBeanServiceAsync.onFailure(Throwable)");
                            }

                            public void onSuccess(String result) {
                                SC.say("MBeanServiceAsync.onSuccess(String) - " + result);
                            }
                        });
            }
        });
        listItem.add(button);

        form.setItems(listItem.toArray(new FormItem[0]));
        RootPanel.get().add(form);

        final ListGrid operGrid = new ListGrid();
        operGrid.setTitle("MbeanOperGridationInfo");
        operGrid.setWidth(800);
        operGrid.setHeight(120);
        operGrid.setTop(20);
        operGrid.setShowAllRecords(true);

        ListGridField operInfoField = new ListGridField("info", "Information", 100);
        ListGridField operNameField = new ListGridField("name", "Name", 100);
        ListGridField operTypeField = new ListGridField("type", "Type", 100);
        ListGridField operDescriptionField = new ListGridField("description", "Description");

        operGrid.setFields(new ListGridField[]{operInfoField, operNameField, operTypeField, operDescriptionField});
        RootPanel.get().add(operGrid);

        final ListGrid attrGrid = new ListGrid();
        attrGrid.setTitle("MBeanAttributeInfo");
        attrGrid.setWidth(800);
        attrGrid.setHeight(200);
        attrGrid.setTop(40);
        attrGrid.setShowAllRecords(true);

        ListGridField nameField = new ListGridField("name", "Attr Name", 100);
        ListGridField valueField = new ListGridField("value", "Attr Value", 100);
        ListGridField typeField = new ListGridField("type", "Attr Type", 100);
        ListGridField descriptionField = new ListGridField("description", "Description");

        attrGrid.setFields(new ListGridField[]{nameField, valueField, typeField, descriptionField});

        RootPanel.get().add(attrGrid);

        selectItemA.addChangedHandler(new ChangedHandler() {
            public void onChanged(ChangedEvent event) {
                String selectedItem = (String) event.getValue();

                mbeanService.getMBeanInfo(selectedItem, new AsyncCallback<MbeanInfo>() {
                    public void onFailure(Throwable caught) {
                        SC.say("MBeanServiceAsync.onFailure(Throwable)");
                    }

                    public void onSuccess(MbeanInfo result) {
                        //Operations
                        List<String> listOper = new ArrayList<String>();
                        MbeanOper[] opers = result.getOpers();
                        for (MbeanOper oper : opers) {
                            listOper.add(oper.getName());
                        }
                        selectItemB.setValueMap(listOper.toArray(new String[0]));

                        //Attributes
                        List<MbeanAttrGrid> listAttr = new ArrayList<MbeanAttrGrid>();
                        MbeanAttr[] attrs = result.getAttrs();
                        for (MbeanAttr attr : attrs) {
                            MbeanAttrGrid attrGrid = new MbeanAttrGrid(attr.getName(), attr.getValue(), attr.getType(),
                                    attr.getDescription());
                            listAttr.add(attrGrid);
                        }
                        attrGrid.setData(listAttr.toArray(new MbeanAttrGrid[0]));
                        operGrid.setData(new MbeanOperGrid[0]);

                        //parameters
                        for (int i = 1; i <= 10; i++) {
                            FormItem item = form.getItem("param" + i);
                            item.setDisabled(true);
                        }
                    }

                });

            }
        });

        selectItemB.addChangedHandler(new ChangedHandler() {
            public void onChanged(ChangedEvent event) {
                final String selectedItem = (String) event.getValue();
                final String selectItemA = (String) form.getValue("selectItemA");

                mbeanService.getMBeanInfo(selectItemA, new AsyncCallback<MbeanInfo>() {
                    public void onFailure(Throwable caught) {
                        SC.say("MBeanServiceAsync.onFailure(Throwable)");
                    }
                    public void onSuccess(MbeanInfo result) {
                        List<MbeanOperGrid> listOper = new ArrayList<MbeanOperGrid>();
                        MbeanOper[] opers = result.getOpers();
                        for (MbeanOper oper : opers) {
                            if (!oper.getName().equals(selectedItem)) {
                                continue;
                            }

                            //Operation information
                            MbeanOperGrid operGrid = new MbeanOperGrid(oper.getInfo(), oper.getName(), oper
                                    .getDescription(), oper.getType());
                            listOper.add(operGrid);

                            //parameters information
                            MbeanParam[] params = oper.getParams();
                            for (MbeanParam pram : params) {
                                operGrid = new MbeanOperGrid(pram.getInfo(), pram.getName(), pram.getDescription(),
                                        pram.getType());
                                listOper.add(operGrid);
                            }
                        }
                        operGrid.setData(listOper.toArray(new MbeanOperGrid[0]));

                        //parameters
                        for (int i = 1; i <= 10; i++) {
                            FormItem item = form.getItem("param" + i);
                            item.setDisabled(i >= listOper.size());
                        }

                    }
                });
            }
        });

    }

}