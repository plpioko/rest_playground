package com.osk.interfaces;


import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;


public class ReqFilter implements ClientRequestFilter {
    private String bearerToken;

    public ReqFilter(String bearerToken) {
        if(bearerToken.startsWith("Bearer ")) {
            this.bearerToken = bearerToken;
        } else {
        this.bearerToken = "Bearer "+ bearerToken;
        }
    }

    @Override
    public void filter(ClientRequestContext requestContext)  {
       requestContext.getHeaders().add("Authorization", bearerToken);
       requestContext.getHeaders().add("key", "a7c9eeba-7e75-11ea-bc55-0242ac130003");
    }
}
