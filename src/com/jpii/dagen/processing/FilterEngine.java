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
	public static void applyPercentUpper(Engine eng, int percentWater) {
		
		boolean sucessfull = true;
		
		eng.reRunStats();
		
		if (eng.getStats().getPercentWater() >= percentWater)
			sucessfull = false;
		
		while (!sucessfull) {
			applyRise(eng, 0.1);
			
			eng.reRunStats();
			
			if (eng.getStats().getPercentWater() < percentWater)
				sucessfull = true;
		}
	}
	public static void applyRadicalTermination(Engine eng, double heightorabove, int radius) {
		int r2 = radius * radius;
		for (int x = 0; x < eng.getWidth(); x++) {
			for (int y = 0; y < eng.getHeight(); y++) {
		        for (int x2 = -radius; x2 <= radius; x2++) {
		            int y2 = (int) (Math.sqrt(r2 - x2*x2) + 0.5);
		            
		            if (heightorabove < eng.getPoint(x + x2, y + y2) && heightorabove < eng.getPoint(x+x2, y - y2))
		            	eng.setPoint(x + x2, y + y2, 0.4);
		            	eng.setPoint(x + x2, y - y2, 0.4);
		        }
			}
		}
	}
}
