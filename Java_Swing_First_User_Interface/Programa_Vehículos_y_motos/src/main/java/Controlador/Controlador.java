package Controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import misExcepciones.misExcepciones;
import modelo.Vehiculo;


/**
 *Controlador general que contiene los métodos:
 * - Crear Archivo : Crea un archivo ("DOCU.txt") donde guardar los datos introducidos al final del programa
 * - EscribirDocu : Guardda los cambios hechos en el archivo "DOCU.txt"
 * - RecuperarDocu : Recupera los datos guardados en "DOCU.txt" convirtiendolos a objetos
 * - ObtenerVehiculos : Metodo para poder acceder al mapa vehículos desde otras clases (un getter basicamente)
 * - RecorrerVehiculos : Muestra por consola una lista con los datos del HasMap Vehículos
 * - IngresarVehiculos : guarda un nuevo vehículo en el Hashmap
 * - Elimanar : Elimina un vehículo del hashMap segun la clave (Matrícula)
 * - MostrarCoches : Recorre el mapa vehículos y devuelve un String con la lista de vehiculos tipo 1 
 * - MostrarMotos : Recorre el mapa vehículos y devuelve un String con la lista de vehiculos tipo 2
 * - crearPilaVehiculosPredeterminada : Introduce en el hash map 4 vehiculos (2 coches y 2 motos) con valores 
 * predeterminados (Lo hice para poder hacer las pruebas sin meter 500 veces los datos)
 * 
 * @author eduardolucasmunozdelucas
 */
public class Controlador {

    private static HashMap<String, Vehiculo> vehiculos = new HashMap<>();
    
    /*Creo la variable file con la ruta determinada para poder acceder a ella desde los métodos de tratamiento de
    archivos del controlador.
    */
    
    static final String ruta = System.getProperty("user.dir") + File.separator + "DOCU.txt";
    static final    File doc = new File(ruta);
   
    /**
     * Crea  el archivo donde se guardarrán los cambios realizados en la base de datos.
     * 
     * @throws misExcepciones 
     */
    public static void crearArchivo() throws misExcepciones {
        if(!doc.exists()){
            try{
                doc.createNewFile();
            }catch(IOException e){
                throw new misExcepciones(" Error en la creación del archivo.");
            }
        }        
    }
    
    /**
     * Almacena el HashMap en el documento "DOCU.txt".
     * @throws FileNotFoundException
     * @throws IOException 
     */
     public static void escribirDocu () throws FileNotFoundException, IOException{
    
        try{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
        
            oos.writeObject(vehiculos);
            oos.flush();
            oos.close();
            System.out.println("Archivo escrito correctamente"); //Lo escribo por consola para enterarme si el proceso se hace o no
        }catch(Exception e) {
		e.printStackTrace();
	}        
    }
    
     /**
      * Rcupera los datos de "DOCU.txt" y los trasforma al objeto HashMap dentro de la aplicación
      * @throws FileNotFoundException 
      */
    public static void recuperarDocu() throws FileNotFoundException{
       
        try{
            FileInputStream fis = new FileInputStream(ruta);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        vehiculos = (HashMap <String, Vehiculo>) ois.readObject(); //Casteo la informacíon del documento para convertirla en el objeto correspondiente
        ois.close();
            System.out.println("Recuperado correctamente."); //Lo escribo por consola para enterarme si el proceso se hace o no
        
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error");
        }        
    }
    
    /**
     * Metodo para poder acceder al mapa vehículos desde otras clases
     * @return ---> HashMap<String, Vehiculo>
     */
    public static HashMap<String, Vehiculo> obtenerVehiculos() {
        return vehiculos;
    }
    
    /**
     * Muestra por consola un listado de las ocurrencias del mapa vehíclos.
     * (Podía tambien utilizar obtenerVehiculos().values().ToString sin necesidad de usar este método pero bueno, por practicar)
     */
    public static void recorrerVehiculos() {
        System.out.println(vehiculos.values());
    }

