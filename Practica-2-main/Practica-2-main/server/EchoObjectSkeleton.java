
package server;
import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;
import rmi1.EchoInterface;
import java.util.ArrayList;
public class EchoObjectSkeleton implements EchoInterface {
    static usuario u = new usuario();
    static ArrayList<usuario> usuarios = u.RecuperarDatos();       
    String myURL="localhost";
    //Constructor de la clase EchoObjectSkeleton
    public EchoObjectSkeleton()
    {
       
        try {
// obtengo el nombre del equipo donde estoy ejecutando y lo guardo en la propiedad MyURL
            myURL=InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) 
               {
                // si no pude conocer el nombre del equipo, mantengo el nombre localhost para MyURL
               myURL="localhost";
              }
    }
// el Metodo Echo que es la implementacion de la interfaz EchoInterface
    public String echo(String input) throws java.rmi.RemoteException 

    {
// toma la fecha y hora 
        // escribe la fecha y la hora, nombre de compuadora y lo regresa
        Date h = new Date();
        // obtengo la fecha y hora actual 
        String fecha = DateFormat.getTimeInstance(3,Locale.FRANCE).format(h);
        String ret = myURL + ":" + fecha + "> " + input;
        System.out.println("Procesando: '" + input + "'");
        
        try {
            Thread.sleep(3000); // hilo actual
            ret = ret + " (retrasada 3 segundos)";
        } catch (InterruptedException e) {}
        System.out.println("Procesamiento de '"+ input +"'terminado.");
        return ret;
    }
    
    
    public static int pagos(Double precio,  String no_tarjeta, String cvv){

        int val=0;
        for(int i=0;i<usuarios.size();i++){
            for(int j=0;j<usuarios.get(i).tarjetas.size();j++){
                if(usuarios.get(i).tarjetas.get(j).numeracion.equals(no_tarjeta))
                if(usuarios.get(i).tarjetas.get(j).CVV.equals(cvv)){
                    System.out.println("El ccv es correcto");
                    if(usuarios.get(i).tarjetas.get(j).Total>=precio)
                        val=1;
                    else
                        val=0;
                }
                else
                System.out.println("El cvv no es correcto");
            }
            


        }
        //checas tu arraytlist de tarjetas
        //checas si está el CVV
        // de ese index recuperas el monto
        //comparas valor con monto en tarjeta<
        //regresas valor si se puede comprar


        return val;
    }
   }
