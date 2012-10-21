package AutoCooker.Utils;

import java.util.HashMap;
import java.util.Map;

public enum Food
{
    Shrimp(317, 315, 7954, 1, 34, 300), Manta(389, 391, 393, 91, 100, 2160), Herring(
            345, 347, 357, 5, 37, 500), Trout(335, 333, 343, 20, 50, 700), Cod(
            341, 339, 343, 18, 39, 750), Pike(349, 351, 343, 20, 52, 800), Salmon(
            331, 329, 343, 25, 58, 900), Tuna(359, 361, 367, 30, 63, 1000), Lobster(
            377, 379, 381, 40, 74, 1200), Crayfish(13435, 13433, 13437, 1,
            32, 300), Karambwanji(3150, 3151, 3148, 1, 32, 100), Sardine(
            327, 325, 369, 1, 32, 400), Anchovies(321, 319, 323, 1, 33, 300), Mackerel(
            353, 355, 357, 10, 45, 600), SlimyEel(3379, 3381, 3383, 28, 58,
            950), Karambwan(3142, 3144, 3148, 30, 100, 1900), RainbowFish(
            10138, 10136, 10140, 35, 60, 1100), CaveEel(5001, 5003, 5002,
            38, 40, 1150), Bass(363, 365, 367, 43, 80, 1300), Swordfish(
            371, 373, 375, 45, 86, 1400), LavaEel(2148, 2149, 3383, 53, 30,
            1100), Monkfish(7944, 7946, 7948, 62, 92, 1500), Shark(383,
            385, 387, 80, 100, 2100), SeaTurtle(395, 397, 399, 82, 100,
            2120);


    private int rawId;
    private int level;
    private int stopBurningLevel;
    private int experience;
    private int cookedId;
    private int burntId;
    public int raw()
    
    {
        return rawId;
    }
    
    public int level()
    {
        return level;
    }

    public int stopBurning()
    {
        return stopBurningLevel;
    }

    public int experience()
    {
        return experience;
    }

    public int cooked()
    {
        return cookedId;
    }

    public int burned()
    {
        return burntId;
    }

    Food(int rawId, int cookedId, int burntId, int level,
            int stopBurningLevel, int experience)
    {
        this.rawId = rawId;
        this.burntId = burntId;
        this.cookedId = cookedId;
        this.level = level;
        this.stopBurningLevel = stopBurningLevel;
        this.experience = experience;
    }

    private static Map<Integer, Food> food = new HashMap<Integer, Food>();
    static
    {
        for (Food data : Food.values())
        {
            food.put(data.raw(), data);
        }
    }

    public static Food get(int obj)
    {
        return food.get(obj);
    }
}