package com.jpii.dagen;

public class EngineStatistics {
	Engine eng;
	public EngineStatistics(Engine eng) {
		this.eng = eng;
	}
	
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
	
	public int getPercentLand() {
		return 100 - getPercentWater();
	}
}
