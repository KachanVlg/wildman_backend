package com.example.wildman_backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;

import java.util.List;
import java.util.Map;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto implements Message {
    private String text;
    private MessageType type = MessageType.USER;
    private String mistakes;
    private List<Message> history;

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

    public String getMistakes() {return mistakes;}

    public List<Message> getHistory() {
        return history;
    }

    public void setHistory(List<Message> history) {
        this.history = history;
    }
}
