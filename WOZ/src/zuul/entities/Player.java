package zuul.entities;

import zuul.rooms.Room;

import java.util.ArrayList;

/**
 * Created by user on 13/11/14.
 */
public class Player {

    private final String name;
    private ArrayList<Item> inventory;
    private int energy;

    public Player(String name, Item item){
        this.name = name;
        this.inventory = new ArrayList<>(100);
        this.inventory.add(item);
    }

    /* Basic getters / setters */
    public String getName() {
        return name;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Method allowing the player to pickup an item on the ground
     * and to put it in is own inventory
     * @param item Item being pick up
     * @return If the item has successfully been added or not
     */
    public boolean pickUp(Room room,Item item){
        if(room != null && item != null){
            room.getItemsList().remove(item);
            return this.inventory.add(item);
        }else{
            return false;
        }
    }

    /**
     * Drop one player's Item from its inventory in the current room
     * @param room The room the player is currently settled
     * @param item The item the player want to drop
     * @return If the player has successfully dropped his item
     */
    public boolean dropItem(Room room, Item item){
        if(room != null && item != null &&  this.inventory.contains(item)){
            room.addItem(item);
            return this.inventory.remove(item);
        }else{
            return false;
        }
    }


    public String getInventoryContent() {
        String res = "";
        for(Item item : inventory){
            res += item.getName() + " - ";
        }
        return (res.length()>3)? res.substring(0, res.length()-3): res;
    }

    public int getEnergy() {
        return energy;
    }

    private void setEnergy(int energy) {
        if(energy > 5){
            energy=5;
        }else if(energy < 0){
            energy =0;
        }
        this.energy = energy;
    }

    /**
     * Gain a specific amount of energy
     * @param i amount of energy
     */
    public void gainAmountEnergy(int i){
        setEnergy(getEnergy()+i);
    }

    /**
     * Gain one point of energy
     */
    public void gainEnergy(){
        setEnergy(getEnergy()+1);
    }

    /**
     * Loose one point of energy
     */
    public void looseEnergy(){
        setEnergy(getEnergy()-1);
    }

    /**
     * loose a specifig amount of energy
     * @param i amount of energy
     */
    public void looseAmountEnergy(int i){
        setEnergy(getEnergy()-i);
    }
}
