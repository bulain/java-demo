package com.bulain.activiti.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.bulain.activiti.model.Account;
import com.bulain.activiti.pojo.AccountSearch;
import com.bulain.activiti.pojo.AccountView;
import com.bulain.activiti.pojo.Item;
import com.bulain.activiti.pojo.ItemConst;
import com.bulain.activiti.service.AccountService;
import com.bulain.activiti.service.ReferanceService;
import com.bulain.common.controller.PageSupportActionSupport;

public class AccountAction extends PageSupportActionSupport {
    private static final long serialVersionUID = 2094256969236681808L;
    private static final String TEXT_ACCOUNT_MODEL = "account.model";
    private static final String TEXT_ACCOUNT_NAME = "account.name";
    private static final Logger LOG = LoggerFactory.getLogger(AccountAction.class);

    private Long id;
    private AccountSearch search;
    private Account account;
    private List<AccountView> listAccount;

    private transient AccountService accountService;
    private transient ReferanceService referanceService;

    private List<Item> listReferanceAccountStatus;
    private List<Item> listReferanceCurrency;
    private List<Item> listReferanceCashAccountType;
    private List<Item> listReferanceFrequency;
    private List<Item> listReferanceBoolean;

    public String list() {
        search = (AccountSearch) getSearchFromSession(AccountSearch.class, search);
        page = getPageFromSession();

        List<Account> list = accountService.page(search, page);
        listAccount = formatList(list);

        putSearchToSession(AccountSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        account = new Account();
        return SUCCESS;
    }

    public String create() {
        try {
            accountService.insert(account);
            String msg = getText("info.create", new String[]{getText(TEXT_ACCOUNT_MODEL)});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("create()", e);
            String msg = getText("error.create", new String[]{getText(TEXT_ACCOUNT_MODEL)});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }

    public String show() {
        account = accountService.get(id);
        account = formatItem(account);
        return SUCCESS;
    }

    public String edit() {
        account = accountService.get(id);
        return SUCCESS;
    }

    public String update() {
        try {
            accountService.update(account, true);
            String msg = getText("common.updateInfo", new String[]{TEXT_ACCOUNT_MODEL});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("update()", e);
            String msg = getText("common.updateError", new String[]{TEXT_ACCOUNT_MODEL});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }

    public String destroy() {
        try {
            accountService.delete(id);
            String msg = getText("common.deleteInfo", new String[]{TEXT_ACCOUNT_MODEL});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("destroy()", e);
            String msg = getText("common.deleteError", new String[]{TEXT_ACCOUNT_MODEL});
            addActionError(msg);
            return ERROR;
        }

        return SUCCESS;
    }

    public void prepareList() {
        listReferanceAccountStatus = referanceService.findItem(ItemConst.NAME_ACCOUNT_STATUS, getLanguage());
        listReferanceCurrency = referanceService.findItem(ItemConst.NAME_CURRENCY, getLanguage());
        listReferanceCashAccountType = referanceService.findItem(ItemConst.NAME_CASH_ACCOUNT_TYPE, getLanguage());
        listReferanceFrequency = referanceService.findItem(ItemConst.NAME_FREQUENCY, getLanguage());
        listReferanceBoolean = referanceService.findItem(ItemConst.NAME_BOOLEAN, getLanguage());
    }

    public void prepareNewn() {
        prepareList();
    }

    public void prepareEdit() {
        prepareList();
    }

    public void prepareCreate() {
        prepareList();
    }

    public void prepareUpdate() {
        prepareList();
        if (id != null) {
            account = accountService.get(id);
        }
    }

    public void validateCreate() {
        long cnt = accountService.countByDuplicate(account);
        if (cnt > 0) {
            String msg = getText("validate.duplicate.1", new String[]{getText(TEXT_ACCOUNT_NAME)});
            addActionError(msg);
        }
    }

    public void validateUpdate() {
        validateCreate();
    }

    protected List<AccountView> formatList(List<Account> list) {
        List<AccountView> listView = new ArrayList<AccountView>();
        for (Account lgn : list) {
            listView.add(formatItem(lgn));
        }
        return listView;
    }

    protected AccountView formatItem(Account account) {
        AccountView view = new AccountView();
        BeanUtils.copyProperties(account, view);

        view.setStatusName(referanceService.getText(ItemConst.NAME_ACCOUNT_STATUS, account.getStatus(), getLanguage()));
        view.setCurrencyName(referanceService.getText(ItemConst.NAME_CURRENCY, account.getCurrency(), getLanguage()));
        view.setTypeName(referanceService.getText(ItemConst.NAME_CASH_ACCOUNT_TYPE, account.getType(), getLanguage()));
        view.setStatementCycleName(referanceService.getText(ItemConst.NAME_FREQUENCY, account.getStatementCycle(),
                getLanguage()));
        view.setUrgencyFlagName(referanceService.getText(ItemConst.NAME_BOOLEAN, account.getUrgencyFlag(),
                getLanguage()));

        return view;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountSearch getSearch() {
        return search;
    }

    public void setSearch(AccountSearch search) {
        this.search = search;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<AccountView> getListAccount() {
        return listAccount;
    }

    public void setListAccount(List<AccountView> listAccount) {
        this.listAccount = listAccount;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public List<Item> getListReferanceAccountStatus() {
        return listReferanceAccountStatus;
    }

    public List<Item> getListReferanceCurrency() {
        return listReferanceCurrency;
    }

    public List<Item> getListReferanceCashAccountType() {
        return listReferanceCashAccountType;
    }

    public List<Item> getListReferanceFrequency() {
        return listReferanceFrequency;
    }

    public void setReferanceService(ReferanceService referanceService) {
        this.referanceService = referanceService;
    }

    public List<Item> getListReferanceBoolean() {
        return listReferanceBoolean;
    }
}
