package com.example.application.integrations;

import com.example.application.integrations.response.Token;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://login.questrade.com/oauth2/")
public interface QustradeTokenClient {

    @RequestMapping(method = RequestMethod.GET, value = "/token")
    Token getToken(
        @RequestParam(name = "grant_type") String grantType,
        @RequestParam(name = "refresh_token") String refreshToken
    );

}
