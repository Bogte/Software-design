package raf.draft.dsw.controller.tabobserver;

import raf.draft.dsw.controller.observer.ISubscriber;

public interface TubIPublisher {
    public void addSubsriber(TubISubscriber subscriber);
    public void removeSubscriber(TubISubscriber subscriber);
    public void notifySubscribers();
}
