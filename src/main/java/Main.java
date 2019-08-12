import ar.com.nubank.exceptions.CannotMoveRobotException;
import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.exceptions.RobotNotFoundException;
import ar.com.nubank.services.GridService;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/myapp/";

    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig().packages("ar.com.nubank.rest");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();

    }


    public static void execute(){
        GridService service = GridService.instance();

        service.createGrid(10,10);

        try {
            service.addRobot(0,0,1);
            //service.addRobot(1,1,1);
//            service.addRobot(5,5,0);
            service.addDinosaur(0,1);
            service.addDinosaur(5,6);

            service.printGrid();

            System.out.println("-----------------------");




            service.attack(0);

            service.moveForward(0);


            service.turnRight(0);


            service.moveForward(0);


            service.moveForward(0);

            service.moveForward(0);


            service.moveForward(0);


            service.moveForward(0);
            service.turnLeft(0);

            service.moveForward(0);
            service.moveForward(0);
            service.moveForward(0);
            service.moveForward(0);
            service.attack(0);

            service.printGrid();
        } catch (ElementAlreadyPresentException e) {
            e.printStackTrace();
        } catch (RobotNotFoundException e) {
            e.printStackTrace();
        } catch (CannotMoveRobotException e) {
            e.printStackTrace();
        }

    }

}
