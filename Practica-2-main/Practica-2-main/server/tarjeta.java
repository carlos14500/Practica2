package server;

public class tarjeta {

    String numeracion;
    String FechaExpiracion; //MM-DD o DD-MM
    String CVV; //3 dígitos
    Double Total;

    public tarjeta(String num, String fec, String cvv){
        this.numeracion = num;
        this.FechaExpiracion = fec;
        this.CVV = cvv;
        this.Total = 100.0;
    }
    
}
