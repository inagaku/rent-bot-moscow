package com.javaschool.cianparser.services.parsers;

import com.javaschool.cianparser.model.Advertisement;

import java.util.List;


public interface ExtractorService {
    List<Advertisement> extract();
}
