package test;

import java.text.DecimalFormat;

public class Map {

	private static int width = 400, height = 200,
			xCount = 5, yCount = 4; 
	
	private static Block[][] blocks;
	
	public static void main(String[] args) {
		init();
//		for (int i = 0; i < blocks.length; i++) {
//			Block[] b = blocks[i];
//			for (int j = 0; j < b.length; j++) {
//				System.out.println(b[j]);
//			}
//		}
		
		DecimalFormat df = new DecimalFormat(".##");
		double d = 1252.2563;
		String st = df.format(d);
		System.out.println(st);
		System.out.println(Double.parseDouble(st));
		
//		new Ui(blocks);
	}

	public static void init() {
		blocks = new Block[xCount][yCount];
		int xGap = width / xCount;
		int yGap = height / yCount;
		int x = 0, y = 0;
		for (int i = 0; i < xCount; i++) {
			for (int j = 0; j < yCount; j++) {
				blocks[i][j] = new Block(x, y, x + xGap, y + yGap);
				y += yGap; 
			}
			x += xGap;
			y = 0;
		}
	}
}

class Block {
	int x1, y1, x2, y2;

	public Block(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public boolean isInside(int x, int y, int offside_x, int offside_y) {
		return x >= x1 + offside_x && x <= x2 + offside_x && y >= y1 + offside_y && y <= y2 + offside_y;
	}
	
	@Override
	public String toString() {
		return "(" + x1 + "," + y1 + ")->(" + x2 + "," + y2 + ")";
	}
	
	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
}