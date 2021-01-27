package Multihilo;

import java.io.*;
import java.net.*;

public class HiloServidor extends Thread {

	private Socket cliente;
	
	public HiloServidor(Socket cliente){
		this.cliente = cliente;
	}
	
	@Override
	public void run() {
		try {
			// CREO FLUJO DE ENTRADA DEL CLIENTE
			InputStream entrada = null;
			entrada = cliente.getInputStream();
			DataInputStream flujoEntrada = new DataInputStream(entrada);

			// EL CLIENTE ME ENVIA UN MENSAJE
			System.out.println("Recibiendo del CLIENTE: \n\t" + flujoEntrada.readUTF());

			// CREO FLUJO DE SALIDA AL CLIENTE
			OutputStream salida = null;
			salida = cliente.getOutputStream();
			DataOutputStream flujoSalida = new DataOutputStream(salida);

			// ENVIO UN SALUDO AL CLIENTE
			flujoSalida.writeUTF("Saludos al cliente del servidor");

			// CERRAR STREAMS Y SOCKETS
			entrada.close();
			flujoEntrada.close();
			salida.close();
			flujoSalida.close();
			cliente.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}// fin de Ejemplo1Servidor