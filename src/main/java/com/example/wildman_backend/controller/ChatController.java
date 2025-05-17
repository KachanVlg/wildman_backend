package com.example.wildman_backend.controller;

import com.example.wildman_backend.domain.dto.ChatDto;
import com.example.wildman_backend.domain.dto.MessageDto;
import com.example.wildman_backend.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ChatDto startChat(){
        return chatService.startChat();
    }

    @PostMapping("/{id}/message")
    public MessageDto processMessage(@PathVariable("id") String chatId,
                                     @RequestBody MessageDto dto){
        return chatService.sendMessage(chatId, dto);
    }

    @PostMapping("{id}/end_chat")
    public void endChat(@PathVariable("id") String chatId) {
        chatService.endChat(chatId);
    }
}
