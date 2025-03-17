package com.biznex;

import com.biznex.utils.tglog.MyTelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
public class BiznexApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(BiznexApplication.class, args);
        applicationStartUpLog(app.getEnvironment());
        try {
            MyTelegramBot bot = app.getBean(MyTelegramBot.class);
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(bot);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private static void applicationStartUpLog(Environment env) {

        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) protocol = "https";

        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");

        if (StringUtils.isBlank(contextPath)) contextPath = "/";

        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("The host name could not be determined, using `localhost` as fallback");
        }

        if (log.isInfoEnabled()) {
            log.info(
                    """
                            
                                ----------------------------------------------------------
                                \tApplication '{}' is running! Access URLs:
                                \tLocal: \t\t{}://localhost:{}{}
                                \tExternal: \t{}://{}:{}{}
                                \tProfile(s): \t{}
                                ----------------------------------------------------------
                            """,
                    env.getProperty("spring.application.name"),
                    protocol,
                    serverPort,
                    contextPath,
                    protocol,
                    hostAddress,
                    serverPort,
                    contextPath,
                    env.getActiveProfiles()
            );
        }
    }

}
