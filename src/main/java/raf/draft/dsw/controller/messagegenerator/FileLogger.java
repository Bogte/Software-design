package raf.draft.dsw.controller.messagegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger{
    File f=new File("src/main/resources/log.txt");
    @Override
    public void updateMessage(String message) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
            bw.write(message);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
