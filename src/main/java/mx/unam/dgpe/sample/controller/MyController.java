package mx.unam.dgpe.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyController extends AbstractVerticle {
    private static final Logger logger = Logger.getLogger(MyController.class);
    private static String pba = null;
    
    public void start(Future<Void> fut) {
        logger.info("Inicializando Vertical");
        Router router = Router.router(vertx);
        //router.route("/*").handler(StaticHandler.create("assets")); // para invocar asi: http://localhost:8080/index.html
        // el directorio "upload-folder" será creado en la misma ubicación que el jar fue ejecutado
        router.route().handler(BodyHandler.create().setUploadsDirectory("upload-folder"));
        router.get("/api/factorial").handler(this::factorial);
        
        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx.createHttpServer().requestHandler(router::accept).listen(
                config().getInteger("http.port", 8080), result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });        
        pba=System.getenv("PBA");
        logger.info("Vertical iniciada !!!"+pba);
    }  

    private void factorial(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        int factorial = 1;
        int numero = Integer.parseInt(request.getParam("num"));
        if(numero>10000000){
            procesa(0);
        }else{            
            while ( numero!=0) {
                factorial=factorial*numero;
                numero--;
            }
        }
        String jsonResponse = procesa(new String(factorial+"").length());
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }

    

    private String procesa(int longitud) {
        
        Map<Object, Object> info = new HashMap<>();
        info.put("longitud", longitud);

        return Json.encodePrettily(info);
    }

    

}
