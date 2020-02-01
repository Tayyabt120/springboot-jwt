package com.tab.springjwt.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TAYYAB
 */
@RestController
public class SampleController {

    @GetMapping("sample")
    public String sampleApi() {
        return "This is sample Rest API for JWT example..!!!";
    }
}
