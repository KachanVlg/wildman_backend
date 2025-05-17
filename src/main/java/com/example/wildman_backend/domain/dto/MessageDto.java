package com.example.wildman_backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;

import java.util.Map;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto implements Message {
    private String text;
    private MessageType type = MessageType.USER;

    @Override
    public MessageType getMessageType() {
        return type;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Map<String, Object> getMetadata() {
        return Map.of();
    }
}
