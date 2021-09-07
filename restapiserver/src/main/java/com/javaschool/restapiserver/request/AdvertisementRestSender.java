package com.javaschool.restapiserver.request;

import com.javaschool.restapiserver.models.Advertisement;
import com.javaschool.restapiserver.models.AdvertisementWrapper;
import com.javaschool.restapiserver.models.Person;
import com.javaschool.restapiserver.services.AdvertisementWrapperService;
import com.javaschool.restapiserver.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdvertisementRestSender implements RestSender {

    @Autowired
    PersonService personService;

    @Autowired
    AdvertisementWrapperService advertisementWrapperService;

    @Override
    public void postRequest(Advertisement advertisement) {
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<AdvertisementWrapper> requestBody = new HttpEntity<>(advertisementWrapperService.createWrapper(advertisement), headers);

        System.out.println("advertisement = " + advertisement);

        // Send request with POST method.
        Object e = restTemplate.postForObject("http://telegram-bot/user/advertisement", requestBody, Person.class);
        if (e != null) {
            System.out.println(e);
        }
    }
}
