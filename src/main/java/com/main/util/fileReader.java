package com.main.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class fileReader {

    public static String fileReader() {
        String filePath = "schema_control.sql";
        StringBuilder queryCreation = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                 queryCreation.append(line).append(" ");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return queryCreation.toString();
    }
}


