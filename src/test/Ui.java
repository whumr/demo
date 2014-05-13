package test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class Ui extends JFrame {

	private static final long serialVersionUID = 8639537085769502818L;

	private Block[][] blocks;
	int start_x = 50, start_y = 50;
	
	public Ui(Block[][] blocks) {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				checkBlock(p);
			}
		});
		
		setSize(500, 300);
		setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation( (screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        
        this.blocks = blocks;
        drawMap();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawMap();
	}
	
	public void drawMap() {
		Graphics gs = getGraphics();
		for (int i = 0; i < blocks.length; i++) {
			Block[] bs = blocks[i];
			for (int j = 0; j < bs.length; j++) {
				Block b = bs[j];
				gs.drawRect(start_x + b.x1, start_y + b.y1, (b.x2 - b.x1), (b.y2 - b.y1));
			}
		}
	}
	
	private void checkBlock(Point p) {
		int x = p.x, y = p.y;
		all:
		for (int i = 0; i < blocks.length; i++) {
			Block[] bs = blocks[i];
			for (int j = 0; j < bs.length; j++) {
				Block b = bs[j];
				if (b.isInside(x, y, start_x, start_y)) {
					System.out.println((i + 1) + "\t" + (j + 1) + "\t" + b.toString());
					getGraphics().drawOval((start_x + b.x1), (start_y + b.y1), (b.x2 - b.x1), (b.y2 - b.y1));
					break all;
				}
			}
		}
	}
}