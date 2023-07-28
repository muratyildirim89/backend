package gov.ankara112;

import spark.Request;
import spark.Response;
import spark.Spark;

public class SparkController {
    public static void main(String[] args) {
        Spark.port(4567);

        Spark.get("/count",SparkController::getHello);
        Spark.post("/greet/:name",SparkController::postGreet);
        Spark.get("/hello/:name",SparkController::getHelloName);
    }

    private static String getHello(Request request, Response response){
        return "Hello, Java Developer!" + request.params("name");
    }

    private static String postGreet(Request request,Response response){
        String name=request.params("name");
        return "Hello! " + name + "!";
    }

    private static String getHelloName(Request request,Response response){
        String name=request.params("name");
        return "Hello! " + name +"!**";
    }
}
