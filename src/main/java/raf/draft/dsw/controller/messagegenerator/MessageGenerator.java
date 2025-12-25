package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.messages.Message;
import raf.draft.dsw.model.messages.MessageType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageGenerator implements IPublisher {
    private List<ISubscriber> subscribers;
    public MessageGenerator() {
        this.subscribers =new ArrayList<>();
    }

    public String getTimestamp() {
        SimpleDateFormat timestamp;
        timestamp= new SimpleDateFormat("dd.MM.yyyy. HH:mm");
        return timestamp.format(new Date());
    }
    public void newMassage(MessageType messageType, String message) {
            StringBuilder sb = new StringBuilder();
            sb.append("[" + messageType + "] ");
            sb.append("[" + getTimestamp() + "] ");
            sb.append("[" + message + "]");
            sendMessage(sb.toString());
    }
    @Override
    public void addSubsriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }
    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }
    @Override
    public void sendMessage(String message) {
        for (ISubscriber subscriber: subscribers)
        {
            subscriber.updateMessage(message);
        }
    }


}


