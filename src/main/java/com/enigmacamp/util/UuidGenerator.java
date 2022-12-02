package com.enigmacamp.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidGenerator implements RandomStringGenerator {
    @Override
    public String random() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
