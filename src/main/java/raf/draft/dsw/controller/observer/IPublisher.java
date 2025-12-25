package raf.draft.dsw.controller.observer;

import raf.draft.dsw.model.messages.Message;

public interface IPublisher {
    public void addSubsriber(ISubscriber subscriber);
    public void removeSubscriber(ISubscriber subscriber);
    public void sendMessage(String message);
}
