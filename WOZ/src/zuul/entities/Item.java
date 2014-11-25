package zuul.entities;

/**
 * Created by user on 13/11/14.
 */
public class Item {
    /**
     * Basic class test, has to be improved !
     */
    private final String name;
    private int energy;

    public Item(String name, int energy){
        this.name = name;
        this.energy = energy;
    }

    public Item(String name){
        this.name = name;
        this.energy = 0;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public void use() {
        //TODO Dorian method
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
