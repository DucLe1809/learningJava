package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class DatabaseConfigSingleton {
    private String host;
    private int port;
    private String user;
    private String password;

    private static volatile DatabaseConfigSingleton obj;

    private DatabaseConfigSingleton() {}

    public static DatabaseConfigSingleton getInstance()
    {
        if (obj == null)
        {
            synchronized (DatabaseConfigSingleton.class)
            {
                if (obj == null)
                {
                    obj = new DatabaseConfigSingleton();
                }
            }
        }
        return obj;
    }

    public void loadFromFile(String path) {
        try
        {
            File myFile = new File(path);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine().trim();

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
                        this.host = value;
                        break;
                    case "port":
                        this.port = Integer.parseInt(value);
                        break;
                    case "user":
                        this.user = value;
                        break;
                    case "password":
                        this.password = value;
                        break;
                    default:
                        System.out.println("Invalid key: " + key);
                        break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
        }
    }

    // Getter function
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }


}
