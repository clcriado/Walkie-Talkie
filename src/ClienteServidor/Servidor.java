package ClienteServidor;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Clase Servidor, establece una conexión esperando al cliente remoto que se conecte,
 * al realizar la conexion cuenta con dos opciones, enviar o recibir un mensaje.
 * 
 * @author Carlos
 */
public class Servidor {
     
      public static void main(String[] arg) {
          boolean conexion = true;
          ServerSocket servidor = null;
          Socket clienteConectado = null;
          InputStream entrada = null;
          OutputStream salida = null;
          DataInputStream flujoEntrada = null;
          DataOutputStream flujoSalida = null;
          
    	  try {
            int numeroPuerto = 6000;// Puerto
            servidor = new ServerSocket(numeroPuerto);
            System.out.println("MODO ACTIVADO: SERVIDOR.");
            System.out.println("Programa iniciado, esperando respuesta...\n");
            clienteConectado = servidor.accept();
            System.out.println("Conexion establecida, accediendo al menu de seleccion.\n");	
            
            // CREO FLUJO DE ENTRADA DEL CLIENTE   

            entrada = clienteConectado.getInputStream();
            flujoEntrada = new DataInputStream(entrada);
           
            // CREO FLUJO DE SALIDA AL CLIENTE

            salida = clienteConectado.getOutputStream();
            flujoSalida = new DataOutputStream(salida);
            
            while(true) {
            	
            // EL SERVIDOR ME ENVIA UN MENSAJE  
            System.out.println("Aplicacion Walkie-Talkie iniciada, elija una opcion.");
            System.out.println("1.- Enviar Mensaje");
            System.out.println("2.- Recibir Mensaje");
                
            Scanner sc = new Scanner(System.in);
                
            String opcion = sc.nextLine();	
            	
            if(opcion.equals("1")) {
                System.out.println("¿Cual es el mensaje que desea enviar?.\n");
                String mensaje = sc.nextLine();
                flujoSalida.writeUTF(mensaje);
                System.out.println("El mensaje ha sido enviado correctamente por el destinatario. \n");
            }        
            else if (opcion.equals("2")) {
            	Boolean acceso = flujoEntrada.readBoolean();
            	if(acceso.equals(true)) {
                    boolean comunicacion = true;
                    System.out.println("Esperando mensajes...\n");
                    while(comunicacion) {
                    	String mensajeRecibido = flujoEntrada.readUTF();
                    	if(!mensajeRecibido.equals("")) {
                        	System.out.println(mensajeRecibido + "\n");
                        	comunicacion = false;
                    	}

                    }
            	} else {
                    System.out.println("Este canal esta ocupado actualmente.\n");
            	}
            } else {
                System.out.println("Entrada erronea, vuelve a intentarlo.\n");
            }
    	  }
           
            // CERRAR STREAMS Y SOCKETS
    	  } catch(Exception e) {
    		  e.printStackTrace();
    	  } finally {
    		  try {
    	          entrada.close();
    	          flujoEntrada.close();
    	          salida.close();
    	          flujoSalida.close();
    	          clienteConectado.close();
    	          servidor.close();
    		  } catch(IOException e) {
    			  e.printStackTrace();
    		  }
    	  }
      }
}