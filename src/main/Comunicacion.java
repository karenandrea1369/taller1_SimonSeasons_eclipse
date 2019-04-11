package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

public class Comunicacion extends Thread {

	private ServerSocket ss;
	private Socket s;
	private DataOutputStream salida;
	private DataInputStream entrada;

	public Comunicacion() {
		System.out.println("CREANDO CONEXION");
	}

	@Override
	public void run() {
		try {
			ss = new ServerSocket(5000);
			System.out.println("Esperando cliente...");
			s = ss.accept();
			System.out.println("Cliente conectado");
			//Inicializa la entrada y salida
			salida = new DataOutputStream(s.getOutputStream());//salida
			entrada = new DataInputStream(s.getInputStream());//entrada, aun no se usa
		} catch (IOException e1) {
			e1.printStackTrace();
		}				
		while (true) {				
			
			try {
				recibir();
				sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 			
		}
	}

	private void recibir() throws IOException {
		String mensaje = entrada.readUTF();
	}

	public void enviar(final String msj) {
		if (s != null & s.isConnected() && salida != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						salida.writeUTF(msj);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
