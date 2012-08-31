package com.jpii.dagen;

/**
 * A statistics class manager for Engine class.
 * @author MKirkby
 */
public class EngineStatistics {
	Engine eng;
	/**
	 * Constructs a new instance of EngineStatistics.
	 * @param eng
	 */
	public EngineStatistics(Engine eng) {
		this.eng = eng;
	}
	
	/**
	 * Gets the amount of land in the water. See Engine.getWaterLevel().
	 * @return Returns a number out of 100 that pertains to the amount that is water.
	 */
	public int getPercentWater() {
		int water = 0;
		double[][] points = eng.getPoints();
		for (int x = 0; x < eng.getWidth(); x++) {
			for (int y = 0; y < eng.getHeight(); y++) {
				if (points[x][y] < eng.getWaterLevel()) {
					water += 1;
				}
			}
		}
		return (water * 100) / (eng.getWidth() * eng.getHeight());
	}
	
	/**
	 * Gets the amount of land in the map. See Engine.getWaterLevel().
	 * @return Returns a number out of 100 that pertains to the amount that is land.
	 */
	public int getPercentLand() {
		return 100 - getPercentWater();
	}
}
