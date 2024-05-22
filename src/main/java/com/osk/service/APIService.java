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
        String token = "Bearer xxx";

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
