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
import java.util.Date;
import java.util.List;
import mx.itson.edu.persistence.MySQLConnection;

/**
 *
 * @author migue
 */
public class Funcion {
    private int id;
    private int idPelicula;
    private int idSala;
    private String horario;
    private Date date;
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
     * @return the idPelicula
     */
    public int getIdPelicula() {
        return idPelicula;
    }

    /**
     * @param idPelicula the idPelicula to set
     */
    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    /**
     * @return the idSala
     */
    public int getIdSala() {
        return idSala;
    }

    /**
     * @param idSala the idSala to set
     */
    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    /**
     * @return the horario
     */
    public String getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
   
    
    public static List<Funcion> getAll(String filtro) {
        List<Funcion> funciones = new ArrayList<>();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement =
                    conexion.prepareStatement("SELECT * FROM funciones WHERE horario LIKE ?");
            statement.setString(1, "%" + filtro + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Funcion f = new Funcion();
                f.setId(resultSet.getInt("id"));
                f.setIdPelicula(resultSet.getInt("id_pelicula"));
                f.setIdSala(resultSet.getInt("id_sala"));
                f.setHorario(resultSet.getString("horario"));
                funciones.add(f);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return funciones;
    }

    public boolean save(int idPelicula, int idSala, String horario, Date date) {
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query = "INSERT INTO funciones (id_pelicula, id_sala, horario, date) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idPelicula);
            statement.setInt(2, idSala);
            statement.setString(3, horario);
            statement.setDate(4, (java.sql.Date) date);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            connection.close();
        } catch (Exception ex) {
            System.err.println("Error:" + ex.getMessage());
        }
        return result;
    }

    public boolean update(int id, int idPelicula, int idSala, String horario, Date date) {
        boolean result = false;
        try {
            Connection conexion = MySQLConnection.get();
            String query = "UPDATE funciones SET id_pelicula = ?, id_sala = ?, horario = ?, date = ? WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, idPelicula);
            statement.setInt(2, idSala);
            statement.setString(3, horario);
            statement.setDate(4, (java.sql.Date) date);
            statement.setInt(5, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            conexion.close();
        } catch (Exception ex) {
            System.err.println("Error:" + ex.getMessage());
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query = "DELETE FROM funciones WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;

            connection.close();
        } catch (Exception ex) {
            System.err.println("Error:" + ex.getMessage());
        }
        return result;
    }
    
    // Otros métodos si es necesario

    
    
}
