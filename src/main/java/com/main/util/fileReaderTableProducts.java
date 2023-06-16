package com.main.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class fileReaderTableProducts {

    public static String fileReaderTableProducts() {
        StringBuilder queryTableCreation = new StringBuilder();
        String filePath = "table_products_creation.sql"; // Substitua pelo caminho do arquivo que deseja ler
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                queryTableCreation.append(line).append(" ");// Faça algo com cada linha lida, neste exemplo, apenas imprimimos no console
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return queryTableCreation.toString();
    }
}
