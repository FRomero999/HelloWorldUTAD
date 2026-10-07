package org.example;

import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EjemplosIO {
    static void main() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println(String.format("Hello and welcome!"));

        File pom = new File("pom.xml");

        System.out.println(pom.exists());
        System.out.println(pom.isFile());
        System.out.println( "Tamaño del archivo: "+pom.length() );
        System.out.println("---");



        File dir = new File(".");
        if(dir.isDirectory()) {
            for (String s : dir.list()) {
                System.out.println(s);
            }
        }

        var f = new File("test.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(BufferedReader bfr = new BufferedReader(new FileReader(pom));
            BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"))) {
            String s;
            Integer i = 1;
            while( (s = bfr.readLine())!=null ){
                bw.write(i+" - ");
                bw.write(s);
                bw.newLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
