package com.phonebook.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FonoApiClient {

    public HttpRequest getRq(String url) throws URISyntaxException {

        URI targetURI = new URI(url);
        return HttpRequest.newBuilder()
                .uri(targetURI)
                .GET()
                .build();
    }

    public HttpResponse doCall(String url) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        return httpClient.send(getRq(url), HttpResponse.BodyHandlers.ofString());
    }
}
