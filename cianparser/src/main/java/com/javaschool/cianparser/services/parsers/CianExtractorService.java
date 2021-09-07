package com.javaschool.cianparser.services.parsers;

import com.javaschool.cianparser.config.CianConnectionOptions;
import com.javaschool.cianparser.model.Advertisement;
import com.javaschool.cianparser.services.connectors.CianConnectionService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;


@Service
public class CianExtractorService implements ExtractorService {

    private final int CIAN_RETRY = 3;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    CianConnectionService connectService;

    @SneakyThrows
    public List<Advertisement> extract() {

        HttpURLConnection connection = connectService.connect();

        connection.setRequestMethod("GET");

        InputStream response = connection.getInputStream();

        JsonNode dataNode = objectMapper.readTree(response).get("data");

        if (dataNode == null) {
            return null;
        }

        List<Advertisement> advertisements = objectMapper.readValue(dataNode.toString(), new TypeReference<List<Advertisement>>(){});

        advertisements.stream()
                .forEach(ad -> ad.setUrl(CianConnectionOptions.FLAT_URL_PREFIX + Long.toString((ad.getId()))));

        return advertisements;
    }
}

