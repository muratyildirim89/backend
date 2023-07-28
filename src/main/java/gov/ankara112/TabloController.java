package gov.ankara112;

import java.util.List;
import java.util.Map;
import static spark.Spark.*;

public class TabloController {

    private final TabloService tabloService = new TabloService() {
        @Override
        public List<String> getTableNames() {
            return null;
        }

        @Override
        public Map<String, List<String>> getColumnNamesByTable() {
            return null;
        }

        @Override
        public Map<String, List<Map<String, Object>>> getDataByTable() {
            return null;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    };

//    public void registerRoutes(){
//        get("/tableNames", (req, res) -> {
//            return tabloService.getTableNames(); // Tablo adlarını döndüren metot
//        });
//
//        get("/columnNames/:tableName", (req, res) -> {
//            String tableName = req.params(":tableName");
//            return tabloService.getColumnNamesByTable(tableName); // Tablonun kolon adlarını döndüren metot
//        });
//
//        get("/data/:tableName", (req, res) -> {
//            String tableName = req.params(":tableName");
//            return tabloService.getDataByTable(tableName); // Tablodaki verileri döndüren metot
//        });
//    }
}
