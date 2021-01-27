package ClienteServidor;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Ejemplo1Cliente {
     
      public static void main(String[] args) {
          String Host = "localhost";
          int Puerto = 6000;//puerto remoto

          DataInputStream flujoEntrada = null;
          DataOutputStream flujoSalida = null;
          Socket Cliente = null;
          
          Scanner sc = new Scanner(System.in);
          
    	  try {
            System.out.println("MODO ACTIVADO: CLIENTE.");
            System.out.println("Programa Cliente iniciado, esperando respuesta...\n");

            Cliente = new Socket(Host, Puerto);
            
            // CREO FLUJO DE SALIDA AL SERVIDOR
            flujoSalida = new DataOutputStream(Cliente.getOutputStream());        
            // CREO FLUJO DE ENTRADA AL SERVIDOR
            flujoEntrada = new DataInputStream(Cliente.getInputStream());
             
            System.out.println("Conexion establecida, accediendo al menu de seleccion.\n");	

            while(true) {
            	

            // EL SERVIDOR ME ENVIA UN MENSAJE  
            System.out.println("Aplicacion Walkie-Talkie iniciada, elija una opcion.");
            System.out.println("1.- Enviar Mensaje");
            System.out.println("2.- Recibir Mensaje");                
                
            String opcion = sc.nextLine();	
            	
            if(opcion.equals("1")) {
                System.out.println("¿Cual es el mensaje que desea enviar?.\n");
                String mensaje = sc.nextLine();
                flujoSalida.writeUTF(mensaje);
                System.out.println("El mensaje ha sido enviado correctamente por el destinatario. \n");
            }            
            else if (opcion.equals("2")) {
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
                System.out.println("Entrada erronea, vuelve a intentarlo.\n");
            }
    	  }
            
    	  } catch(Exception e) {
            //System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());
           e.printStackTrace();
            // CERRAR STREAMS Y SOCKETS

    	  } finally {
    		  try {
              flujoEntrada.close();
              flujoSalida.close();
              Cliente.close();
    		  } catch(IOException e) {
    			  e.printStackTrace();
    		  }
    	  }
      }// fin de main
     

}// Fin de Ejemplo1Cliente