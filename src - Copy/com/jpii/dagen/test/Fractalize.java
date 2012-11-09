package com.jpii.dagen.test;


import java.applet.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import com.jpii.dagen.vegetation.TreeFractal;

public class Fractalize extends Applet
{
	TreeFractal fract;
	int width,height;
	public void init()
	{
		width = 150;
		height = 75;
		fract = new TreeFractal(width,height);
		fract.Generate(45, new Point(width/2,height-10),180);
		setSize(width*4,height*4);
		repaint();
	}
	public void paint(Graphics g2)
	{
		Graphics2D g = (Graphics2D)g2;
		g.setColor(new Color(36,71,111));
		g.fillRect(0,0,4*width,4*height);
		g.setColor(new Color(52,72,34));
		g.fillRect(0,fract.origin.y+1+(fract.maxheight*2),4*width,height*2);
		int prevx = -1;
		int prevy = -1;
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				if (fract.swap[x][y] > 0)
				{
					int rgb = ((fract.swap[x][y] * 255) /100);
					g.setColor(new Color(98-fract.rand(-5,5),79-fract.rand(-5,5),49-fract.rand(-5,5)));
					double ao = (Math.sqrt(fract.swap[x][y])*Math.cbrt(fract.swap[x][y]))/9+4;
					g.setStroke(new BasicStroke((float)ao));
					//g.fillRect(x*4,y*4,4,4);
					if (prevx != -1 && prevy != -1)
						g.drawLine(x*4,y*4,prevx*4,prevy*4);
					else
						g.drawLine(x*4,y*4,x*4,y*4);
					
					prevx = x;
					prevy = y;
					g.setStroke(new BasicStroke(1.0f));
					
					if (fract.swap[x][y] > 75)
					{
						g.setColor(new Color(3,rgb-fract.rand(0, 30),fract.rand(30,60)));
						int d = fract.rand(3,6);
						int dover2 = d / 2;
						//g.drawLine(x*4,y*4,(x-fract.rand(-5,5))*4,(y+fract.rand(10,20))*4);
						g.fillOval((x*4)-dover2-fract.rand(-3,4),(4*y)-dover2-fract.rand(-3,4),d,d);
					}
				}
			}
		}
	}
	public boolean mouseDown(Event e, int x, int y)
	{
		fract = new TreeFractal(width,height);
		fract.Generate(45, new Point(width/2,height-10),180);
		setSize(width*4,height*4);
		repaint();
		return true;
	}
}
