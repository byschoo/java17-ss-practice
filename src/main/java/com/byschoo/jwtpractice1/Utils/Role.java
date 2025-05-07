package com.byschoo.jwtpractice1.Utils;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    ROLE_SUPER(Arrays.asList(Permission.values())),
    ROLE_ADMIN(Arrays.asList(Permission.READ_PRODUCT, Permission.READ_USER, Permission.SAVE_PRODUCT, Permission.SAVE_USER, Permission.MODIFY_PRODUCT, Permission.MODIFY_USER, Permission.REFRESH_TOKEN)),
    ROLE_USER(Arrays.asList(Permission.READ_PRODUCT, Permission.READ_USER, Permission.SAVE_PRODUCT, Permission.SAVE_USER, Permission.REFRESH_TOKEN)),
    ROLE_INVITED(Arrays.asList(Permission.READ_PRODUCT, Permission.READ_USER, Permission.REFRESH_TOKEN)),
    ROLE_RESTRICTED_USER(Arrays.asList(Permission.READ_PRODUCT, Permission.READ_USER, Permission.SAVE_PRODUCT, Permission.SAVE_USER)),
    ROLE_RESTRICTED_INVITED(Arrays.asList(Permission.READ_PRODUCT, Permission.READ_USER));

    private List<Permission> permissions;

}
