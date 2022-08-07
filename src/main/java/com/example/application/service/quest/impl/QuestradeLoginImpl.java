package com.example.application.service.quest.impl;

import com.example.application.integrations.QustradeTokenClient;
import com.example.application.integrations.response.Token;
import com.example.application.service.quest.QuestradeLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public final class QuestradeLoginImpl implements QuestradeLoginService {

    private final Object lock = new Object();

    private final QustradeTokenClient tokenClient;

    private final String refreshToken;

    private Token accessToken;

    @Autowired
    public QuestradeLoginImpl(
        final QustradeTokenClient tokenClient,
        @Value("${questrade.refresh_token}") final String refreshToken
    ) {
        this.tokenClient = tokenClient;
        this.refreshToken = refreshToken;
    }

    @Override
    public Token getToken() {
        synchronized (this.lock) {
            if (this.accessToken == null) {
                this.accessToken = this.tokenClient.getToken("refresh_token", this.refreshToken);
            }
            return this.accessToken;
        }
    }
}
