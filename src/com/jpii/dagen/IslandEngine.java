package com.jpii.dagen;

import java.util.Random;

public class IslandEngine extends Engine {
	
	double rr = 0;
	
	public IslandEngine(int width, int height) {
		super(width, height);
		if (rand == null) {
			rand = new Random();
		}
		rr = rand.nextInt(width/3) + width/2;
	}
	
	public void setIslandRadius(double radius) {
		rr = radius;
	}
	
	public void generate(MapType type, int seed, double magnitude) {
		super.generate(type, seed, magnitude);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (!iterate(x,y)){
					//points[x][y] += 0.2;
					//points[x][y] -= 0.3;
				}
				else {
					//points[x][y] -= 0.3;
				}
			}
		}
		int numLakes = 0;
		
		//numLakes = (int)((rr / width) * 12);
		
		for (int lakes = 0; lakes < numLakes; lakes++) {
			boolean lakepointfound = false;
			int x = 0;
			int y = 0;
			while (!lakepointfound) {
				x = r(0,width);
				y = r(0,height);
				
				if (points[x][y] > 0.4)
					lakepointfound = true;
			}
			
			for (int pass = 0; pass < r(60,100); pass++) {
				int rx = x + r(-5,5);
				int ry = y + r(-5,5);
				for (int c = 0; c < r(5,10); c++){
					int gx = r(5,10);
					int gy = r(5,10);
					gx = ((int)Math.cos(gx * r(2,5)) + gx + r(-3,3)) + rx;
					gy = ((int)Math.sin(gy * r(2,5)) + gy + r(-3,3)) + ry;
					if (gx < width && gy < height && gx >= 0 && gy >= 0)
						points[gx][gy] = 0;
				}
			}
		}
		
		for (int x = 2; x < width-4; x++) {
			for (int y = 2; y < height-4; y++) {
				if (points[x-1][y] < 0.7 && points[x][y-1] < 0.7 &&
						points[x+1][y] < 0.7 && points[x][y+1] < 0.7) {
					//points[x][y] = rand.nextDouble() * 0.6;
				}
			}
		}		
	}
	
	/**
	 * Cycles the main generation routine. Do not play with.
	 * @param x The x adjust coordinate
	 * @param y The y adjust coordinate
	 * @return A flag indicating whether the coordinate is valid.
	 */
	private boolean iterate(int x, int y) {
		double nx = Math.cos(x * rr) + x + r(-2,2);
		double ny = Math.sin(y * rr) + y + r(-2,2);
		
		double tr = Math.sqrt(Math.pow(nx - (width/2),2) + Math.pow(ny - (height/2),2));
		
		if (tr + 0.000001 + (width/5) > rr)
			return false;
		else
			return true;
	}
}
