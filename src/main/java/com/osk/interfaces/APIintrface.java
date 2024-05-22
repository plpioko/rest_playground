package com.osk.interfaces;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.client.api.ClientMultipartForm;

public interface APIintrface {
    
    @POST
    //@ClientQueryParam(name  = "key", value = "{getKeyValue}")
    //@ClientQueryParam(name  = "wh_id", value = "WIAW102")
    String jsonInterface(String json);

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    //@PartType(MediaType.TEXT_PLAIN) 
    String multipartInterface(ClientMultipartForm dataParts);
}
