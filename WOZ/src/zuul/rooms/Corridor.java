package zuul.rooms;

/**
 * Created by user on 13/11/14.
 */
public class Corridor extends Room{

    private boolean light;

    /**
     * Create a rooms described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The rooms's description.
     */
    public Corridor(String description) {
        super(description);
        this.light = false;
    }

    public boolean isLite() {
        return light;
    }

    public void switchLight() {
        this.light = !this.light;
    }
}