    /**
     * Ingresa un vehiculo con su matrícula al mapa vehículos si la matrícula no existe. Si existe muestra error.
     * @param clave ---> String
     * @param u ---> Vehiculo
     * @throws misExcepciones 
     */
    public static void ingresarVehículo(String clave, Vehiculo u) throws misExcepciones {
        if (vehiculos.keySet().contains(clave)) {
            throw new misExcepciones("la matrícula ya está registrada");
        }
        
        vehiculos.put(clave, u);
        System.out.println(vehiculos.values()); //Lo muestro por consola para para hacer seguimiento, no es necesario.
    }

    /**
     * Elimina un vehiculo introduciendo como paprametro la matrícula. Si la matricula no existe lanza excepción.
     * @param s 
     */
    public static void Eliminar(String s)  {

        if (!vehiculos.keySet().contains(s)) {
            System.out.println("la matrícula no existe.");
        }
        vehiculos.remove(s);
    }
        
    

    /**
     * Recorre el mapa vehiculos y retorna en un string todos los vehiculos del tipo 1 (Coches). Si no hay mensaje de está vacío
     * @return 
     */
    public static String mostrarCoches() {
        ArrayList<String> lista = new ArrayList<>(); //Creo un arrayList donde almacenaré los String resultados de recorrer el HasMap vehiculos
        for (Iterator<Map.Entry<String, Vehiculo>> iterator = vehiculos.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, Vehiculo> entry = iterator.next();

            if (entry.getValue().getTipo() == 1) {  

                /*
                //Si el tipo de vehiculo es 1, añado al arrayList un String con la matricula, modelo y marca.
                En un principio cambíe el metodo to String para que solo mostrara estos valores, pero como quería tambien comprobar por consola
                que cuando añadía un vehiculo me mostrara todos los datos, decidí hacerlo así.
                */
                lista.add("Matricula: " + entry.getKey() + " ---> " + entry.getValue().getMarca() + "    " + entry.getValue().getModelo() + '\n');
            }
        }

        return (!lista.isEmpty() ? lista.toString() : "--- Vacío ---"); //En caso de no estar vacío retorno un String con la lista, si lo está string : está vacio.
    }

    /**
     * Recorre el mapa vehiculos y retorna en un string todos los vehiculos del tipo 2 (Coches). Si no hay mensaje de está vacío
     * Mismo procedimiento que MOstrarCoches.
     * @return 
     */
    public static String mostrarMotos() {

       
        ArrayList<String> lista = new ArrayList<>();
        for (Iterator<Map.Entry<String, Vehiculo>> iterator = vehiculos.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, Vehiculo> entry =  iterator.next();

            if (entry.getValue().getTipo() == 2) {
//                        System.out.println("Opción 1");
//                        System.out.println(entry.toString()+'\n');

//                        System.out.println("Opción 2");
//                        System.out.println("Vehiculo: " + entry.getValue().getMarca()+ " "+entry.getValue().getModelo());
                System.out.println(entry.getKey()+" --> "+vehiculos.get(entry.getKey()));
                lista.add("Matricula: " + entry.getKey() + " ---> " + vehiculos.get(entry.getKey())+ '\n');
//                        iterator.toString(); 
            }
        }

            return (!lista.isEmpty() ? lista.toString() : "--- Vacío ---");
    }
    
    /**
     * Metodo utilizado para introducir valores directamente y poder hacer las distitnas prruebas sin tener que meter uno a uno.
     */
    public static void crearPilaVehiculosPredeterminada(){
        ArrayList <String> l = new ArrayList<>();
        l.add("Altavoces surround");
        l.add("GarantiaPremium");
                
        
//        vehiculos.put("4567-FTY", new Vehiculo("Fiat","Twingo",  500, l, 5000, 1));
//        vehiculos.put("0000-FHJ", new Vehiculo("Opel", "Combo",  400, l, 21000, 1));
//        vehiculos.put("1234-KLP", new Vehiculo("Kawassaki","Ninja",  500, l, 6000, 2));
//        vehiculos.put("5467-FNO", new Vehiculo("SizzukKi", "Torpedo",  700, l, 45000, 2));

    }
    public static void eliminarTodoElMapa(){
        vehiculos.clear();
    }
    

}  





