package AutoCooker.Jobs;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.methods.widget.Bank;

import AutoCooker.Cooker;
import AutoCooker.Utils.Food;


public class Banking extends Node{
	Food cFood = Cooker.cfood;
	
	@Override
	public boolean activate() {
		return Cons.bankNow;
	}

	@Override
	public void execute() {
		SceneObject Booth = SceneEntities.getNearest(Cons.boothId);
		if (Inventory.getItem(cFood.raw()) == null) {
			if (Cons.bankArea.contains(Players.getLocal().getLocation())) {
				if (Bank.isOpen()) {
					if (Inventory.getItem(cFood.cooked()) != null || Inventory.getItem(cFood.burned()) != null) {
						Cooker.sStatus = "Depositing";
						Bank.depositInventory();
						Time.sleep(1000, 1500);
					} else {
						Cooker.sStatus = "Withdrawing";
						if (Bank.getItem(cFood.raw()) != null) {
							Bank.withdraw(cFood.raw(), 0);
							Time.sleep(600, 800);
						}
					}
				} else {
					if (Booth != null) {
						if (Booth.isOnScreen()
								&& !Players.getLocal().isMoving()) {
							Booth.interact("Bank");
							Time.sleep(1000, 1500);
						} else
							Camera.turnTo(Booth);
					}
				}
			} else {
				Cooker.sStatus = "Walking to bank";
				Walking.newTilePath(Cons.pathToCook).reverse().traverse();
				Time.sleep(500, 700);
			} 
		} else
			Cons.bankNow = false;
	}
}
