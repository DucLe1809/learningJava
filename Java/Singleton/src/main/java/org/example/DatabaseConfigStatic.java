package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatabaseConfigStatic {
    public static String host;
    public static int port;
    public static String user;
    public static String password;

    public static void loadFromFile(String filename) {
        try {
            File myFile = new File(filename);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("=", 2);
                if (parts.length < 2) {
                    continue;
                }
                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (key) {
                    case "host":
                        host = value;
                        break;
                    case "port":
                        port = Integer.parseInt(value);
                        break;
                    case "user":
                        user = value;
                        break;
                    case "password":
                        password = value;
                        break;
                    default:
                        System.out.println("Invalid key: " + key);
                        break;
                }
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }


    }
}
