package br.com.rchlo.data;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionFactory {

    public static void init() {
        runScript("schema.sql");
        runScript("data.sql");
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://root:root@localhost:3306/rchlo");
        } catch (SQLException ex) {
            throw new IllegalStateException("Error while connecting with database", ex);
        }
    }

    private static void runScript(String script) {
        try {
            Path path = Paths.get(Objects.requireNonNull(ConnectionFactory.class.getClassLoader().getResource(script)).toURI());
            String schema = Files.readString(path);
            try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(schema)) {
                preparedStatement.execute();
                System.out.println("Script ran successfully: " + script);
            }
        } catch (IOException | SQLException | URISyntaxException ex) {
            throw new IllegalStateException("Error while processing " + script, ex);
        }
    }

}
