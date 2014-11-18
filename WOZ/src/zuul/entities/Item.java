package zuul.entities;

/**
 * Created by user on 13/11/14.
 */
public class Item {
    /**
     * Basic class test, has to be improved !
     */
    private final String name;

    public Item(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return getName();
    }

    @Override
    public boolean equals(Object object){
        if (object == null) { return false; }
        if (object == this) { return true; }
        if (((Item) object).name.equals(this.name)){ return true; }
        return false;
    }
}
