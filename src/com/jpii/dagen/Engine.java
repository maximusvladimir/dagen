package com.jpii.dagen;

import java.util.Random;

public class Engine {
	int width = 0;
	int height = 0;
	
	int mtMag = 0;
	double magnitude = 1.0;
	int smoothamount = 10;
	
	double[][] points;
	
	Random rand;
	
	public Engine(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void generate(MapType type, int seed, double magnitude) {
		points = new double[width][height];
		
		rand = new Random(seed);
		this.magnitude = magnitude;
		
		if (type == MapType.Hills)
			mtMag = 3;
		else if (type == MapType.Plains)
			mtMag = 1;
		else if (type == MapType.Dips)
			mtMag = -3;
		
		iterate(0,0,width,height,rand.nextDouble(),rand.nextDouble(),rand.nextDouble(),rand.nextDouble());
		iterate(smoothamount >> 2);
	}
	
	private void iterate(double x, double y, double width, double height, double c1, double c2, double c3, double c4) {
        double e1,e2,e3,e4,e5,e6,e7;
        e6 = Math.floor(width / 2);
        e7 = Math.floor(height / 2);
        if (width > 1 || height > 1)
        {
            e5 = snap(((c1 + c2 + c3 + c4) / 4) + ((rand.nextDouble() - 0.5) * ((e6 + e7) / (width+height) * magnitude)));
            e1 = snap((c1 + c2) / 2);
            e2 = snap((c2 + c3) / 2);
            e3 = snap((c3 + c4) / 2);
            e4 = snap((c4 + c1) / 2);		
            iterate(x, y, e6, e7, c1, e1, e5, e4);
            iterate(x + e6, y, width - e6, e7, e1, c2, e2, e5);
            iterate(x + e6, y + e7, width - e6, height - e7, e5, e2, c3, e3);
            iterate(x, y + e7, e6, height - e7, e4, e5, e3, c4);
        }
        else
        {
        	double c = (c1 + c2 + c3 + c4) / 4;
            points[(int)(x)][(int)(y)] = c;
            if (width == 2)
                points[(int)(x + 1)][(int)(y)] = c;
            if (height == 2)
                points[(int)(x)][(int)(y + 1)] = c;
            if ((width == 2) && (height == 2))
                points[(int)(x + 1)][(int)(y + 1)] = c;
        }
    }

    private double snap(double num) {
        if (num < 0)
            return 0;
        else if (num > 1.0)
            return 1.0;
        else
        	return num;
    }
	
    private void iterate(int cycli) {
    	if (cycli > 0)
    		iterate(cycli - 1);
    	for (int x = 1; x < width-2; x++) {
    		for (int y = 1; y < height-2; y++) {
    			double a = points[x][y];
    			a += points[x+1][y];
    			a += points[x][y+1];
    			
    			a/=3;
    			
    			points[x][y] = a;
    			points[x+1][y] = a;
    			points[x][y+1] = a;
    		}
    	}
    }
    
	public double[][] getPoints() {
		if (points == null)
			throw new NullPointerException("Map has not been generated yet.");
		
		return points;
	}
	
	public int getSmoothFactor() {
		return smoothamount;
	}
	
	public int r(int min, int max) {
		return rand.nextInt(max-min) + min;
	}
	
	public void setSmoothFactor(int amount) {
		smoothamount = amount;
	}
}
