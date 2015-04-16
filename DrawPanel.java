import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

import javax.swing.JPanel;


public class DrawPanel extends JPanel {
	private int MAX;
	private int N;
	private Path2D.Double[] triangles;
	private Color[] colors;
	private Path2D.Double netForce;
	
	public DrawPanel(int MAX, int N, Path2D.Double[] triangles, Color[] colors, Path2D.Double netForce){
		this.MAX = MAX;
		this.N = N;
		this.triangles = triangles;
		this.colors = colors;
		this.netForce = netForce;
	}
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // paint the background
        g2.setPaint(Color.white);
        //g2.fillRect(0, 0, getWidth(), getHeight());
	    g2.fillRect(0,0, MAX, MAX);
	    	
	    // paint each triangle
	    for(int i = 0; i<N; i++){
	       g2.setColor(colors[i]);
	       g2.fill(triangles[i]);
	    }
	    
	    // Paint the netForce vector
	    g2.setPaint(Color.black);
	    g2.setStroke(new BasicStroke(2));
	    g2.draw(netForce);
        
    }
    @Override
    public  Dimension getPreferredSize(){
        return new Dimension(MAX, MAX);
    }
}
