package AdmRAM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GMemory extends JFrame{
	
	
	public GMemory(ArrayList<Segment> memory){
		VisualMem graphicMemory = new VisualMem();
		
		JFrame visualMemFrame = new JFrame("Visualizador de Memoria");
		visualMemFrame.add(graphicMemory);
		
		visualMemFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		visualMemFrame.setVisible(true);
		
		
	}
	
	public class VisualMem extends JPanel{
		
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.darkGray);
		g.drawRect(10, 10, 240, 100);
	}
	}
	
}
