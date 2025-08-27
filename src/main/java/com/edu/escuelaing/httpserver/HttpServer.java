/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.edu.escuelaing.httpserver;

import com.edu.escuelaing.httpserver.anotations.RestController;
import com.edu.escuelaing.httpserver.anotations.GetMapping;
import com.edu.escuelaing.httpserver.anotations.RequestParam;

import java.net.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author santiago.gualdron-r
 */
public class HttpServer {
    
    public static Map<String, Method> services = new HashMap<>();
    private static final String directory = "src/main/java/com/edu/escuelaing/httpserver/resources";

    public static void loadServices() throws ClassNotFoundException{
        try{
            Class c = Class.forName("com.edu.escuelaing.httpserver.anotations.GreetingController");
            
            if (c.isAnnotationPresent(RestController.class)){
                Method[] methods = c.getDeclaredMethods();
                for (Method m : methods){
                    if (m.isAnnotationPresent(GetMapping.class)){
                        String mapping = m.getAnnotation(GetMapping.class).value();
                        services.put(mapping, m);
                    }
                }
            }
        }catch(ClassNotFoundException ex){
        
        }
    }
    
    public static void runServer(String[] args) throws IOException, URISyntaxException, ClassNotFoundException {
        loadServices();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;

        boolean running = true;
        while (running) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;

            String path = null;
            boolean firstline = true;
            URI requri = null;

            while ((inputLine = in.readLine()) != null) {
                if (firstline) {
                    requri = new URI(inputLine.split(" ")[1]);
                    System.out.println("Path: " + requri.getPath());
                    firstline = false;
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            if (requri.getPath().startsWith("/app")) {
                outputLine = invokeService(requri);
            } else {
                //Leo del disco

                outputLine = serveStaticFile(requri.getPath());
            }
            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    private static String helloService(URI requesturi) {
        //Encabezado con content-type adaptado para retornar un JSON        
        String response = "HTTP/1.1 200 OK\r\n"
                + "content-type: application/json\r\n"
                + "\r\n";
        //Extrae el valor de "name" desde el query.
        String name = requesturi.getQuery().split("=")[1]; //name=jhon

        //Crea la respuesta completa                
        response = response + "{\"mensaje\": \"Hola " + name + "\"}";
        return response;
    }

    private static String serveStaticFile(String path) {
        if (path.equals("/")) {
            path = "/index.html";
        }

        File file = new File(directory + path);
        if (!file.exists() || file.isDirectory()) {
            return error404();
        }

        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            String contentType = getContentType(path);
            return "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: " + contentType + "\r\n" +
                    "Content-Length: " + fileContent.length + "\r\n" +
                    "\r\n" +
                    new String(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            return error500();
        }
    }

    private static String invokeService(URI requri) {
        String header = "HTTP/1.1 200 OK\r\n"
                + "content-type: text/html\r\n"
                + "\r\n";
        try{
            HttpRequest req = new HttpRequest(requri);
            HttpResponse res = new HttpResponse();
            String servicePath = requri.getPath().substring(4);
            Method m = services.get(servicePath);
            
            String[] argsValues;
            RequestParam rp = (RequestParam) m.getParameterAnnotations()[0][0];
            if(requri.getQuery() == null){
                argsValues = new String[]{rp.defaultValue()};
            }else{
                String queryParamName = rp.value();
                argsValues = new String[]{req.getValues(queryParamName)};
            }
            return header + m.invoke(null, argsValues);
        }catch(IllegalAccessException ex){
            System.getLogger(HttpServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }catch (InvocationTargetException ex){
            System.getLogger(HttpServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return header + "Error!";
    }

    public static void staticfiles(String localFilesPath) {
    }
    
    private static String error404() {
        return "HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<h1>404 Not Found</h1>";
    }
    
    private static String error500() {
        return "HTTP/1.1 500 Internal Server Error\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<h1>500 Internal Server Error</h1>";
    }

    private static String getContentType(String fileName) {
        if (fileName.endsWith(".html")) return "text/html";
        if (fileName.endsWith(".css")) return "text/css";
        if (fileName.endsWith(".js")) return "application/javascript";
        if (fileName.endsWith(".png")) return "image/png";
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) return "image/jpeg";
        if (fileName.endsWith(".gif")) return "image/gif";
        return "text/plain";
    }
}
