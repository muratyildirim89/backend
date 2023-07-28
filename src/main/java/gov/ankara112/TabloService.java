package gov.ankara112;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.List;
import java.util.Map;

public interface TabloService {

    List<String> getTableNames();
    Map<String,List<String >> getColumnNamesByTable();
    Map<String,List<Map<String,Object>>> getDataByTable();
}
