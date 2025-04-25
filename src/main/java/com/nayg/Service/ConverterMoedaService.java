package com.nayg.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConverterMoedaService {

    public static Double converter(String json, String rate, Double value) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

        return rates.get(rate).getAsDouble() * value;
    }
}
