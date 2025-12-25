package raf.draft.dsw.controller.states.StateObserver;

import raf.draft.dsw.controller.observer.ISubscriber;

public interface SIPublisher {
    public void addSubsriber(SISubscriber subscriber);
    public void removeSubscriber(SISubscriber subscriber);
    public void informSubscribers();
}
