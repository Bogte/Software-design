package raf.draft.dsw.factories;

import raf.draft.dsw.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.controller.messagegenerator.FileLogger;
import raf.draft.dsw.controller.messagegenerator.Logger;

public class LoggerFactory{

    public Logger createMessage(String type) {

        if(type.toUpperCase().equals("CONSOLELOGGER")){
            return new ConsoleLogger();
        }
        else if(type.toUpperCase().equals("FILELOGGER")){
            return new FileLogger();
        }
        return null;
    }
}
