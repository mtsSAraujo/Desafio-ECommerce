package com.main.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class fileReaderTableProducts {

    public static String fileReaderTableProducts() {
        StringBuilder queryTableCreation = new StringBuilder();
        String filePath = "table_products_creation.sql";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                queryTableCreation.append(line).append(" ");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return queryTableCreation.toString();
    }
}
