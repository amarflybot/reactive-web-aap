package com.example.reactivewebaap.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RequestMapping
@RestController
public class WebHookResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookResource.class);

    private static final String SIGNATURE = "intuit-signature";

    @PostMapping("/webhook")
    @ResponseBody
    public ResponseEntity<ResponseWrapper> webhooks(@RequestBody String payload) {


        // if payload is empty, don't do anything
        if (!StringUtils.hasText(payload)) {
            new ResponseEntity<>(new ResponseWrapper("Success"), HttpStatus.OK);
        }

        LOGGER.info("request recieved ");


        return new ResponseEntity<>(new ResponseWrapper("Success"), HttpStatus.OK);
    }

}
