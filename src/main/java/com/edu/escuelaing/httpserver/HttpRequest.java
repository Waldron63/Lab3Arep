/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.escuelaing.httpserver;

import java.net.URI;

/**
 *
 * @author santiago.gualdron-r
 */
public class HttpRequest {
    
    URI reqUri = null;
    
    HttpRequest(URI requestUri){
        reqUri = requestUri;
    }
    
    public String getValues(String paramName){
        String paramValue = reqUri.getQuery().split("=")[1];
        return paramValue;
    }
    
}
