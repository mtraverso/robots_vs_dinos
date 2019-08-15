package ar.com.nubank;

import ar.com.nubank.exceptions.CannotMoveRobotException;
import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.exceptions.RobotNotFoundException;
import ar.com.nubank.services.DinosaurService;
import ar.com.nubank.services.GridService;
import ar.com.nubank.services.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;


@SpringBootApplication
public class Main {

    public static final String BASE_URI = "http://localhost:8080/nubank/";



    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class);


        //execute();

    }




    @Autowired
    GridService gridService;

    @Autowired
    RobotService robotService;

    @Autowired
    DinosaurService dinosaurService;

    //@Bean
    public void execute(){


        gridService.createGrid(10,10);

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
        } catch (ElementAlreadyPresentException | RobotNotFoundException | CannotMoveRobotException e) {
            e.printStackTrace();
        }

    }

}
