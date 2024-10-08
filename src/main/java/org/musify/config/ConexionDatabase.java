package org.musify.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDatabase {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    // Instancia única para el Singleton
    private static ConexionDatabase database;
    private static Connection conexion;

    // el constructor es privado para evitar que lo instancien otras clases
    private ConexionDatabase() {
        try {
            // Lee lo de application.properties y trae el url usuario y contraseña
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/application.properties"));

            // Asignar las propiedades a las variables
            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.username");
            PASSWORD = properties.getProperty("db.password");

            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");

        } catch (IOException | SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static ConexionDatabase getDatabase() {
        if (database == null) {
            database = new ConexionDatabase();
        }
        return database;
    }

    public Connection getConexion() {
        return conexion;
    }
}