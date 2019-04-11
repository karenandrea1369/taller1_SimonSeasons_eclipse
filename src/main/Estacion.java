package main;

import processing.core.PImage;

public class Estacion extends Thread {
	private Main app;
	private int x, y;
	private int tipo= 0; // 0.sin nada, 1.primavera, 2.verano, 3.otoño, 4.invierno
	private boolean grande, marco, vivo;
	private PImage normal;
	private PImage verano;
	private PImage invierno;
	private PImage otono;
	private PImage primavera;

	public Estacion(Main app) {
		this.app = app;
		this.x = x;
		this.y = y;
		normal = app.loadImage("Normal.png");
		verano= app.loadImage("Verano.png");
		invierno= app.loadImage("Invierno.png");
		otono= app.loadImage("otono.png");
		primavera= app.loadImage("Primavera.png");
	}

	public void run() {
		while (true) {

			try {
				sleep(66);
				if (tipo!=0) {
					sleep(300);
					tipo=0;
				}
				
//				if (grande) {
//					sleep(70);
//					grande = false;
//				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void pintar() {
		switch (tipo) {
		case 0://----------SIN UNDIR NINGUNO----------
			//imagen
			app.image(normal, 0, 0);
			break;
		case 1://----------PRIMAVERA----------
			//imagen
			app.image(primavera, 0, 0);
			break;
		case 2://----------VERANO----------
			//imagen
			app.image(verano, 0, 0);
			break;
		case 3://----------OTOÑO----------
			//imagen
			app.image(otono, 0, 0);
			break;
		case 4://----------INVIERNO----------
			//imagen
			app.image(invierno, 0, 0);
			break;
		}
	}

	public void undido(int i) {
		tipo = i;
	}

	public boolean isGrande() {
		return grande;
	}

}