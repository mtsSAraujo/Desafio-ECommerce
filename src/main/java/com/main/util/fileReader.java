package com.main.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class fileReader {

    public static StringBuilder queryCreation;
    public static void main(String[] args) {
        String filePath = "schema_control.sql"; // Substitua pelo caminho do arquivo que deseja ler

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                 queryCreation.append(line).append(" ");// Faça algo com cada linha lida, neste exemplo, apenas imprimimos no console
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}


