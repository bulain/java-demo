package com.bulain.shiro;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;

public interface ShiroService {
    @RequiresAuthentication
    void callByAuth();
    
    @RequiresRoles({"role1"})
    void callByRole();
    
    @RequiresPermissions({"a1:b1"})
    void callByPerm();
}
