package com.tacstargame.ui.util;

public class Measure {
	public int width = 0;
	public int height = 0;
	
	public Measure(int width, int height) {
		this.height = height;
		this.width = width;
	}
	
	@Override
	public boolean equals(Object obj) {
		return width == ((Measure) obj).width && height == ((Measure) obj).height;
	}
	
	@Override
	public String toString() {
		return "[" + width + "x" + height + "]";
	}
}
