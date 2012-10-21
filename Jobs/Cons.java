package AutoCooker.Jobs;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public class Cons {
	
	public static final int rangeId = 76295;
	
	public static final int cookId = 896;
	public static final int boothId = 76274;
	
	public static boolean bankNow = true;
	
	public static final Area bankArea = new Area(new Tile[] {
			new Tile(3268, 3160, 0), new Tile(3272, 3163, 0),
			new Tile(3272, 3168, 0), new Tile(3272, 3173, 0),
			new Tile(3267, 3174, 0) });

	public static final Area cookArea = new Area(new Tile[] {
			new Tile(3272, 3186, 0), new Tile(3268, 3186, 0),
			new Tile(3268, 3180, 0), new Tile(3272, 3180, 0) });

	public static final Tile[] pathToCook = new Tile[] { new Tile(3270, 3168, 0),
			new Tile(3276, 3171, 0), new Tile(3270, 3184, 0) };
}
