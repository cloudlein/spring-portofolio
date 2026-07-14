package com.my.portofolio.service.impl;

import com.my.portofolio.service.TokenBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenBlacklistServiceImpl implements TokenBlacklistService {

    private final StringRedisTemplate stringRedisTemplate;
    private static final String TOKEN_BLACKLIST_PREFIX = "blacklist:jwt:";


    @Override
    public void addToBlacklist(String token, long expirationTimeMillis) {
        String key = TOKEN_BLACKLIST_PREFIX + token;
        String value = "blacklisted";
        stringRedisTemplate.opsForValue().set(key, value, Duration.ofMillis(expirationTimeMillis));
    }

    @Override
    public boolean isBlacklisted(String token) {
        String key = TOKEN_BLACKLIST_PREFIX + token;
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        return Boolean.TRUE.equals(hasKey);
    }
}
