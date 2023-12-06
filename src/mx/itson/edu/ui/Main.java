/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mx.itson.edu.ui;
import java.sql.Connection;
import java.util.Scanner;
import mx.itson.edu.entidades.Funcion;
import mx.itson.edu.persistence.MySQLConnection;
import mx.itson.edu.entidades.Pelicula;


/**
 *
 * @author migue
 */
public class Main {

    private static Connection connection = MySQLConnection.get();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {

         if (connection == null) {
            System.out.println("No se pudo conectar a la base de datos.");
            return;
        }
         
   Pelicula nemo = new Pelicula();
   nemo.setActores("Nemo y su jefe");
   nemo.setDuracion(0);
   nemo.setGenero("Animacion 3D");
   nemo.setNombre("Buscando nemo y dory");
   nemo.setSinopsis("El nemo se puso crazy");
   nemo.setId(5);
     
        try {
          Pelicula.delete(nemo);  
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
     
    }
    
}
