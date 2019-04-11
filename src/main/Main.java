package main;

import processing.core.PApplet;

public class Main extends PApplet {
	
	static Comunicacion com;
	private Logica log;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Main");
		com= new Comunicacion();
		com.start();
		
	}
	
	public void settings() {
		size(1000,600);
	}
	
	public void setup() {
		log = new Logica(this);
	}
	
	public void draw() {
		log.pintar();
	}
	
	public void keyPressed() {
		log.teclas();
	}
	
	public void mouseClicked() {
		log.click();
	}
	
	

}
