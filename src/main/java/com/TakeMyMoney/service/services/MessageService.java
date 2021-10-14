package com.TakeMyMoney.service.services;

import com.TakeMyMoney.service.controllers.authentication.UserContext;
import lombok.extern.slf4j.Slf4j;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Slf4j
@Service
public class MessageService {

    @Value("${public.key}")
    private String publicKey;
    @Value("${private.key}")
    private String privateKey;

    private PushService pushService;
    private Map<UUID, Subscription> subscriptions = new HashMap<>();

    @PostConstruct
    private void init() throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        pushService = new PushService(publicKey, privateKey);
    }

    public String getPublicKey(){
        return publicKey;
    }

    public void subscribe(Subscription subscription){
        log.info(format("subscribing user %s", UserContext.getUsername()));
        this.subscriptions.put(UserContext.getUserId(), subscription);
    }

    public void unsubscribe(){
        log.info(format("unsubscribing user %s", UserContext.getUsername()));
        subscriptions.remove(UserContext.getUserId());
    }

    public void sendNotification(String message, UUID userId){
        try {
            Subscription subscription = subscriptions.get(userId);
            pushService.send(new Notification(subscription, message));
        } catch (GeneralSecurityException | IOException | JoseException | ExecutionException | InterruptedException e) {
            log.error(format("Unable to send notifications to %s", UserContext.getUsername()));
            e.printStackTrace();
        }
    }

}
