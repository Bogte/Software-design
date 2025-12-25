package raf.draft.dsw.model.structures;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.util.Objects;

@Getter
@Setter
public class Project extends DraftNodeComposite {
    private String autor;
    private String file;
    private boolean tabsOpenFlag=false;
    private boolean changed=true;
    public Project(String name,String autor,String file,DraftNode parent) {
        super(name,parent);
        this.autor=autor;
        this.file=file;
    }

    public Project() {
        super(null, null); // Pozivanje super konstruktora sa null vrednostima
    }
    @Override
    public void addChild(DraftNode child) {
        if (child != null &&  child instanceof Room){
            Room room = (Room) child;
            if (!this.getChildren().contains(room)){
                this.getChildren().add(room);
            }
        }
        else if (child != null &&  child instanceof Building)
        {
            Building building = (Building) child;
            if (!this.getChildren().contains(building)){
                this.getChildren().add(building);
            }
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Project project = (Project) obj;
        return Objects.equals(getName(), project.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }



}
