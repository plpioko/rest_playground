package com.osk.service;

import java.util.Base64;
import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.jboss.resteasy.reactive.client.api.ClientMultipartForm;

import com.osk.interfaces.APIintrface;
import com.osk.interfaces.ReqFilter;

import jakarta.ws.rs.Produces;

@ApplicationScoped
@Path("")
public class APIService {
    private static final String BASE_URL = "https://webhook.site/0a933d43-d41e-4965-addf-d886e8dc9b5b";
    
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOnea() {
        String token = "Bearer eyJ0eXAiOiAiSldUIiwgImFsZyI6ICJSUzI1NiIsICJraWQiOiAiR2ttMXdlVjBQSF95YVZGZzZQU0tnc0VIYmlYRGlEek00Y3NxMFlTTTVDbyJ9.eyJhdWQiOiAiWjdjZExNeXZXZGRLQmRKeG4zelNnVWZLMldnZ01vcUxBdHV3WElLaiIsICJpYXQiOiAxNzE2MjgyMzk4LCAiYXRfaGFzaCI6ICJCOXZhM3l4dTh4cXE3Tm1EdUJOVG53IiwgInN1YiI6ICJvc2dhZG1pbiIsICJlbWFpbCI6ICJwb3J0YWwtc3VwcG9ydEBvcGVuc2t5Z3JvdXAuY29tIiwgImZpcnN0X25hbWUiOiAiT1NHIiwgImxhc3RfbmFtZSI6ICJBZG1pbiIsICJuYW1lIjogIk9TRyBBZG1pbiIsICJwcmVmZXJyZWRfdXNlcm5hbWUiOiAib3NnYWRtaW4iLCAidGlkIjogImxvY2FsaWFtIiwgImFwcCI6ICJlY2IiLCAic2NvcGUiOiBbImVjYiJdLCAiaXNzIjogImh0dHA6Ly9sb2NhbGlhbS5kb2NrZXIuZ2F0ZXdheTo4MDAwL2lhbS9vIiwgImV4cCI6IDE3MTYzMTgzOTgsICJhdXRoX3RpbWUiOiAxNzE2MjgyMzk4LCAianRpIjogIjkzMzhjNDkxLTMzM2ItNGVhMy1hMTUwLTI1ODU0MTA5NGYwMiJ9.Efx5DO_CgDig32y-KNz6f3lbHoFZwyyqczRfw7ijh962IvrxDXEbtQGoY41NI2HohL9ZOY1cnzCe9qMSC2PyyIiwG3qQjp2R9wc8EDnCGpQFHSvYloKWSPA1ISs2CJnoaXL_-gKTYaoDBuePHI_MKr77TCprIjfrT4G52erCaZnW5QbN-1Brg7-ZMCTPbclvG9YbcxfIq4RTU05cEEbpbJO-uPM5VldG409Q5xyWUIf-JGEiFmpzAoWJZ6DmQmvyFIYISAq5N-yXS13Y1gjLKSnTOz64grPJdeFCU_VrPmqo8hhjeLME3_jiN6D67x1uVvvixUJc5rXyYsYATKb73w6N8k5wq_mtCVRfXj0nNMnCDqT_6yKaUT2o3wuTT6uwtoARlz1-VjenlSUnjYM16R7sZM7WKOWM5km830LTHNkjin86uCsLNSOUpKaatcqNpfT-jkVimo9_KXf-Yh5CYvw4oxSVFjLh5w977qnUFb6ToRhRwjz0Qfqd_-yUUKSU36L_RrRm9C8NRGuTROhuqhAymdV1_PttfcSQPW2vjYurbElTmMwcXBdDWjKTXL_wWfEJw20ju3dQ2ULL0KVQPaft_rRbu3K5437OWo5nK3T3djMHfEd8gidxEj_4tUrJSk8Pm7AYHWoYLI0bnTzt_lOWQUgNf-94KHmCccYlF18";

        APIintrface apiinterface = QuarkusRestClientBuilder.newBuilder().baseUri(URI.create(BASE_URL)).register(new ReqFilter(token)).build(APIintrface.class);
        String k = "";
        try {
            // JSONObject json = new JSONObject();
            // json.put("wh_id", "WIAW102");
            // json.put("key", "a7c9eeba-7e75-11ea-bc55-0242ac130003");
            
            // k = apiinterface.jsonInterface(json.toString());
            //String fileData = readFileData("invoice.pdf"); // Replace with actual method to get file data
            String jsonData = "{\"wh_id\": \"WIAW102\", \"key\": \"a7c9eeba-7e75-11ea-bc55-0242ac130003\"}";
            ClientMultipartForm multiPartForm = ClientMultipartForm.create();
            multiPartForm.attribute("json_data", jsonData, "json_data");
            //multiPartForm.attribute("file", fileData, "file");
            multiPartForm.binaryFileUpload("pdffile", "pdffile.pdf", "src/main/resources/code64.cdf" , MediaType.APPLICATION_OCTET_STREAM);
            k = apiinterface.multipartInterface(multiPartForm);
           

        } catch (Exception e) {
            // Log the error with the response details
            e.printStackTrace();
            //throw new RuntimeException("Failed to fetch data from API", e);
            k = "Failed to fetch data from API";
        }
        return k;
        //return List.of(new ApiResponse("1", "one", APIService.BASE_URL)); 
    }
        private String readFileData(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IOException("File not found: " + fileName);
        }
        byte[] fileContent = inputStream.readAllBytes();
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
