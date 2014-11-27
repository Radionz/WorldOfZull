package zuul.rooms;

/**
 * Created by user on 13/11/14.
 */
public class Corridor extends Room{

    private boolean isLite;

    /**
     * Create a rooms described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The rooms's description.
     */
    public Corridor(String description) {
        super(description);
        this.isLite = false;
    }

    public boolean isLite() {
        return isLite;
    }

    public void switchLight() {
        this.isLite = !this.isLite;
    }
}
