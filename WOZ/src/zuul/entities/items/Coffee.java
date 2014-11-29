package zuul.entities.items;

import zuul.Game;

public class Coffee extends Item{

	public Coffee() {
		name = "coffee";
		energy = 1;
	}
	
	@Override
	public String use(){
		Game.getPlayer().gainAmountEnergy(this.energy);
		return "You won " + this.energy + " Energy !";
	}
}
