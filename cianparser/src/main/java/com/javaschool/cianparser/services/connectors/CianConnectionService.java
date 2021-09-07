package com.javaschool.cianparser.services.connectors;

import com.javaschool.cianparser.config.CianConnectionOptions;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CianConnectionService implements ConnectionService{

    @Override
    @SneakyThrows
    public HttpURLConnection connect() {
        StringBuilder fullURL = new StringBuilder();

        fullURL.append(CianConnectionOptions.URL + "?")
                .append("login=" + CianConnectionOptions.LOGIN)
                .append("&token=" + CianConnectionOptions.TOKEN)
                .append("&category_id=" + CianConnectionOptions.CATEGORY_ID)
                .append("&region_id=" + CianConnectionOptions.REGION_ID)
                .append("&deal_id=" + CianConnectionOptions.DEAL_ID)
                .append("&last_s=" + CianConnectionOptions.LAST_S)
                .append("&limit=" + "10");

        URL url = new URL(fullURL.toString());
        
        return (HttpURLConnection) url.openConnection();
    }
}
