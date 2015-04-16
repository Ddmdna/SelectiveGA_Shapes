import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

import javax.swing.JComponent;

//This will represent the Bug through a shape
public class Model {
	private DrawPanel drawPanel;
	private Color[] colors = new Color[]{
			Color.red, Color.pink, Color.green, Color.yellow, Color.lightGray, 
			Color.pink, Color.cyan, Color.orange, Color.gray, Color.cyan
			};
	private Path2D.Double[] triangles = new Path2D.Double[10];
	
	final int MAX = 100; // MAX for X and Y
	final int CENTER = MAX/2; // Center location for X and Y
	public Model(Bug bug){
	double fullAngle = (Math.PI/5);
	double halfAngle = (Math.PI/10);
	
	// Keep track of the netForce with vector components i and j
	double iv = 0.0;
	double jv = 0.0;
	
	// Create each triangle
    for(int i = 0; i<10; i++){
    	double r =( (5*bug.getValue(i)/12) / (2*Math.sin(halfAngle)) );
    	
    	double X[] = new double[]{
    			CENTER, r*Math.cos(i*fullAngle)+CENTER, r*Math.cos((i+1)*fullAngle)+CENTER
    	};
    	double Y[] = new double[]{
    			CENTER, r*Math.sin(i*fullAngle)+CENTER, r*Math.sin((i+1)*fullAngle)+CENTER
    	};
    	
    	// Determine the netForce Component totals
		iv += r*Math.cos(i*fullAngle)+r*Math.cos((i+1)*fullAngle);
		jv += r*Math.sin(i*fullAngle)+r*Math.sin((i+1)*fullAngle);
		
    	
	    //Create the triangle
    	Path2D.Double polyline = new Path2D.Double();
    	polyline.moveTo(X[0], Y[0]);
    	polyline.lineTo(X[1], Y[1]);
    	polyline.lineTo(X[2], Y[2]);
    	polyline.closePath();
    	
    	// add it to the array of triangles
    	triangles[i] = polyline;
    }
    
    // Create an arrow to represent the netForce Vector
 	Path2D.Double netForce = new Path2D.Double();
 	netForce.moveTo(CENTER, CENTER);
 	netForce.lineTo(iv+CENTER, jv+CENTER);
    
    drawPanel = new DrawPanel(MAX, 10, triangles, colors, netForce);
		
	
    drawPanel.setPreferredSize(new Dimension(MAX*2, MAX*2));
    drawPanel.setBackground(Color.gray);
	
	} // Model(Bug bug)
	
	public DrawPanel getDrawPanel(){
		
		return drawPanel;
	}
}
