package AutoCooker.Jobs;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import AutoCooker.Cooker;
import AutoCooker.Utils.Food;

public class Cook extends Node {
	Food cFood = Cooker.cfood;
	
	@Override
	public boolean activate() {
		return !Players.getLocal().isMoving() && !Cons.bankNow && Game.getClientState() != 12;
	}
	

	@Override
	public void execute() {
		WidgetChild cookA;
		SceneObject range = SceneEntities.getNearest(Cons.rangeId);
		if (Inventory.getItem(cFood.raw()) != null) {
			if (Cons.cookArea.contains(Players.getLocal().getLocation())) {
				if (range != null) {
					if (range.isOnScreen()) {
						if (!Players.getLocal().isMoving()
								&& Players.getLocal().getAnimation() != cFood.raw()) {
							if (!waitForAnimation(3000)) {
								Cooker.sStatus = "Selecting food";
								Inventory.selectItem(cFood.raw());
								Cooker.sStatus = "Using range";
								Mouse.click(range.getCentralPoint(), true);
								// range.interact("Use");
								Time.sleep(1500, 3000);
								cookA = Widgets.get(905, 14);
								if (cookA != null) {
									Cooker.sStatus = "Cooking food...";
									cookA.click(true);
								}
							}
						}
					} else
						Camera.turnTo(range);
				}
			} else {
				Cooker.sStatus = "Walking to range";
				Walking.newTilePath(Cons.pathToCook).traverse();
			}
		} else
			Cons.bankNow = true;
		
		if (Cooker.lLevel<Skills.getLevel(Skills.COOKING)) {
			Cooker.lLevel=Skills.getLevel(Skills.COOKING);
		}
		
		
	}

	public boolean waitForAnimation(int timeout) {
		long time = System.currentTimeMillis();
		while (System.currentTimeMillis() - time < timeout) {
			if (Players.getLocal().getAnimation() > -1) {
				return true;
			}
			Time.sleep(5, 15);
		}
		return false;
	}

}
