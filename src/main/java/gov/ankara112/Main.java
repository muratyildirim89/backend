package gov.ankara112;

import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.List;

public class Main {

    static DBOperations dbOperations = new DBOperations();

    public static void main(String[] args) {
        Spark.port(4567);

        CorsFilter.apply();

        Spark.get("/count", Main::getCount);
        Spark.get("/database-info", Main::getDatabaseInfo);
        Spark.get("/columns", Main::getColumnsName);
        Spark.get("/list", Main::getList);
        Spark.get("/table-name", Main::getTableName);
    }


    private static String getCount(Request request, Response response) {
        List<DBOperations.TableInfo> tableInfoList = dbOperations.getDatabaseInfo();
        String row = "";
        for (DBOperations.TableInfo tableInfo : tableInfoList) {
            row = String.valueOf(tableInfo.getRowCount());
        }
        return row;
    }

    private static String getDatabaseInfo(Request request, Response response) {
        List<DBOperations.TableInfo> tableInfoList = dbOperations.getDatabaseInfo();
        String info = "";
        for (DBOperations.TableInfo tableInfo : tableInfoList) {
            info = tableInfo.getTableName();
        }
        return info;
    }

    private static List<String> getColumnsName(Request request, Response response) {
        List<DBOperations.TableInfo> tableInfoList = dbOperations.getDatabaseInfo();
        List<String> info=null;
        for (DBOperations.TableInfo tableInfo : tableInfoList) {
            info = tableInfo.getColumnNames();
        }
        return info;
    }

    private static List<String> getList(Request request, Response response) {
        List<DBOperations.TableInfo> tableInfoList = dbOperations.getDatabaseInfo();
        List<String> info=null;
        for (DBOperations.TableInfo tableInfo : tableInfoList) {
            info =tableInfo.getRowData();
        }
        return info;
    }
    private static String getTableName(Request request, Response response) {
        List<DBOperations.TableInfo> tableInfoList = dbOperations.getDatabaseInfo();
        String info = "";
        for (DBOperations.TableInfo tableInfo : tableInfoList) {
            info = tableInfo.getTableName();
        }
        return info;
    }

}