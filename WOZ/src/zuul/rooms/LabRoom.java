package zuul.rooms;


import zuul.studies.Lab;

/**
 * Created by user on 13/11/14.
 */
public class LabRoom extends Room{

    private Lab lab;

    public LabRoom(String description){
        //TODO Ask dorian good implementation
        super(description);
    }


    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }
}
