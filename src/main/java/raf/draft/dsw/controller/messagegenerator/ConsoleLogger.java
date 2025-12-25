package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.model.messages.Message;

import java.util.Date;

public class ConsoleLogger implements Logger{
    @Override
    public void updateMessage(String message) {
        System.out.println(message);
    }
}
