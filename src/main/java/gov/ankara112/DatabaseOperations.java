package gov.ankara112;

import java.sql.*;

public class DatabaseOperations {
    public DatabaseOperations(){
        String jdbcUrl = "jdbc:postgresql://localhost:5432/tryDatabase";
        String admin = "postgres";
        String password = "12345";

        try(Connection connection = DriverManager.getConnection(jdbcUrl, admin, password);){
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("JDBC Driver Version: " + metaData.getDriverVersion() +
                    " Driver Name: " + metaData.getDriverName());

            String[] tableTypes = {"TABLE"};
            try (ResultSet resultSet = metaData.getTables(null, null, "%", tableTypes)) {
                System.out.println("Table Names in the Database: ");
                while (resultSet.next()) {
                    String tableName = resultSet.getString("TABLE_NAME");
                    System.out.println(tableName);

                    try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
                        while (columns.next()) {
                            String columnName = columns.getString("COLUMN_NAME");
                            System.out.println(" Kolon Adı: " + columnName);
                        }
                    }

                    try (Statement statement = connection.createStatement();
                         ResultSet result = statement.executeQuery("SELECT * FROM " + tableName)) {
                        ResultSetMetaData resultSetMetaData = result.getMetaData();
                        while (result.next()) {
                            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                                System.out.println(" " + resultSetMetaData.getColumnName(i) + " : " +
                                        result.getString(i));
                            }
                        }
                    }

                    try (Statement count = connection.createStatement();
                         ResultSet resultSet1 = count.executeQuery("SELECT  COUNT(*) FROM " + tableName)) {
                        resultSet1.next();
                        int rowCount = resultSet1.getInt(1);
                        System.out.println("Data nu: " + rowCount);
                    }
                }
            }
        } catch(SQLException e){
            System.out.println("Veritabanı Hatası: " + e.getMessage());
        }

    }
}
