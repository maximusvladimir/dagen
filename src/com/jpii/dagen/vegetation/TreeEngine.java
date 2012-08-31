package com.jpii.dagen.vegetation;

import java.awt.image.*;
import java.awt.*;

import com.jpii.dagen.Engine;

public class TreeEngine {
	Engine eng;
	BufferedImage image;
	Tree[][] flags;
	public TreeEngine(Engine eng){
		if (eng == null || !eng.isGenerated()){
			throw new NullPointerException("Generator has not been initialized.");
		}
		
		this.eng = eng;
	}
	
	public void generate(double minheight, int min, int max) {
		int total = 0;
		int wanted = eng.r(min, max);
		flags = new Tree[eng.getWidth()][eng.getHeight()];
		while (total < wanted) {
			boolean flag = false;
			int x = eng.r(0, eng.getWidth());
			int y = eng.r(0, eng.getHeight());
			while (!flag) {
				if (eng.getPoint(x, y) > minheight && eng.getPoint(x-1, y-1) > minheight && eng.getPoint(x, y-1) > minheight
						&& eng.getPoint(x-1, y) > minheight && eng.getPoint(x, y+1) > minheight
						&& eng.getPoint(x+1, y) > minheight && eng.getPoint(x+1, y+1) > minheight) {
					flag = true;
					break;
				}
				else {
					x = eng.r(0, eng.getWidth());
					y = eng.r(0, eng.getHeight());
				}
			}
			flag = false;
			if (eng.r(0,5) == 1) {
				flag = false;
			}
			flags[x][y] = new Tree(TreeType.Pine,flag);
			total++;
		}
	}
	
	public BufferedImage getGeneratedImage(int width, int height, int pixelscale) {
		image = new BufferedImage(width*pixelscale, height*pixelscale, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		int pixel = pixelscale;
		// / 3;
		if (pixel <= 0)
			pixel = 1;
		for (int x = 0; x < eng.getWidth(); x++) {
			for (int y = 0; y < eng.getHeight(); y++) {
				if (flags[x][y] != null) {
					x *= pixelscale;
					y *= pixelscale;
					int scz = 10;
					int brkr = 76 + eng.r(-scz, scz);
					int brkg = 43 + eng.r(-scz, scz);
					int brkb = 27 + eng.r(-scz, scz);
					int trrr = 71 + eng.r(-scz, scz);
					int trrg = 133 + eng.r(-scz, scz);
					int trrb = 66 + eng.r(-scz, scz);
					g.setColor(new Color(brkr,brkg,brkb));
					g.fillRect(x-(1*pixel), y-(3*pixel), 1*pixel, 6*pixel);
					g.setColor(new Color(trrr,trrg,trrb));
					g.fillOval(x-(1*pixel), y-(3*pixel), 1*pixel, 3*pixel);
					x /= pixelscale;
					y /= pixelscale;
				}
			}
		}
		
		return image;
	}
}
