package ar.com.nubank.utils;

import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;

public class ResponseErrors {

    public static Response gridNotReady(){
        return Response.status(HttpStatus.FORBIDDEN.value()).entity("Grid not ready").build();
    }

    public static Response elementAlreadyPresent(int row, int col){
        return Response.status(HttpStatus.FORBIDDEN.value()).entity("Element already present in "+row+","+col).build();
    }

    public static Response movedOutOfBounds(){
        return Response.status(HttpStatus.FORBIDDEN.value()).entity("Element moved out of bounds").build();
    }

    public static Response cannotAddElement() {
        return Response.status(HttpStatus.FORBIDDEN.value()).entity("Element added out of bounds").build();
    }

    public static Response elementNotFoundInPosition(int row, int col) {
        return Response.status(HttpStatus.FORBIDDEN.value()).entity("Element not present in "+row+","+col).build();
    }

    public static Response cannotClearElement() {
        return Response.status(HttpStatus.FORBIDDEN.value()).entity("Cannot clear element from grid").build();
    }

    public static Response cannotMoveElement() {
        return Response.status(HttpStatus.FORBIDDEN.value()).entity("Cannot move element").build();
    }
}
