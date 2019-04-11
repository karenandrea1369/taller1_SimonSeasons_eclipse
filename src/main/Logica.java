package main;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class Logica {

	// Imagenes
	private PImage instrucciones;
	private PImage score;
	private PImage inicio;

	private Main main;
	private ArrayList<String> secuenciaGenerada;
	private int numRandom;// el que genera una opcion random
	private String tecla;// guarda lo que unde el usuario
	private int pantalla, contador, contador2, cont;
	// contador: lleva el conteo de lo que va undiendo el usuario
	// contador2: lleva el conteo en recorrerSecuencia
	// cont: el contador de tiempo
	private Estacion estaciones;
	private boolean recorrerSec;// para mostrar la nueva secuencia o no

	public Logica(Main app) {
		this.main = app;
		pantalla = 1;
		// Imagenes
		instrucciones = main.loadImage("instrucciones.png");
		score = main.loadImage("score.png");
		inicio = main.loadImage("Inicio Compu.png");
		secuenciaGenerada = new ArrayList<String>();

		contador = 0;
		contador2 = 0;
		tecla = " ";
		recorrerSec = false;

		estaciones = new Estacion(main);
		estaciones.start();
	}


	public void pintar() {
		switch (pantalla) {
		case 1:
			main.image(inicio, 0, 0);
			break;

		case 2:
			estaciones.pintar();
			if (recorrerSec) {
				simon();
			}
			break;

		case 3:
			main.image(score, 0, 0);
			break;
		}

	}

	public void comparar() {
		if (secuenciaGenerada.get(contador).equals(tecla)) {// compara si lo que hay en secuenciaGenerada en la posicion
			// del contador, equivale a lo que hay en la variable tecla(donde se guarda lo
			// que unde el usuario)
			if (contador == secuenciaGenerada.size() - 1) {// pregunta si ya llegó al final de la secuencia
				agregarPaso();// agrega otro paso
				cont = main.millis();// devuelve el contador a cero
				recorrerSec = true;// llama a recorrerSec para que muestre la nueva secuencia
				contador = 0;// reinicia contador
			} else {// sino ha llegado al final del arreglo
				contador++;// va aumentando contador para seguir comparando
			}
		} else {// si no son iguales la tecla y la secuencia pasa a pantalla final
			secuenciaGenerada.clear();
			pantalla = 3; // pantalla de perder
		}
	}

	public void agregarPaso() {

		numRandom = (int) main.random(0, 4);

		switch (numRandom) {
		case 0:
			secuenciaGenerada.add("UP");
			// System.out.println("uno");
			break;
		case 1:
			secuenciaGenerada.add("RIGHT");
			// System.out.println("dos");
			break;
		case 2:
			secuenciaGenerada.add("DOWN");
			// System.out.println("tres");
			break;
		case 3:
			secuenciaGenerada.add("LEFT");
			// System.out.println("cuatro");
			break;
		}
	}

	// se va a llamar recorrerSecuencia
	public void simon() {// solo se encarga de mostrar la secuencia
		if (main.millis() - cont > 600) {// que espere 600 millis antes de hacer lo que sigue

			// contador2 como que recorre la secuencia pero del simon
			if (contador2 == secuenciaGenerada.size()) {// pregunta si ya llegó al final de la secuencia y reinicia
														// contador2
				contador2 = 0;
				recorrerSec = false;

			} else {// si no ha llegado al final, va recorriendo uno por uno el arreglo de
					// secuenciaGenerada
				// y dependiendo de lo que hay muestra la imagen mas grande'rt4
				if (secuenciaGenerada.get(contador2).equals("UP")) {
					estaciones.undido(1);
				}
				if (secuenciaGenerada.get(contador2).equals("RIGHT")) {
					estaciones.undido(2);
				}
				if (secuenciaGenerada.get(contador2).equals("DOWN")) {
					estaciones.undido(3);
				}
				if (secuenciaGenerada.get(contador2).equals("LEFT")) {
					estaciones.undido(4);
				}
				contador2++;
				cont = main.millis();
			}
		}
	}

	public void teclas() {//---------aqui valida lo que recibe de Android----------

		if (main.keyCode == main.UP) {
			tecla = "UP";
			comparar();
			estaciones.undido(1);
		}

		if (main.keyCode == main.RIGHT) {
			tecla = "RIGHT";
			comparar();
			estaciones.undido(2);
		}

		if (main.keyCode == main.DOWN) {
			tecla = "DOWN";
			comparar();
			estaciones.undido(3);
		}

		if (main.keyCode == main.LEFT) {
			tecla = "LEFT";
			comparar();
			estaciones.undido(4);
		}

	}

	public void click() {
		if (pantalla == 1) {
			pantalla = 2;
			cont = main.millis();
			agregarPaso();
			recorrerSec = true;// TOCA PONERLO CUANDO CAMBIE A LA PANTALLA DE JUEGOOOOOOOOOOOOOOOOOO
		}
	}

}
