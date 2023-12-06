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
public class Sala {

     private int id;
    private String estado;
    
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
     * @return the disponible
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setEstado(String disponible) {
        this.estado = disponible;
    }
    
   public static List<Sala> getAll(String filtro){
        List<Sala> salas = new ArrayList<>();
        try{
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement =
                    conexion.prepareStatement("SELECT * FROM salas WHERE nombre LIKE ?");
            statement.setString(1, "%" + filtro + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
             while (resultSet.next()){
                Sala s = new Sala();
                 s.setId(resultSet.getInt("id"));
                 s.setEstado(resultSet.getString("disponible"));
                 salas.add(s);
             }
                    
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return salas;
    }
    
     public static boolean save(Sala sala){
        boolean result = false;
        try{
           Connection conexion = MySQLConnection.get();
           String query = "INSERT INTO salas (Disponible) VALUES (?)";
           PreparedStatement statement = conexion.prepareStatement(query);
           statement.setString(1, sala.getEstado());
           statement.execute();
           
           result = statement.getUpdateCount() == 1;
           
           conexion.close();
        }catch(Exception ex){
            System.err.println("Error:" + ex.getMessage());
        }
        return result;
    }
    
     public static boolean update(Sala sala){
        boolean result = false;
        try{
           Connection conexion = MySQLConnection.get();
           String query = "UPDATE salas SET Disponible = ? WHERE id = ?";
           PreparedStatement statement = conexion.prepareStatement(query);
           statement.setString(1, sala.getEstado());
           statement.setInt(2, sala.getId());
           statement.execute();
           
           result = statement.getUpdateCount() == 1;
           
           conexion.close();
        }catch(Exception ex){
            System.err.println("Error:" + ex.getMessage());
        }
        return result;
    }
    
    public static boolean delete(Sala sala){
        boolean result = false;
        try{
           Connection conexion = MySQLConnection.get();
           String query = "DELETE from salas WHERE id = ?";
           PreparedStatement statement = conexion.prepareStatement(query);
           statement.setInt(1, sala.getId());
           statement.execute();
           
           result = statement.getUpdateCount() == 1;
           
           conexion.close();
        }catch(Exception ex){
            System.err.println("Error:" + ex.getMessage());
        }
        return result;
    }
    
    
    
}

    
    
    
    
    

