package com.bulain.zk.validator;

import java.util.List;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;

public interface ValidatorManager {
    List<Validator> getValidators(String id, String command);
    void validate(String id, String command, ValidationContext ctx);
}
