
package server;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class EchoServer extends EchoObjectSkeleton{
	// Objeto remoto ( es el Stub del servidor, conocido como Skeleton)
    private static EchoObjectSkeleton eo = new EchoObjectSkeleton();
    private static String myURL="localhost";
    private static ServerSocket serverSocket =null;
    private static Socket clientSocket = null;
    private static BufferedReader is = null;
    private static PrintWriter os = null;
    private static String inputline = new String();
    
    public static void main(String[] args) {

        //para crear y recuperar los usuarios
        usuario u = new usuario();

        try {
            // obtengo el nombre de mi computadora
            myURL=InetAddress.getLocalHost().getHostName(); 
        } catch (UnknownHostException e) {
            System.out.println("Host Desconocido  :" + e.toString());
            System.exit(1);
        }
        try {
            // abro el socket para el servidor ( para que este a la escucha) en el puerto 1007
            serverSocket = new ServerSocket(1007);
        } catch (IOException e) {
            System.out.println(myURL + ": no puedo escuchar en el puerto: 1007, " +e.toString());
            System.exit(1);
        }
        System.out.println(myURL + ": EchoServer esta escuchando en el  puerto: 1007");
       // Servidor esta ahora a la escucha ( esperando conexiones)
        try {
            boolean listening = true;
            while(listening) // bucle infinito
            {
                clientSocket = serverSocket.accept();// aceptamos conexion de un client

                /*
                    Aquí se recuperan los valores de los usuarios y sus tarjetas para posibles pagos            
                */      
                // preparamos para leer  del socket
                is = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
                //preparamos para escribir en el socket
                os = new PrintWriter(clientSocket.getOutputStream());
               
                // revisamos que en el socket el cliente ha enviado algun texto
                while ((inputline = is.readLine()) != null) 
                {    
                   
                    /* Se verifica qué quiere, tarjeta, fecha, CVV si pasa se hace println con valor positivo, 
                    si no, con negativo */

                    //cumple con la sintaxis producto, precio,tarjeta y CVV
                    if(inputline.split(",").length == 3) 
                    {
                        System.out.println("Ento again");
                        int resultado = pagos(Double.parseDouble(inputline.split(",")[0]),
                        inputline.split(",")[1].toString(),inputline.split(",")[2].toString());

                        if(resultado == 1){
                            os.println(eo.echo("se puede comprar"));
                           
                        }
                        else{
                            os.println(eo.echo("no se puede comprar"));
                        }
                    }
                    

                    os.println(eo.echo(inputline));//escribimos en el socket lo recibido mas mensaje
                    os.flush();//limpiamos el socket
                }
                os.close();
                is.close();
                clientSocket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error enviando/recibiendo" + e.getMessage());
            e.printStackTrace();
        }


       
    }

    
}