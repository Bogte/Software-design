package raf.draft.dsw.model.messages;

public class Message {
    private String messageContent;
    private MessageType messageType;
    private String timestamp;
    public Message(String messageContent, MessageType messageType,String timestamp) {
        this.messageContent = messageContent;
        this.messageType = messageType;
        this.timestamp = timestamp;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
