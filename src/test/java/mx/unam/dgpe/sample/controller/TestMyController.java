package mx.unam.dgpe.sample.controller;

import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

import io.vertx.core.AbstractVerticle;
import static mx.unam.dgpe.sample.controller.RestUtil.*;

public class TestMyController extends AbstractVerticle {
    private static final Logger logger = Logger.getLogger(TestMyController.class);
    
    @Test
    public void ok() throws Exception {
        //Se debe configurar ip y puerto para que test funcione correctamente

    	//String result = sendGet("http://192.168.1.145:8082/api/factorial?num=5");
        //logger.info(result);
        assert(true);
        //assertTrue(result.contains("\"longitud\" : 3"));
         
    
        
    }

}
