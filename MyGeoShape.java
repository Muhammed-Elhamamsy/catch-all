import java.awt.Graphics;
import java.awt.Color;


public abstract class MyGeoShape{
	protected int x0;
	protected int y0;
	protected int x1;
	protected int y1;
	protected int colorcode;
	protected boolean solidstate;
	protected boolean dottedstate;
	protected boolean starttracker=true;
	
	
	abstract public void draw(Graphics g);

	
// ---------------------------ABSTRACT GETTERS-------------------------
	abstract public int getx0();
	abstract public int gety0();
	abstract public int getx1();
	abstract public int gety1();
	abstract public int getcolorcode();
	
	
// ---------------------------ABSTRACT SETTERS-------------------------
	abstract public void setx0(int x0c);
	abstract public void sety0(int y0c);
	abstract public void setx1(int x1c);
	abstract public void sety1(int y1c);
	
	
// --------------------------CONCRETE FUNCTIONS------------------------
	public void setshapecolor(Graphics g, int colornumber){
		switch(colornumber)
		{
			case 0:
				g.setColor(Color.red);
			break;
			case 1:
				g.setColor(Color.green);
			break;
			case 2:
				g.setColor(Color.blue);
			break;
			case -1:
				g.setColor(Color.white);
			break;
		}
	}
	
	
}