package com.jpii.dagen;

import java.awt.Color;
import java.util.Random;

public class ColorHelper {
	public static Color getRelatedRandomColor(Color c,Random r, int factor) {
		//return new Color(colorSnap(c.getRed() + getRand(r,-factor, factor)),colorSnap(c.getGreen() + getRand(r,-factor, factor))
			//	,colorSnap(c.getBlue() + getRand(r,-factor, factor)),colorSnap(c.getAlpha() + getRand(r,-factor, factor)));
		int red = colorSnap(c.getRed() + getRand(r, -1 * factor, factor));
		int green = colorSnap(c.getGreen() + getRand(r, -1 * factor, factor));
		int blue = colorSnap(c.getBlue() + getRand(r, -1 * factor, factor));
		return new Color(red,green,blue);
	}
	public static int colorSnap(int rgbavalue) {
		if (rgbavalue > 255)
			rgbavalue = 255;
		if (rgbavalue < 0)
			rgbavalue = 0;
		return rgbavalue;
	}
	private static int getRand(Random r,int min, int max) {
		return r.nextInt(max - min) + min;
	}
}
