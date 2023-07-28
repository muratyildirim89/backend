package gov.ankara112;


import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

public class CorsFilter {
    public static void apply(){
        Filter filter=(Request request, Response response) ->{
            String origin =request.headers("Origin");
            response.header("Access-Control-Allow-Origin",origin);
            response.header("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS");
            response.header("Access-Control-Allow-Headers","Content-Type");
        };
        Spark.before(filter);
    }
}
