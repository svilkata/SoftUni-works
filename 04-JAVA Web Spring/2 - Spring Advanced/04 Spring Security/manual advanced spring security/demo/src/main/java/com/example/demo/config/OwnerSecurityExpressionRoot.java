package com.example.demo.config;

import com.example.demo.service.OfferService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public class OwnerSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private final Authentication authentication;
    private final OfferService offerService;
    private Object filterObject;
    private Object returnObject;

    public OwnerSecurityExpressionRoot(Authentication authentication, OfferService offerService) {
        super(authentication);
        this.authentication = authentication;
        this.offerService = offerService;
    }

    // This method is accessible in the @PreAuthorize annotation
    // When more complicated scenario for roles - admin can delete everything, but if we want the user who created the article to be able to delete it also - this is the way
    public boolean isOwner(UUID id) {
        if (authentication.getPrincipal() == null) {
            return false; //Not logged users cannot do anything
        }

        var userName = authentication.getName();

        return offerService.isOwner(userName, id);

    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
