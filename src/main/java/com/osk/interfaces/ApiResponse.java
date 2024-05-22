package com.osk.interfaces;

import org.jboss.resteasy.reactive.PartType;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;

public class ApiResponse {
    @FormParam("fileData")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] fileData;
    @FormParam("jsonData")
    @PartType(MediaType.TEXT_PLAIN)
    public String jsonData;

    public ApiResponse(byte[] fileData, String jsonData) {
        this.fileData = fileData;
        this.jsonData = jsonData; }

}
