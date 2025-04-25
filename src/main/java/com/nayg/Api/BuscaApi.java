package com.nayg.Api;

import com.nayg.exception.ApiException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscaApi {
    private static final String API_KEY = System.getenv("API_KEY_EXCHANGE"); // variável de ambiente
    private static final String urlBase = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static String buscaApi(String base) throws ApiException {

        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new ApiException("A chave da API não está definida na variável de ambiente 'API_KEY_EXCHANGE'.");
        }

        String urlApi = urlBase + base;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlApi))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 200 ? response.body() : null;

        } catch (IOException | InterruptedException e) {
           throw new ApiException("Erro de conexção com a API " + e.getMessage());
        }
    }
}
