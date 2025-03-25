package edu.matc.persistence;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.rates.ExchangeRates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRatesDao {

    public ExchangeRates getExchangeRates(String baseCurrency) throws Exception {
        String apiUrl = "https://open.er-api.com/v6/latest/" + baseCurrency;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseJson = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            responseJson.append(line);
        }
        in.close();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseJson.toString());
        JsonNode ratesNode = root.get("rates");

        return mapper.treeToValue(ratesNode, ExchangeRates.class);
    }
}
