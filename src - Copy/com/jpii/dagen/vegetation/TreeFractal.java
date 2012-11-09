package com.jpii.dagen.vegetation;

import java.awt.*;
import java.util.Random;

public class TreeFractal
{
	Random randy;
	public int swap[][];
	int width, height;
	public Point origin;
	public int maxheight = 0;
	public TreeFractal(int w, int h)
	{
		randy = new Random();
		width = w;
		height = h;
		swap = new int[w][h];
	}
	int maxd = 100;
	public void Generate(int desiredLength, Point start, int direction)
	{
		origin = start;
		maxd = desiredLength;
		step(desiredLength,start,direction,100,true);
	}
	private void step(int length, Point start, double direction, int depth,boolean subBranch)
	{
		double cx = start.x;
		double cy = start.y;
		while (length > 0)
		{
			length--;
			direction += (rand(-6,6) / 10.0);
			
			cx += randd(-0.3,0.3);
			cy -= 1;
			
			if (cy > maxheight)
				maxheight = (int)cy;
			
			int x = (int)cx;
			int y = (int)cy;
			x = snap(x,width);
			y = snap(y,height);
			swap[x][y] = y;
		}
	}
	public int snap(int v, int upper)
	{
		if (v > upper-1)
			v = upper-1;
		if (v < 0)
			v = 0;
		return v;
	}
	public int rand(int min, int max)
	{
		return randy.nextInt(max-min)+min;
	}
	public double randd(double min, double max)
	{
		int rint = rand((int)(min*100000),(int)(max*100000));
		return rint / 100000.0;
	}
}
