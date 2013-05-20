package nl.gameoflife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MosaicPanel extends JPanel implements ActionListener, MouseListener, ComponentListener {

	private int sq = GameOfLife.settings.getSq();
	private Grid grid;
	private BufferedImage buffer;
	private int gridW, gridH;

	public MosaicPanel(Grid grid) {
		gridW = GameOfLife.settings.getWorldSize();
		gridH = GameOfLife.settings.getWorldSize();

		this.grid = grid;
		this.buffer = new BufferedImage(gridW, gridH, BufferedImage.TYPE_INT_ARGB);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(gridW, gridH));
		setDoubleBuffered(false);
		this.addMouseListener(this);
		
		new Timer(GameOfLife.settings.getSpeed(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fillMosaic(buffer.getGraphics());
			}
		}).start();
		
	}

	public void paintComponent(Graphics g) {				
		super.paintComponent(g);
		g.drawImage(buffer, 0, 0, this);
	}

	private void fillMosaic(Graphics g) {
		Color color;
		Creatures thisShape;

		for (int w = 0; w != grid.getGridSize(); w++) {
			for (int h = 0; h != grid.getGridSize(); h++) {
				thisShape = grid.getCreature(w, h);

				switch (thisShape) {
				case RABBIT:
					color = new Color(0, 255, 0);					
					g.setColor(color);
					g.drawOval(w * sq, h * sq, sq - 1, sq - 1);
					break;
				case FOX:
					color = new Color(0, 125, 230);
					g.setColor(color);
					g.drawOval(w * sq, h * sq, sq - 1, sq - 1);
					break;
				case HUNTER:
					color = new Color(255, 0, 125);
					g.setColor(color);
					g.drawOval(w * sq, h * sq, sq - 1, sq - 1);
					break;
				case RABFOX:
					color = new Color(255, 255, 255);
					g.setColor(color);
					g.drawOval(w * sq, h * sq, sq - 1, sq - 1);
					break;
				case EMPTY:
					color = new Color(0, 0, 0);
					g.setColor(color);
					g.fillRect(w * sq, h * sq, sq, sq);
					break;
				}

			}
		}		
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent m) {
		Point coords = m.getPoint();
		int posX, posY;
		
		posX = coords.x / sq - 7;	// about half of the PULSAR shape
		posY = coords.y / sq - 7;
		
		System.out.println(posX + " = " + posY);
		
		grid.setLive(Shapes.PULSAR, Creatures.RABBIT, posX, posY);
		
	}
	
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
