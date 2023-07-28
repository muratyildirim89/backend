package gov.ankara112;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOperations {

    public static class TableInfo {
        private String databaseInfo;
        private String tableName;
        private List<String> columnNames;
        private List<String> rowData;
        private int rowCount;

        public TableInfo() {
            this.columnNames = new ArrayList<>();
            this.rowData = new ArrayList<>();
        }

        public TableInfo(String databaseInfo, String tableName, List<String> columnNames, List<String> rowData, int rowCount) {
            this.databaseInfo = databaseInfo;
            this.tableName = tableName;
            this.columnNames = columnNames;
            this.rowData = rowData;
            this.rowCount = rowCount;
        }

        public String getDatabaseInfo() {
            return databaseInfo;
        }

        public void setDatabaseInfo(String databaseInfo) {
            this.databaseInfo = databaseInfo;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public List<String> getColumnNames() {
            return columnNames;
        }

        public void setColumnNames(List<String> columnNames) {
            this.columnNames = columnNames;
        }

        public List<String> getRowData() {
            return rowData;
        }

        public void setRowData(List<String> rowData) {
            this.rowData = rowData;
        }

        public int getRowCount() {
            return rowCount;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }
    }

    public List<TableInfo> getDatabaseInfo() {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/tryDatabase";
        String admin = "postgres";
        String password = "12345";

        List<TableInfo> tableInfoList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, admin, password)) {
            DatabaseMetaData metaData = connection.getMetaData();

            String[] tableTypes = {"TABLE"};
            try (ResultSet tables = metaData.getTables(null, null, "%", tableTypes)) {
                while (tables.next()) {
                    TableInfo tableInfo = new TableInfo();
                    tableInfo.setDatabaseInfo("JDBC Version:" + metaData.getDriverVersion() + " Driver Name: " + metaData.getDriverName());
                    String tableName = tables.getString("TABLE_NAME");
                    tableInfo.setTableName(tableName);

                    List<String> columnNames = new ArrayList<>();
                    List<String> rowData = new ArrayList<>();

                    try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
                        while (columns.next()) {
                            String columnName = columns.getString("COLUMN_NAME");
                            columnNames.add(columnName);
                        }
                    }

                    try (Statement statement = connection.createStatement();
                         ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {
                        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                        while (resultSet.next()) {
                            StringBuilder row = new StringBuilder();
                            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                                row.append(resultSetMetaData.getColumnName(i))
                                        .append(": ")
                                        .append(resultSet.getString(i))
                                        .append(", ");
                            }
                            rowData.add(row.toString());
                        }
                    }

                    try (Statement statement = connection.createStatement();
                         ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM " + tableName)) {
                        resultSet.next();
                        int rowCount = resultSet.getInt(1);
                        tableInfo.setRowCount(rowCount);
                    }
                    tableInfo.setColumnNames(columnNames);
                    tableInfo.setRowData(rowData);
                    tableInfoList.add(tableInfo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database Exceptin: " + e.getMessage());
        }
        return tableInfoList;
    }
}