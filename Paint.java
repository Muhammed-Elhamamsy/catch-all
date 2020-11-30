import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
// --------------

// import java.awt.geom.Arc2D;
// import java.awt.geom.Ellipse2D;
// import java.awt.geom.GeneralPath;
// import java.awt.geom.Line2D;
// import java.awt.geom.Rectangle2D;
// import java.awt.geom.RoundRectangle2D;

// import javax.swing.JApplet;
// import javax.swing.JFrame;



public class Paint extends Applet{	

// ----------------------VARIABLES AND REFERENCES---------------------------
	// --------------------------SHAPES HOLDER----------------------------
	Vector <MyGeoShape> shapesvector = new Vector <MyGeoShape>();
	Vector <MyGeoShape> backupvector = new Vector <MyGeoShape>();
	Vector <Tuple> indicesvector = new Vector <Tuple>();
	Vector <Tuple> indicesbackupvector = new Vector <Tuple>();
	
	
	
	// -----------------------BUTTONS REFERENCES--------------------------
	Button redbutton;
	Button bluebutton;
	Button greenbutton;
	Button rectbutton;
	Button ovalbutton;
	Button linebutton;
	Button freehandbutton;
	Button eraserbutton;
	Button clearbutton;
	Checkbox dottedcheckbox;
	Checkbox solidcheckbox;
	Button undobutton;
	
	// -----------------------------FLAGS---------------------------------
	//  0: RED, 1:GREEN, 2:BLUE	
	private boolean eraserflag= false;
	private boolean freehandflag= false;
	private boolean solidflag=false;
	private boolean dottedflag=false;
	private boolean clearpressed=false;
	private int colorid=0;
	private int shapeid= 0;
	
	// ------------------------TEMP COORDINATES----------------------------
	private int xtemp;
	private int ytemp;
// ----------------------END OF VARIABLES AND REFERENCES--------------------


	public void init(){		
// 	----------------------INSTANTIATING BUTTONS--------------------------
		redbutton = new Button("   ");
		redbutton.setBackground(Color.red);
		greenbutton = new Button("   ");
		greenbutton.setBackground(Color.green);
		bluebutton = new Button("   ");
		bluebutton.setBackground(Color.blue);
		rectbutton = new Button("RECT");
		ovalbutton = new Button("OVAL");
		linebutton = new Button("LINE");
		freehandbutton = new Button("FREEHAND");
		dottedcheckbox= new Checkbox("- - - - - -");
		eraserbutton = new Button("ERASER");
		solidcheckbox = new Checkbox("SOLID");
		clearbutton = new Button("CLEAR ALL");
		undobutton = new Button("UNDO");
// 	--------------------END OF INSTANTIATING BUTTONS-----------------------
		
		
		
		
// 	----------------------ADDING BUTTONS TO CANVAS-----------------------
		add(redbutton);
		add(greenbutton);
		add(bluebutton);
		add(linebutton);
		add(rectbutton);
		add(ovalbutton);
		add(freehandbutton);
		add(dottedcheckbox);
		add(eraserbutton);
		add(solidcheckbox);
		add(clearbutton);
		add(undobutton);
// 	-------------------END OF ADDING BUTTONS TO CANVAS-----------------------
		
		

// 	-----------------------ADDING ACTIONS TO BUTTONS---------------------
		// ----------------------THREE COLORS---------------------------
		redbutton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					colorid=0;
					eraserflag=false;
				}
		});
		greenbutton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					colorid=1;
					eraserflag=false;
				}
		});
		bluebutton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					colorid=2;
					eraserflag=false;
				}
		});
		
		// ----------------------THREE SHAPES---------------------------
		linebutton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					shapeid=0;
					eraserflag=false;
					freehandflag=false;
					if(colorid==-1)
						colorid=0;
				}
		});	
		rectbutton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					shapeid=1;
					eraserflag=false;
					freehandflag=false;
					if(colorid==-1)
						colorid=0;
				}
		});
		ovalbutton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					shapeid=2;
					eraserflag=false;
					freehandflag=false;
					if(colorid==-1)
						colorid=0;
				}
		});	
		
		// --------------------------FREEHAND---------------------------
		freehandbutton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					shapeid=0;
					freehandflag=true;
					eraserflag=false;
					if(colorid==-1)
						colorid=0;
				}
		});
		
		
		
		// -------------------------DOTTED--------------------------
		dottedcheckbox.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent ie){
					dottedflag=!dottedflag;
					eraserflag=false;
				}
		});
		
		
		
		// ----------------------SOLID CHECKBOX---------------------------
		solidcheckbox.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent ie){
					solidflag=!solidflag;
					eraserflag=false;
				}
		});
		
		// -------------------------CLEARING------------------------------
		// CLEAR ALL
		clearbutton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					if(shapesvector.size()!=0)
					{
						backupvector=(Vector)shapesvector.clone();
						indicesbackupvector=(Vector)indicesvector.clone();
						shapesvector.clear();
						indicesvector.clear();
						clearpressed=true;
						eraserflag=false;
						repaint();
					}

					

				}
		});
		// ERASER
		eraserbutton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					eraserflag=true;
					freehandflag=false;
					colorid=-1;
				}
		});
		// -------------------------UNDOING------------------------------
		undobutton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					
					if(clearpressed && shapesvector.size()==0)
					{
						shapesvector=(Vector)backupvector.clone();
						indicesvector=(Vector)indicesbackupvector.clone();
						clearpressed=false;
					}else if(shapesvector.size()>0){
						for(
							int i=indicesvector.get(indicesvector.size()-1).getstartindex();
							i<=indicesvector.get(indicesvector.size()-1).getendindex();
							i++
						)
						{
							if(shapesvector.size()>0)
								shapesvector.remove(shapesvector.size()-1);
						}
						if(indicesvector.size()>0)
							indicesvector.remove(indicesvector.size()-1);
					}
					repaint();
				}
		});
		
