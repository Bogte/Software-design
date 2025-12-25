package raf.draft.dsw.controller.states;

import lombok.Getter;
import lombok.Setter;

public class StateManager {
    @Getter
    @Setter
    private State currState;
    private EditRoomState editRoomState;
    private AddState addState;
    private SelectState selectState;
    private ResizeState resizeState;
    private DeleteState deleteState;
    private EditState editState;
    private MoveState moveState;
    private ZoomState zoomState;

    public StateManager() {
        initStates();
    }

    private void initStates()
    {
        editRoomState=new EditRoomState();
        addState=new AddState();
        selectState=new SelectState();
        resizeState=new ResizeState();
        deleteState=new DeleteState();
        editState=new EditState();
        moveState=new MoveState();
        zoomState=new ZoomState();
        currState=editRoomState;
    }
    public void setEditRoomState()
    {
        currState=editRoomState;
    }
    public void setAddState()
    {
        currState=addState;
    }
    public void setSelectState()
    {
        currState=selectState;
    }
    public void setResizeState()
    {
        currState=resizeState;
    }
    public void setDeleteState()
    {
        currState=deleteState;
    }
    public void setEditState(){currState=editState;}
    public void setMoveState(){currState=moveState;}
    public void setZoomState(){currState=zoomState;}

}
