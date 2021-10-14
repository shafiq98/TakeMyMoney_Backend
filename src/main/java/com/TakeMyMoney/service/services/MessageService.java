package com.TakeMyMoney.service.services;

import com.TakeMyMoney.service.controllers.authentication.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.String.format;

@Slf4j
@Service
public class MessageService {

//    @Value("${public.key}")
//    private String publicKey;
//    @Value("${private.key}")
//    private String privateKey;

//    private PushService pushService;
//    private Map<UUID, Subscription> subscriptions = new HashMap<>();
    private Map<UUID, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();
//
//    @PostConstruct
//    private void init() throws GeneralSecurityException {
//        Security.addProvider(new BouncyCastleProvider());
//        pushService = new PushService(publicKey, privateKey);
//    }

//    public String getPublicKey() {
//        return publicKey;
//    }

//    public void subscribe(Subscription subscription) {
//        log.info(format("subscribing user %s", UserContext.getUsername()));
//        this.subscriptions.put(UserContext.getUserId(), subscription);
//    }

    public SseEmitter subscribe() {
        log.info(format("subscribing user %s", UserContext.getUsername()));
        SseEmitter sseEmitter = new SseEmitter();
        sseEmitterMap.put(UserContext.getUserId(), sseEmitter);
        return sseEmitter;
    }

    public void unsubscribe() {
        log.info(format("unsubscribing user %s", UserContext.getUsername()));
//        subscriptions.remove(UserContext.getUserId());
        sseEmitterMap.remove(UserContext.getUserId());
    }

//    public void sendNotification(String message, UUID userId) {
//        try {
//            Subscription subscription = subscriptions.get(userId);
//            pushService.send(new Notification(subscription, message));
//        } catch (GeneralSecurityException | IOException | JoseException | ExecutionException | InterruptedException e) {
//            log.error(format("Unable to send notifications to %s", UserContext.getUsername()));
//            e.printStackTrace();
//        }
//    }

    public void sendEvent(String message, UUID userId) {
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        SseEmitter sseEmitter = sseEmitterMap.get(userId);
        sseMvcExecutor.execute(() -> {
            try {
                SseEmitter.SseEventBuilder event = SseEmitter.event()
                        .data(message)
                        .id(UUID.randomUUID().toString())
                        .name("sse event - mvc -> " + LocalTime.now().toString());
                sseEmitter.send(event);
            } catch (Exception e) {
                log.error(format("Unable to send notifications to %s", UserContext.getUsername()));
                sseEmitter.completeWithError(e);
            }
        });
    }
}
