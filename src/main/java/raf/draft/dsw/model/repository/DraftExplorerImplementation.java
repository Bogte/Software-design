package raf.draft.dsw.model.repository;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.ProjectExplorer;

public class DraftExplorerImplementation implements DraftRepository{

    ProjectExplorer projectExplorer;

    public DraftExplorerImplementation() {
        this.projectExplorer = new ProjectExplorer();
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

}
