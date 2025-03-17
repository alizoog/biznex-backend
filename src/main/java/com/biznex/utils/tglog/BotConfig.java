package com.biznex.utils.tglog;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class BotConfig {

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;
}
