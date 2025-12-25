package raf.draft.dsw.model.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public abstract class DraftNodeComposite extends DraftNode {

    @JsonProperty("roomElements")
    public List<DraftNode> children;

    public DraftNodeComposite(String name,DraftNode parent) {
        super(name,parent);
        children=new ArrayList<>();
    }

    public abstract void addChild(DraftNode child);

}
