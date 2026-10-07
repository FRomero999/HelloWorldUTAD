package org.example;

import org.example.models.Libreria;
import org.example.models.Libro;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EjemploJackson {
    static void main() {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.isEnabled(SerializationFeature.INDENT_OUTPUT);

            Libreria libreria = xmlMapper.readValue(new File("libreria.xml"), Libreria.class);

            for (Libro libro : libreria.getLibros()) {
                System.out.println("ID: " + libro.getId());
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Año: " + libro.getAnio());
                System.out.println("Precio: " + libro.getPrecio());
                System.out.println("---");
            }

            Libro l = new Libro();
            l.setId(5);
            l.setAutor("Francisco");
            l.setAnio(2026);
            l.setPrecio(15.00);
            l.setTitulo("Aprender Java Avanzado");

            libreria.getLibros().add(l);

            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File("libreria.xml"), libreria);

            try(var bw = Files.newBufferedWriter(  Path.of("libreria.csv"))){
                for(Libro libro : libreria.getLibros()) {
                    bw.write(libro.getId()+"");
                    bw.write(",");
                    bw.write(libro.getAutor());
                    bw.write(",");
                    bw.write(libro.getTitulo());
                    bw.write(",");
                    bw.write(libro.getAnio()+"");
                    bw.write(",");
                    bw.write(libro.getPrecio()+"");
                    bw.newLine();
                }
            }

            try(BufferedReader bfr = Files.newBufferedReader( Path.of("libreria.csv"))){
                List<String> lineas = bfr.readAllLines();
                for(String s : lineas){
                    String linea[] = s.split(",");
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el XML con Jackson");
            e.printStackTrace();
        }
    }
}
