import ar.com.nubank.exceptions.CannotMoveRobotException;
import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.exceptions.RobotNotFoundException;
import ar.com.nubank.services.DinosaurService;
import ar.com.nubank.services.GridService;
import ar.com.nubank.services.RobotService;
import com.sun.net.httpserver.HttpServer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/nubank/";

    public static void startServer() throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.packages",
                 "ar.com.nubank.rest");

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }

    public static void main(String[] args) throws Exception {
        startServer();


        //execute();

    }


    public static void execute(){
        GridService gridService = GridService.instance();

        gridService.createGrid(10,10);

        RobotService robotService = RobotService.instance();
        DinosaurService dinosaurService = DinosaurService.instance();

        try {
            robotService.addRobot(0,0,1);
            //service.addRobot(1,1,1);
//            service.addRobot(5,5,0);
            dinosaurService.addDinosaur(0,1);
            dinosaurService.addDinosaur(5,6);

            System.out.println(gridService.printGrid());

            System.out.println("-----------------------");




            robotService.attack(0);
            System.out.println(gridService.printGrid());

            robotService.moveForward(0);


            robotService.turnRight(0);


            robotService.moveForward(0);


            robotService.moveForward(0);

            robotService.moveForward(0);


            robotService.moveForward(0);


            robotService.moveForward(0);
            robotService.turnLeft(0);

            robotService.moveForward(0);
            robotService.moveForward(0);
            robotService.moveForward(0);
            robotService.moveForward(0);
            System.out.println(gridService.printGrid());
            robotService.attack(0);

            System.out.println(gridService.printGrid());
        } catch (ElementAlreadyPresentException e) {
            e.printStackTrace();
        } catch (RobotNotFoundException e) {
            e.printStackTrace();
        } catch (CannotMoveRobotException e) {
            e.printStackTrace();
        }

    }

}
