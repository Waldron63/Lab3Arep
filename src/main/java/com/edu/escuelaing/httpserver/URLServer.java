/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.escuelaing.httpserver;

import java.net.*;
import java.net.MalformedURLException;

/**
 *
 * @author santiago.gualdron-r
 */
public class URLServer {
    public static void main(String[] args)throws MalformedURLException{
        URL myUrl = new URL("http://arep.curso.escuelaing.edu.co:8080/respuestasexamenes/parcial.html/respuestas?estado=true&val=3&color=rojo#publicaciones");
        System.out.println("Protocol: " + myUrl.getProtocol());
        System.out.println("Authority: " + myUrl.getAuthority());
        System.out.println("Host: " + myUrl.getHost());
        System.out.println("Port: " + myUrl.getPort());
        System.out.println("Path: " + myUrl.getPath());
        System.out.println("Query: " + myUrl.getQuery());
        System.out.println("File: " + myUrl.getFile());
        System.out.println("Ref: " + myUrl.getRef());
    }
}
