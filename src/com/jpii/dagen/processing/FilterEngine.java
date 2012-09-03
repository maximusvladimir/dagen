/**
 * 
 */
package com.jpii.dagen.processing;

import com.jpii.dagen.Engine;

/**
 * @author MKirkby
 *
 */
public class FilterEngine {
	public static void applyRise(Engine eng, double amount) {
		for (int x = 0; x < eng.getWidth(); x++) {
			for (int y = 0; y < eng.getHeight(); y++) {
				eng.setPoint(x, y, eng.getPoint(x, y) + amount);
			}
		}
	}
	public static void applyPercent(Engine eng, int percentWater) {
		
		boolean sucessfull = true;
		
		eng.reRunStats();
		
		if (eng.getStats().getPercentWater() <= percentWater)
			sucessfull = false;
		
		while (!sucessfull) {
			applyRise(eng, -0.1);
			
			eng.reRunStats();
			
			if (eng.getStats().getPercentWater() > percentWater)
				sucessfull = true;
		}
	}
}
