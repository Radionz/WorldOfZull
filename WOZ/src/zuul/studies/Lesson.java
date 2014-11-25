package zuul.studies;


/**
 * Created by user on 13/11/14.
 */
public class Lesson {


    private boolean done;
    private boolean isPOO;

    public Lesson(boolean isPOO){
        this.done = false;
        this.isPOO = isPOO;
    }

    public boolean isPOO() {
        return isPOO;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    // SKYPE = dorianblanc
}
