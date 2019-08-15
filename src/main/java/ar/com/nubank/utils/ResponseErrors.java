package ar.com.nubank.utils;

import javax.ws.rs.core.Response;

public class ResponseErrors {

    public static Response gridNotReady(){
        return Response.status(500).entity("Grid not ready").build();
    }

    public static Response elementAlreadyPresent(int row, int col){
        return Response.status(500).entity("Element already present in "+row+","+col).build();
    }

    public static Response outOfBounds(){
        return Response.status(500).entity("Element added out of bounds").build();
    }
}