// 	---------------------END OF ADDING ACTIONS TO BUTTONS-------------------
		
		
		
// -------------------------------MOUSE EVENTS------------------------------
		class MyMouse extends MouseAdapter{
			public void mousePressed(MouseEvent ev){
				xtemp=ev.getX();
				ytemp=ev.getY();
				if(!eraserflag){
					switch(shapeid)
					{
						case 0:
							shapesvector.add(new MyLine(xtemp,ytemp,xtemp,ytemp,colorid,solidflag,dottedflag));
						break;
						case 1:
							shapesvector.add(new MyRect(xtemp,ytemp,xtemp,ytemp,colorid,solidflag,dottedflag));
						break;
						case 2:
							shapesvector.add(new MyOval(xtemp,ytemp,xtemp,ytemp,colorid,solidflag,dottedflag));
						break;
					}
				}else{
					shapesvector.add(new MyRect(xtemp-10,ytemp-10,xtemp+10,ytemp+10,colorid,true,false));
					repaint();
				}
				indicesvector.add(new Tuple(shapesvector.size()-1,shapesvector.size()-1));
			}
			public void mouseDragged(MouseEvent ev){
				if(!eraserflag){
					if(!freehandflag)
					{
						shapesvector.get(shapesvector.size()-1).setx1(ev.getX());
						shapesvector.get(shapesvector.size()-1).sety1(ev.getY());
					}else{
						shapesvector.add(new MyLine(xtemp,ytemp,ev.getX(),ev.getY(),colorid,solidflag,dottedflag));
						xtemp=ev.getX();
						ytemp=ev.getY();	
					}
				}else{
					shapesvector.add(new MyRect(ev.getX()-10,ev.getY()-10,ev.getX()+10,ev.getY()+10,colorid,true,false));
				}

				repaint();
			}
			public void mouseReleased(MouseEvent ev){
				indicesvector.get(indicesvector.size()-1).setendindex(shapesvector.size()-1);
			}
		}
		this.addMouseListener(new MyMouse());
		this.addMouseMotionListener(new MyMouse());
// -----------------------------END OF MOUSE EVENTS--------------------------
	}
	
	
	
	public void paint(Graphics g){
		for(int i=0; i<shapesvector.size();i++)
			shapesvector.get(i).draw(g);
		
			
	}
}

class Tuple{
	public Tuple(int startarg, int endarg){
		this.startindex=startarg;
		this.endindex=endarg;	
		}
	private int startindex;
	private int endindex;
	
	// -----------------GETTERS-----------------------
	public int getendindex(){
		return this.endindex;
	}
	public int getstartindex(){
		return this.startindex;
	}
	
	// -----------------SETTERS-----------------------
	public void setstartindex(int startarg){
		this.startindex = startarg;
	}
	public void setendindex(int endarg){
		this.endindex = endarg;
	}
}







	

