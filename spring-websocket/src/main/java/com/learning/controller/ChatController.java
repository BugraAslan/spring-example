package com.learning.controller;

import com.learning.model.MessageModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat") // mesajın hangi route'dan alınacağı
    //@SendTo("/topic") // mesajının hangi route'a gönderileceği
    //@SendToUser // mesajın hangi user'a gönderileceği
    public void chat(@Payload MessageModel messageModel) {
        System.out.println(messageModel.toString());
        messagingTemplate.convertAndSend("/topic", messageModel); // hangi mesajın hangi route'a gönderileceği
    }
}
