package raf.draft.dsw.controller.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Getter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.nodes.RoomElement;
import raf.draft.dsw.model.repository.DraftExplorerImplementation;
import raf.draft.dsw.model.repository.DraftRepository;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Serializer {
    private final ObjectMapper objectMapper;
    @Getter
    private final String customExtension = ".txt";
    @Getter
    private final String customExtensionJSON = ".json";

    public Serializer() {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        objectMapper.registerModule(module);
    }

    public void saveTemplate(Object data, File file) throws IOException {
        File fileWithExtension = ensureCustomExtensionJSON(file);
        objectMapper.writeValue(fileWithExtension, data);
    }

    public void save(Object data, File file) throws IOException {
        File fileWithExtension = ensureCustomExtension(file);
        objectMapper.writeValue(fileWithExtension, data);
    }

    public <T> T load(File file, Class<T> clazz) throws IOException {
        T data = objectMapper.readValue(file, clazz);

        if (data instanceof DraftNodeComposite) {
            linkParentChildRelationships((DraftNodeComposite) data);
        }

        return data;
    }

    public Room loadJSON(File file) throws IOException{
        Room room = objectMapper.readValue(file, Room.class);
        room.setParent(null);
        for (DraftNode element : room.getChildren()) {
            element.setParent(null);
        }
        return room;
    }

    private File ensureCustomExtensionJSON(File file) {
        if (!file.getName().endsWith(customExtensionJSON)) {
            return new File(file.getAbsolutePath() + customExtensionJSON);
        }
        return file;
    }

    private File ensureCustomExtension(File file) {
        if (!file.getName().endsWith(customExtension)) {
            return new File(file.getAbsolutePath() + customExtension);
        }
        return file;
    }
    private void linkParentChildRelationships(DraftNodeComposite root) {
        Map<UUID, DraftNodeComposite> nodeMap = new HashMap<>();

        buildNodeMap(root, nodeMap);

        setParentReferences(root, nodeMap);
    }
    private void buildNodeMap(DraftNodeComposite node, Map<UUID, DraftNodeComposite> nodeMap) {
        nodeMap.put(node.getId(), node);

        for (DraftNode child : node.getChildren()) {
            if (child instanceof DraftNodeComposite) {
                buildNodeMap((DraftNodeComposite) child, nodeMap);
            }
        }
    }
    private void setParentReferences(DraftNodeComposite node, Map<UUID, DraftNodeComposite> nodeMap) {
        for (DraftNode child : node.getChildren()) {
            if (child.getParentId() != null) {
                DraftNodeComposite parent = nodeMap.get(child.getParentId());
                child.setParent(parent);
            }

            if (child instanceof DraftNodeComposite) {
                setParentReferences((DraftNodeComposite) child, nodeMap);
            }
        }
    }
}
