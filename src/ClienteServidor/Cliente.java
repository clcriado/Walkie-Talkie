package ClienteServidor;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Clase Cliente, establece una conexión buscando al servidor remoto seleccionado,
 * al realizar la conexion cuenta con dos opciones, enviar o recibir un mensaje.
 * 
 * @author Carlos
 */
public class Cliente {
     
      public static void main(String[] args) {
          String Host = "localhost"; //IP del Servidor al que nos vamos a conectar.
          int Puerto = 6000; //Puerto remoto.

          //Declaramos los flujos de entrada y salida.
          DataInputStream flujoEntrada = null;
          DataOutputStream flujoSalida = null;
          
          Socket Cliente = null; //Creamos la conexion.          
          Scanner sc = new Scanner(System.in); //Creamos la entrada de datos.
          
    	  try {
            System.out.println("MODO ACTIVADO: CLIENTE.");
            System.out.println("Programa Cliente iniciado, esperando respuesta...\n");

            Cliente = new Socket(Host, Puerto); //Le damos los valores a la conexion.
            
            // Creamos el flujo de entrada y salida del servidor.
            flujoSalida = new DataOutputStream(Cliente.getOutputStream());        
            flujoEntrada = new DataInputStream(Cliente.getInputStream());
             
            System.out.println("Conexion establecida, accediendo al menu de seleccion.\n");	

            while(true) {
            System.out.println("Aplicacion Walkie-Talkie iniciada, elija una opcion.");
            System.out.println("1.- Enviar Mensaje");
            System.out.println("2.- Recibir Mensaje");                
            System.out.println("3.- Salir");       
            
            String opcion = sc.nextLine(); //Obtenemos la opcion elegida.
            	
           //SI LA OPCION ES 1 ENVIAMOS UN MENSAJE.
            if(opcion.equals("1")) {
                System.out.println("¿Cual es el mensaje que desea enviar?.\n");
                String mensaje = sc.nextLine(); //Escribimos el mensaje que vamos a enviar.
                flujoSalida.writeUTF(mensaje);
                System.out.println("El mensaje ha sido enviado correctamente por el destinatario. \n");
            }            
            
            //SI LA OPCION ES 2 ESPERAMOS A RECIBIR UN MENSAJE.
            else if (opcion.equals("2")) {
                boolean comunicacion = true;
                System.out.println("Esperando mensajes...\n");
                while(comunicacion) {
                	String mensajeRecibido = flujoEntrada.readUTF(); //Leemos el mensaje que nos han enviado.
                	if(!mensajeRecibido.equals("")) {
                    	System.out.println(mensajeRecibido + "\n");
                    	comunicacion = false;
                	}
                }
            }   
            
            //SI LA OPCION ES 3 CERRAMOS EL PROGRAMA.
            else if(opcion.equals("3")) {
                System.out.println("Conexion finalizada, muchas gracias.\n");
            	System.exit(0);
            	
            //SI NO ES NINGUNA DE LAS OTRAS OPCIONES DA ERROR.
            } else {
                System.out.println("Entrada erronea, vuelve a intentarlo.\n");
                }
            }
    	  } catch(Exception e) {
            //System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());
           e.printStackTrace();
            // CERRAR STREAMS Y SOCKETS

    	  } finally {
    		  //FINALMENTE CERRAMOS TODAS LAS CONEXIONES EXISTENTES.
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