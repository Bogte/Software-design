package raf.draft.dsw.core;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.commands.CommandManager;
import raf.draft.dsw.controller.serializer.Serializer;
import raf.draft.dsw.factories.LoggerFactory;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.repository.DraftExplorerImplementation;
import raf.draft.dsw.model.repository.DraftRepository;

@Getter
@Setter
public class ApplicationFramework {
    private static ApplicationFramework instanceApplicationFramework;
    private MessageGenerator messageGenerator;
    private LoggerFactory loggerFactory;
    private DraftRepository draftRepository;
    private CommandManager commandManager;
    private Serializer serializer;

    private ApplicationFramework(){
    }
    public static ApplicationFramework getInstance()
    {
        if (instanceApplicationFramework==null)
            instanceApplicationFramework=new ApplicationFramework();
        return instanceApplicationFramework;
    }

    public void initialize(){
        messageGenerator = new MessageGenerator();
        draftRepository=new DraftExplorerImplementation();
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
        messageGenerator.addSubsriber(MainFrame.getInstance());
        LoggerFactory lf = new LoggerFactory();
        commandManager=new CommandManager();
        serializer=new Serializer();
        messageGenerator.addSubsriber(lf.createMessage("FILELOGGER"));
        messageGenerator.addSubsriber(lf.createMessage("CONSOLELOGGER"));
    }
}
