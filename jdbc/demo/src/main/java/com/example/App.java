package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws SQLException {

        // auto close connection
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/edix", "postgres", "1234")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }



            String GETALL = "SELECT * FROM persons;";
            PreparedStatement output = conn.prepareStatement(GETALL);
            ResultSet input = output.executeQuery();
            ResultSetMetaData metadata = input.getMetaData();
            while (input.next()) {
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    System.out.print(input.getObject(i).toString()+" ");
                }
                System.out.print("\n");
            }


            /* String GETONE = "SELECT * FROM persons WHERE ID = ?";
            output = conn.prepareStatement(GETONE);
            output.setInt(1, 2);
            input = output.executeQuery();
            metadata = input.getMetaData();
            while (input.next()) {
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    System.out.print(input.getObject(i).toString()+" ");
                }
                System.out.print("\n");
            }

            String INSERT = "insert into persons values (default, ?)";
            output = conn.prepareStatement(INSERT);
            output.setString(1, "HOLA");
            input = output.executeQuery();
            metadata = input.getMetaData();
            while (input.next()) {
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    System.out.print(input.getObject(i).toString()+" ");
                }
                System.out.print("\n");
            }

            String UPDATE = "UPDATE PERSONS SET NAME = ? WHERE ID = ?";
            output = conn.prepareStatement(UPDATE);
            output.setString(1, "HEY");
            output.setInt(2, 1);
            input = output.executeQuery();
            metadata = input.getMetaData();
            while (input.next()) {
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    System.out.print(input.getObject(i).toString()+" ");
                }
                System.out.print("\n");
            }
            String DELETE = "DELETE FROM PERSONS WHERE ID = ?";
            output = conn.prepareStatement(DELETE);
            output.setInt(1, 1);
            input = output.executeQuery();
            metadata = input.getMetaData();
            while (input.next()) {
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    System.out.print(input.getObject(i).toString()+" ");
                }
                System.out.print("\n");
            } */



            

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private final String url = "jdbc:postgresql://localhost/edix";
    private final String user = "postgres";
    private final String password = "1234";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
