package com.estrella.principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelos.Conversion;
import modelos.ConversionApi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        //Creamos un nuevo scanner
        Scanner lectura = new Scanner(System.in);
        //Creamos gson
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setPrettyPrinting() //Pare poder visualizar mejor el archivo
                .create();

        while(true) {
            //---------Menú--------------
            System.out.println("****************************************");
            System.out.println("Bienvenido a tu conversor de moneda 💵");
            System.out.println("Aquí tienes algunos códigos de monedas más usados:" +
                    "\nUSD -> Dólar estadounidesnse" +
                    "\nMXN -> Peso mexicano" +
                    "\nCAD -> Dólar Canadiense" +
                    "\nBRL -> Real brasileño" +
                    "\nEUR -> Euro" +
                    "\nCOP -> Peso colombiano" +
                    "\nARS -> Peso argentino" +
                    "\nAquí puedes encontrar más codigos de monedas:" +
                    "\nhttps://www.exchangerate-api.com/docs/supported-currencies" +
                    "\n+++Escribe salir para terminar el programa+++");
            System.out.println("****************************************");
            System.out.println("Dime la moneda que deseas convertir:");
            var moneda1 = lectura.nextLine();

            //condicion de salida
            if(moneda1.equalsIgnoreCase("salir")){
                break;
            }

            System.out.println("A qué moneda la deseas convertir:");
            var moneda2 = lectura.nextLine();

            if(moneda2.equalsIgnoreCase("salir")){
                break;
            }

            System.out.println("Escribe el monto que deseas convertir:");
            var monto = lectura.nextLine();

            if(monto.equalsIgnoreCase("salir")){
                break;
            }

            String direccion = "https://v6.exchangerate-api.com/v6/08a65928d3d326d7288a9518/pair/"
                    + moneda1 + "/" + moneda2 + "/" + monto;


            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                //Respuesta en formato JSON
                String json = response.body();
                //System.out.println(json);

                ConversionApi miConversionApi = gson.fromJson(json, ConversionApi.class);
                //System.out.println(miConversionApi);

                Conversion miConversion = new Conversion(miConversionApi);
                System.out.println("----------Resultado----------");
                System.out.println(monto + ' ' + miConversion.getMonedaInicio()
                        + " son " + miConversion.getTotal() + ' ' + miConversion.getMonedaFin() + '\n');

            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e);
            }
        }
    }
}
