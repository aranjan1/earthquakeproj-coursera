package module1;
import processing.core.*;
public class MyPapplet extends PApplet {
PImage back;
	public void setup(){
		size(500,500);
		back = loadImage("http://cdn1.landscapehdwalls.com/thumbs/1/the-perfect-house-in-the-green-scenery-6109-706.jpg", "jpg");
	}
	public void draw(){
		back.resize(width, height);
		image(back,0,0);
		fill(255,209,0);
		ellipse(width/4,height/4,width/5,height/5);
		
	}
}
