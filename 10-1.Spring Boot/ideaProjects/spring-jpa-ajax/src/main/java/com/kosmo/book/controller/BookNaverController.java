package com.kosmo.book.controller;


import com.kosmo.book.util.NaverBookProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@PropertySource("classpath:apiKey.properties")      // resources/apiKey.properties를 찾음
public class BookNaverController {

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    @Autowired
    private NaverBookProxy proxy;


    @GetMapping("/api/naver")
    public ResponseEntity<String> getNaverBookData(@RequestParam(defaultValue = "ajax") String query,
                                                   @RequestParam(defaultValue = "1") int start,
                                                   @RequestParam(defaultValue = "12") int display){


        log.info("query===={}, start===={}, display===={}", query, start, display);
//        log.info("clientId==============={}", clientId);
//        log.info("clientSecret==========={}", clientSecret);
        String str = proxy.getData(clientId,clientSecret,query,display,start);
        log.info("str=============={}", str);
        return ResponseEntity.ok(str);
    }

}
