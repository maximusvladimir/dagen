package com.jpii.dagen.test;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import com.jpii.dagen.processing.Pathfinder;
import com.jpii.dagen.processing.SimplePoint;

@SuppressWarnings("serial")
public class CCS extends Applet implements MouseMotionListener {
	Pathfinder astar;
	boolean[][] grid;
	SimplePoint destination;
	boolean[][] targetLogic;
	
	int width = 30;
	int height = 30;
	int pixel = 8;
	public void init() {
		
		addMouseMotionListener(this);
		setSize(width*(pixel+1),height*(pixel+1));
		
		grid = new boolean[width][height];
		targetLogic = new boolean[width][height];
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
				grid[x][y] = false;
				targetLogic[x][y] = false;
			}
		}

		for (int x = 0; x < grid.length; x++) {
			grid[x][0] = true;
		}
		
		for (int x = 0; x < grid.length; x++) {
			grid[0][x] = true;
		}
		
		for (int x = 0; x < grid.length; x++) {
			grid[grid.length - 1][x] = true;
		}
		
		for (int x = 0; x < grid.length; x++) {
			grid[x][grid.length - 1] = true;
		}
		astar = new Pathfinder(grid, new SimplePoint(1,1));
		
		destination = new SimplePoint(grid.length - 2,grid.length - 2);
		
		for (int c = astar.getCurrent().getX(); c < destination.getX(); c++) {
			SimplePoint p1 = astar.f(c, astar.findSlope(destination));
			targetLogic[p1.getX()][p1.getY()] = true;
		}
	}
	
	public void paint(Graphics g2) {
		BufferedImage buffer = new BufferedImage(width*pixel,height*pixel,BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.getGraphics();
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
				if (grid[x][y]) {
					g.setColor(Color.green);
				}
				else {
					g.setColor(Color.blue);
				}
				
				if (astar.getCurrent().getX() == x && astar.getCurrent().getY() == y) {
					g.setColor(Color.cyan);
				}
				
				if (destination.getX() == x && destination.getY() == y) {
					g.setColor(Color.red);
				}
				
				if (targetLogic[x][y]) {
					g.setColor(Color.gray);
				}
				g.fillRect(x*8, y*8, 8,8);
			}
		}
		g2.drawImage(buffer, 0, 0, null);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		int mx = arg0.getX();
		int my = arg0.getY();
		
		int lx = mx / pixel;
		int ly = my / pixel;
		
		if (lx < 0)
			lx = 0;
		if (ly < 0)
			ly = 0;
		
		if (lx >= 30)
			lx = 29;
		
		if (ly >= 30)
			ly = 29;
		
		destination = new SimplePoint(lx,ly);
		
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
				targetLogic[x][y] = false;
			}
		}
		
		for (int c = astar.getCurrent().getX(); c < destination.getX(); c++) {
			SimplePoint p1 = astar.f(c, astar.findSlope(destination));
			targetLogic[p1.getX()][p1.getY()] = true;
		}
		
		repaint();
	}
}
