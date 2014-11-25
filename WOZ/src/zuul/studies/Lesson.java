package zuul.studies;


import zuul.io.IO;

import java.io.IOException;

/**
 * Created by user on 13/11/14.
 */
public class Lesson {

    private int number;
    private boolean done;
    private boolean isPOO;

    private String body;

    public Lesson(boolean isPOO, int number){
        this.done = false;
        this.isPOO = isPOO;
        this.number = number;
        getLessonFromFile();
    }

    // Basic getters/setters //
    public boolean isPOO() {
        return isPOO;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getNumber() {
        return number;
    }
    // Basic getters/setters //

    // SKYPE = dorianblanc

    private void getLessonFromFile(){
        try {
            body = IO.getFromFile(number, IO.PossibleFiles.LESSON.getPath());
        } catch (IOException e){
            e.printStackTrace();
        }
        if(body == null){
            body = "ERROR";
        }
    }

    @Override
    public String toString(){
        return this.body+'\n';
    }

}
