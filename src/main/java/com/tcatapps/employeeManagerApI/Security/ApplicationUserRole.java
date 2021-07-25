package com.tcatapps.employeeManagerApI.Security;

import com.google.common.collect.Sets;
import java.util.Set;

import static com.tcatapps.employeeManagerApI.Security.ApplicationUserPermission.EMPLOYEE_READ;
import static com.tcatapps.employeeManagerApI.Security.ApplicationUserPermission.EMPLOYEE_WRITE;

public enum ApplicationUserRole {

    //empty hashset because the employee has no permissions, the empty hashset comes from guava project (dependency)
    EMPLOYEE (Sets.newHashSet()),
    ADMIN(Sets.newHashSet(EMPLOYEE_READ, EMPLOYEE_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(EMPLOYEE_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
