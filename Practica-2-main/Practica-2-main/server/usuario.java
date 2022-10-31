package server;

import java.util.ArrayList;

public class usuario {

    String nombre;    
    ArrayList<tarjeta> tarjetas;

    public usuario(){
        //método vacío

    }


    public ArrayList<usuario> RecuperarDatos(){
        usuario u = new usuario();
        tarjetas = new ArrayList<>();
        //Cadena de valores de usuario
        ArrayList<usuario> usuarios = new ArrayList<>();

        //tarjetas por usuario
        tarjetas = new ArrayList<>();        

        //tarjeta temporal usuario 1
        tarjeta t_temp = new tarjeta("4444444444444444", "12-26", "333");
        tarjetas.add(t_temp);
        nombre = "Karla";

        //Se guarda usuario 1
        u.nombre = nombre;
        u.tarjetas = tarjetas;
        usuarios.add(u);

        //tarjeta temporal usuario 2
        t_temp = new tarjeta("424242424242424242", "12-26", "444");
        nombre = "Daniela";

        u.nombre = nombre;
        u.tarjetas = tarjetas;
        usuarios.add(u);

        
        for(int i=0; i<usuarios.size(); i++){
            System.out.println(usuarios.get(i).nombre);
        }

        return usuarios;
    }
}

