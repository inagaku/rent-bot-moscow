package com.javaschool.restapiserver.request;

import com.javaschool.restapiserver.models.Advertisement;

public interface RestSender {
    void postRequest(Advertisement advertisement);
}
