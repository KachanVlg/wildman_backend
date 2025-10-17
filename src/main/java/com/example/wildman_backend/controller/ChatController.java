package com.example.wildman_backend.controller;

import com.example.wildman_backend.domain.dto.ChatDto;
import com.example.wildman_backend.domain.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {

    private final OpenAiChatModel chatClient;
    private final ChatMemory chatMemory;


    @PostMapping
    public ChatDto createChat(){
        return new ChatDto(UUID.randomUUID().toString());
    }

    @PostMapping("/{id}/message")
    public MessageDto processMessage(@PathVariable("id") String chatId,
                                     @RequestBody MessageDto dto){
        dto.setText("Сгенерируй ответ на данное сообщение, как будто ты ведешь живой диалог, сгенерируй на английском " + dto.getText());
        chatMemory.add(chatId, dto);
        Prompt prompt = new Prompt(chatMemory.get(chatId));
        String response = chatClient.call(prompt).getResult().getOutput().getText();
        MessageDto chatResponse = MessageDto.builder()
                .text(response)
                .type(MessageType.ASSISTANT)
                .build();
        chatMemory.add(chatId, dto);
        return chatResponse;
    }



}
