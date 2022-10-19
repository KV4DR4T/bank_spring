package com.example.bank_spring.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    USER(0,"USER"),
    MANAGER(1,"MANAGER"),
    CHIEF_MANAGER(2,"CHIEF_MANAGER");

    private String authority;
    private Integer id;

    Role(Integer id,String authority) {
        this.id=id;
        this.authority=authority;
    }

    public static Role roleFromId(Integer id ){
        for (Role o: Role.values()){
            if(o.id.compareTo(id)==0){
                return o;
            }
        }
        return null;
    }

    public String getAuthority() {
        return this.authority;
    }

    public Integer getId() {
        return id;
    }

}
