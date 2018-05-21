package com.bulain.zk.vm;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;

import com.bulain.common.model.Person;
import com.bulain.zk.pojo.TabnetView;

public class TabnetVm {
    private TabnetView tabnet = new TabnetView();

    @GlobalCommand
    @NotifyChange("client")
    public void client(@BindingParam("client") Person person) {
        if (person != null) {
            tabnet.setClientId(person.getId());
            tabnet.setClientName(person.getFirstName());
            tabnet.setClientDesc(person.getLastName());
        }
    }

    @GlobalCommand
    @NotifyChange("supplier")
    public void supplier(@BindingParam("supplier") Person person) {
        if (person != null) {
            tabnet.setSupplierId(person.getId());
            tabnet.setSupplierName(person.getFirstName());
            tabnet.setSupplierDesc(person.getLastName());
        }
    }

    @Command
    public void save() {

    }

    public TabnetView getTabnet() {
        return tabnet;
    }

    public void setTabnet(TabnetView tabnet) {
        this.tabnet = tabnet;
    }

}
