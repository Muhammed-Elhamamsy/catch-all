import java.awt.*;
import java.awt.geom.*;
import java.applet.*;


public class MyOval extends MyGeoShape{	
	MyOval(int x0c,int y0c,int x1c,int y1c, int colorarg, boolean solidarg, boolean dottedarg)
	{
		this.x0=x0c;
		this.y0=y0c;
		this.x1=x1c;
		this.y1=y1c;
		this.colorcode=colorarg;
		this.solidstate=solidarg;
		this.dottedstate=dottedarg;
	}
	// -----------------GETTERS-----------------------
	public int getx0(){
		return this.x0;
	}	
	public int gety0(){
		return this.y0;
	}	
	public int getx1(){
		return this.x1;
	}	
	public int gety1(){
		return this.y1;
	}
	public int getcolorcode(){
		return this.colorcode;
	}

	
	
	// -----------------SETTERS-----------------------
	public void setx0(int x0c){
		this.x0=x0c;
	}
	public void sety0(int y0c){
		this.y0=y0c;
	}
	public void setx1(int x1c){
		this.x1=x1c;
	}
	public void sety1(int y1c){
		this.y1=y1c;
	}


	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		if(this.dottedstate){
			g2d.setStroke (new BasicStroke(
			  0f, 
			  BasicStroke.CAP_ROUND, 
			  BasicStroke.JOIN_ROUND, 
			  1f, 
			  new float[] {2.5f}, 
			  0f));
		}else{
			g2d.setStroke (new BasicStroke());
		}

		this.setshapecolor(g,this.getcolorcode());
		if(!this.solidstate){
			if(this.x0>this.x1&&this.y0>this.y1){
				g2d.draw(
					new Ellipse2D.Double (
					this.x1, this.y1, this.x0-this.x1, this.y0-this.y1
				));
			}
			else if(this.x0>this.x1){
				g2d.draw(
					new Ellipse2D.Double (
					this.x1, this.y0, this.x0-this.x1, this.y1-this.y0
				));
			}else if(this.y0>this.y1){
				g2d.draw(
				new Ellipse2D.Double (
					this.x0, this.y1, this.x1-this.x0, this.y0-this.y1
				));
			}else {
				g2d.draw(
				new Ellipse2D.Double (
					this.x0, this.y0, this.x1-this.x0, this.y1-this.y0
				));
			}
			
		}else{
			if(this.x0>this.x1&&this.y0>this.y1){
				g.fillRect(
					this.x1, this.y1, this.x0-this.x1, this.y0-this.y1
				);
			}
			else if(this.x0>this.x1){
				g.fillRect(
					this.x1, this.y0, this.x0-this.x1, this.y1-this.y0
				);
			}else if(this.y0>this.y1){
				g.fillRect(
					this.x0, this.y1, this.x1-this.x0, this.y0-this.y1
				);
			}else {
				g.fillRect(
					this.x0, this.y0, this.x1-this.x0, this.y1-this.y0
				);
			}
		}
	}

}