package zuul.entities;

import zuul.rooms.Room;

import java.util.ArrayList;

/**
 * Created by user on 13/11/14.
 */
public class Player {

    private final String name;
    private ArrayList<Item> inventory;

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
        for(Item i : inventory){
            res += i.toString() + ", ";
        }
        return (res.length()>2)? res.substring(0, res.length()-2): res;
    }
}
