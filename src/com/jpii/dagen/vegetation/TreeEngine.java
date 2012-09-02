package com.jpii.dagen.vegetation;

import java.awt.image.*;
import java.awt.*;

import com.jpii.dagen.Engine;

public class TreeEngine {
	Engine eng;
	BufferedImage image;
	Tree[][] flags;
	/**
	 * Creates a new instance of the TreeEngine class.
	 * @param eng The engine to produce trees with.
	 */
	public TreeEngine(Engine eng){
		if (eng == null || !eng.isGenerated()){
			throw new NullPointerException("Generator has not been initialized.");
		}
		
		this.eng = eng;
	}
	
	/**
	 * Generates a series of tree, based on the current engine.
	 * @param minheight The minimum height that a tree has to be placed at.
	 * @param min The minimum number of trees.
	 * @param max The maximum number of trees.
	 */
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
	
	/**
	 * Gets the generation in image form.
	 * @param width The width of the image.
	 * @param height The height of the image.
	 * @param pixelscale The current pixel scale to use.
	 * @return Gets the generated image.
	 */
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
					drawLivingTree(g,x*pixelscale,y*pixelscale,pixelscale,flags[x][y]);
				}
			}
		}
		
		return image;
	}

	private void drawLivingTree(Graphics g, int x, int y, int scale, Tree tree) {
		if (tree.getTreeType() == TreeType.Pine) {
			int scz = 10;
			int brkr = 76 + eng.r(-scz, scz);
			int brkg = 43 + eng.r(-scz, scz);
			int brkb = 27 + eng.r(-scz, scz);
			int trrr = 71 + eng.r(-scz, scz);
			int trrg = 133 + eng.r(-scz, scz);
			int trrb = 66 + eng.r(-scz, scz);
			g.setColor(new Color(brkr,brkg,brkb));
			g.fillRect(x-(1*scale), y-(6*scale), 1*scale, 6*scale);
			g.setColor(new Color(trrr,trrg,trrb));
			g.fillRect(x-(1*scale), y-(6*scale), 1*scale, 3*scale);
		}
		else
			throw new NullPointerException("Unimplemented tree exception!");
	}
}
