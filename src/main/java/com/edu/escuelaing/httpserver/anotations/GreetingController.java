/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.escuelaing.httpserver.anotations;

import java.util.concurrent.atomic.AtomicLong;
import com.edu.escuelaing.httpserver.anotations.RestController;
import com.edu.escuelaing.httpserver.anotations.GetMapping;
import com.edu.escuelaing.httpserver.anotations.RequestParam;

/**
 *
 * @author santiago.gualdron-r
 */
@RestController
public class GreetingController {
    private static final String template =  "Hello, %s!";
    private  final AtomicLong counter = new AtomicLong();
    
    @GetMapping("/greeting")
    public static String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	return "Hola " + name;
    }
}
