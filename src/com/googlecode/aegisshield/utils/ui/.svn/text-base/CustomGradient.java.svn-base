/*
 * Copyright (C) 2008 - 2009 Rares Barbantan
 *  
 *	This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.googlecode.aegisshield.utils.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

/**
 * Custom non-linear gradient. 
 * 
 * @author Rares Barbantan
 *
 */
public class CustomGradient extends Drawable{

	Paint p;
	
	public CustomGradient() {
		super();
		p = new Paint();
		moveCenter(0);
	}
	
	public void moveCenter(float center) {
		Shader s = new LinearGradient(0, 0, 200, 0, new int[] {Color.RED, Color.YELLOW, Color.GREEN}, new float[] {0, center, 1}, TileMode.REPEAT);
		p.setShader(s);
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(0, 0, 200, 200, p);
		invalidateSelf();
	}

	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub
		
	}

}
