package com.example.wildman_backend.service;


import com.example.wildman_backend.domain.dto.ChatDto;
import com.example.wildman_backend.domain.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

    private final OpenAiChatModel chatClient;
    private final ChatMemory chatMemory;

    public ChatDto startChat() {
        return new ChatDto(UUID.randomUUID().toString());
    }

    public MessageDto sendMessage(String chatId, MessageDto messageDto) {

        Pattern pattern = Pattern.compile("[А-Яа-яЁё]+");
        Matcher matcher = pattern.matcher(messageDto.getText());

        if(matcher.find()) {
            return MessageDto.builder().mistakes("Сообщение не должно содержать кириллицу").build();
        }

        chatMemory.add(chatId, messageDto);
        String warningMessage = "Сейчас я отправлю тебе текст на английском языке. Ты должен проверить его на вс возможные правила английского языка. Если в тексте окажутся ошибки, ответь мне словом MISTAKE, а еще через перевод строки добавить краткое описание ошибок на русском языке. Если ошибок нет, отправь пустоту";


        MessageDto modifiedMessage = MessageDto.builder()
                .text(warningMessage + "\n\n" + messageDto.getText())
                .type(MessageType.USER)
                .build();

        Prompt mistakesPrompt = new Prompt(List.of(modifiedMessage));
        Prompt chatPrompt = new Prompt(chatMemory.get(chatId));

        String mistakesResponse = chatClient.call(mistakesPrompt).getResult().getOutput().getText();
        String mistakes = "";
        if(mistakesResponse!= null && mistakesResponse.contains("MISTAKE")) {
            mistakes = mistakesResponse.replaceAll("MISTAKE", "");
        }

        String response = chatClient.call(chatPrompt).getResult().getOutput().getText();
        MessageDto chatResponse = MessageDto.builder()
                .text(response)
                .type(MessageType.ASSISTANT)
                .mistakes(mistakes)
                .build();
        chatMemory.add(chatId, chatResponse);

        List<Message> history = chatMemory.get(chatId);
        chatResponse.setHistory(history);
        return chatResponse;
    }

    public void endChat(String chatId) {
        chatMemory.clear(chatId);
    }
}
