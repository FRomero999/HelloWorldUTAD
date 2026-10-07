package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileService {

    void procesarArchivo(String orig, String dest){
        Path origen = Paths.get(orig);
        Path destino = Path.of(dest);
        try {
            var br = Files.newBufferedReader(destino);
            var lineas = Files.readAllLines(origen);
            Integer i = 1;
            for(String s: lineas){
                Files.write(destino,(i + " - " + s + "\n").getBytes(), StandardOpenOption.CREATE_NEW );
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
