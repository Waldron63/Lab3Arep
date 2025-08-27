/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.escuelaing.httpserver;
import com.edu.escuelaing.httpserver.HttpServer;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author santiago.gualdron-r
 */
public class MicroSpringBoot {
    public static void main(String[] args) throws ClassNotFoundException, IOException, URISyntaxException{
        System.out.println("Starting MicroSpringBoot!");
        HttpServer.runServer(args);
    }
}
