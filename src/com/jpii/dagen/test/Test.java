package com.jpii.dagen.test;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

import com.jpii.dagen.IslandEngine;
import com.jpii.dagen.MapType;

@SuppressWarnings("serial")
public class Test extends Applet{
	
	IslandEngine eng;
	
	int WIDTH = 100;
	int HEIGHT = 100;
	
	public void init() {
		eng = new IslandEngine(WIDTH,HEIGHT);
		setSize(WIDTH * 2, HEIGHT * 2);
		//eng.setSmoothFactor(3);
		eng.generate(MapType.Hills, (int)(Math.random() * 4000), 1);
		repaint();
	}
	
	public void paint(Graphics g) {
		if (eng != null) {	
			double[][] points = eng.getPoints();
			double[][] water = eng.getWaterPoints();
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			for (int x = 0; x < WIDTH; x++) {
				for (int y = 0; y < HEIGHT; y++) {
					boolean isWater = false;
					if (water[x][y] == 0)
						isWater = true;
					
					if (isWater){
						g.setColor(new Color(50 - eng.r(-5,5), 100 - eng.r(-5, 5),150-  eng.r(-5, 5)));
						g.fillRect(x * 3, y * 3, 3,3);
					}
					else {
						int reduce = (int)(points[x][y] * 60);
						int cr = 64 - reduce;//eng.r(-5,5) - reduce;
						int cg = 128 - reduce + eng.r(-5, 5);//eng.r(-5, 5) - reduce;
						int cb = 80 - reduce + eng.r(-5, 5); //eng.r(-5, 5) - reduce;
						g.setColor(new Color(cr, cg,cb));
						g.fillRect(x * 3, y * 3, 3,3);
					}
				}
			}
		}
	}
	
}
