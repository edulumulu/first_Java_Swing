
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author edulumulu
 */
public class Vehiculo implements Serializable{
    private String marca;
    private String modelo;
    private int cilindrada;
    private ArrayList complementos;
    private double precio;
    private int tipo;

    public Vehiculo(String marca, String modelo, int cilindrada, ArrayList complementos, double precio, int tipo) {
        this.marca = marca;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
        this.complementos = complementos;
        this.precio = precio;
        this.tipo = tipo;
    }

    public Vehiculo(int tipo) {
        this.tipo = tipo;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        return this.tipo == other.tipo;
    }

    
    // Sobre escribí el to. Sring para mostrar solamente matricula, marca y modelo, pero al final prefiero usar el completo para confirmar por consola
    // Y para mostrar menos datos lo he hecho de otra manera (ver controlador)
    @Override
    public String toString() {
                
//        return "     "+ marca+" "+ modelo+", " +'\n';

        return (marca + " "+ modelo);
//        return "Vehiculo" + "marca=" + marca + ", modelo=" + modelo + ", cilindrada=" + cilindrada +  ", precio=" + precio + ", tipo=" + tipo +", complementos=" + complementos + '}'+'\n';
    }
    
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public ArrayList getComplementos() {
        return complementos;
    }

    public void setComplementos(ArrayList complementos) {
        this.complementos = complementos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
    
}
