package com.phonebook.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonebook.exceptions.ApiNotFoundException;
import com.phonebook.exceptions.InternalException;
import com.phonebook.responses.FonoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class FonoService {
    private static final String TOKEN_URL = "https://fonoapi.freshpixl.com/token/generate";
    private static final String DETAILS_URL = "https://fonoapi.freshpixl.com/v1/getdevice";
    private FonoApiClient client = new FonoApiClient();
    public FonoService() {}

    private String getToken() throws ApiNotFoundException, InternalException {
        HttpResponse rs;
        try {
            rs = client.doCall(TOKEN_URL);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
        if (rs.statusCode() == HttpStatus.OK.value())
            return rs.toString();
        throw new ApiNotFoundException("Can't get token.", rs.statusCode());
    }

    private HttpResponse populateData(Optional<String> brand, String device, int position) throws ApiNotFoundException, InternalException {
        String token = getToken();
        String target = new StringBuilder(DETAILS_URL)
                .append("?token=")
                .append(token)
                .append("&brand=")
                .append(brand)
                .append("&device=")
                .append(device)
                .append("&position=")
                .append(position)
                .toString();
        HttpResponse rs;
        try {
            rs = client.doCall(target);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
        return rs;
    }
    public FonoResponse exposeData(Optional<String> brand, String device) throws ApiNotFoundException, InternalException {
        HttpResponse rs = populateData(brand, device, 0);
        if (rs.statusCode() == HttpStatus.OK.value()) {
            try {
                FonoResponse fonoRs = new ObjectMapper()
                        .readerFor(FonoResponse.class)
                        .readValue(rs.body().toString());
                return fonoRs;
            } catch (JsonProcessingException jpe) {
                throw new InternalException("Can't parse JSON from remote source");
            }
        } else {
            throw new ApiNotFoundException("Can't expose data from fonoapi", rs.statusCode());
        }

    }

}
