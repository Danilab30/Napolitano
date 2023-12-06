/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.edu.entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.itson.edu.persistence.MySQLConnection;

/**
 *
 * @author dani_
 */
public class Pelicula {

    private int id;
    private String nombre;
    private String sinopsis;
    private int duracion;
    private String categoria;
    private String genero;
    private String actores;
    
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the sinopsis
     */
    public String getSinopsis() {
        return sinopsis;
    }

    /**
     * @param sinopsis the sinopsis to set
     */
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    /**
     * @return the duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the actores
     */
    public String getActores() {
        return actores;
    }

    /**
     * @param actores the actores to set
     */
    public void setActores(String actores) {
        this.actores = actores;
    }
  
    public static List<Pelicula> getAll(String filtro){
        List<Pelicula> peliculas = new ArrayList<>();
        try{
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement =
                    conexion.prepareStatement("SELECT * FROM peliculas WHERE nombre LIKE ?");
            statement.setString(1, "%" + filtro + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
             while (resultSet.next()){
                 Pelicula p = new Pelicula();
                 p.setId(resultSet.getInt("id"));
                 p.setNombre(resultSet.getString("nombre"));
                 p.setSinopsis(resultSet.getString("sinopsis"));
                 p.setDuracion(resultSet.getInt("duracion"));
                 p.setGenero(resultSet.getString("Genero"));
                 p.setActores(resultSet.getString("Actores"));
                 peliculas.add(p);
             }
                    
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return peliculas;
    }
    
     public static boolean save(Pelicula pelicula){
        boolean result = false;
        try{
           Connection conexion = MySQLConnection.get();
           String query = "INSERT INTO peliculas (nombre,sinopsis,duracion,genero,actores) VALUES (?,?,?,?,?)";
           PreparedStatement statement = conexion.prepareStatement(query);
           statement.setString(1, pelicula.getNombre());
           statement.setString(2, pelicula.getSinopsis());
           statement.setInt(3, pelicula.getDuracion());
           statement.setString(4, pelicula.getGenero());
           statement.setString(5, pelicula.getActores());
           statement.execute();
           
           result = statement.getUpdateCount() == 1;
           
           conexion.close();
        }catch(Exception ex){
            System.err.println("Error:" + ex.getMessage());
        }
        return result;
    }
    
     public static boolean update(Pelicula pelicula){
        boolean result = false;
        try{
           Connection conexion = MySQLConnection.get();
           String query = "UPDATE peliculas SET nombre = ?,sinopsis = ?,duracion = ?,genero = ?, actores = ? WHERE id = ?";
           PreparedStatement statement = conexion.prepareStatement(query);
           statement.setString(1, pelicula.getNombre());
           statement.setString(2, pelicula.getSinopsis());
           statement.setInt(3, pelicula.getDuracion());
           statement.setString(4, pelicula.getGenero());
           statement.setString(5, pelicula.getActores());
           statement.setInt(6, pelicula.getId());
           statement.execute();
           
           result = statement.getUpdateCount() == 1;
           
           conexion.close();
        }catch(Exception ex){
            System.err.println("Error:" + ex.getMessage());
        }
        return result;
    }
    
    public static boolean delete(Pelicula pelicula){
        boolean result = false;
        try{
           Connection conexion = MySQLConnection.get();
           String query = "DELETE from peliculas WHERE id = ?";
           PreparedStatement statement = conexion.prepareStatement(query);
           statement.setInt(1, pelicula.getId());
           statement.execute();
           
           result = statement.getUpdateCount() == 1;
           
           conexion.close();
        }catch(Exception ex){
            System.err.println("Error:" + ex.getMessage());
        }
        return result;
    }
    
    
    
}
