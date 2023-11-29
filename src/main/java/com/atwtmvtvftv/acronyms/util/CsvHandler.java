package com.atwtmvtvftv.acronyms.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.util.Collections;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class CsvHandler {
    private static final String CSV_FILE_PATH = "songs.csv";

    public static void writeCsv(List<String[]> data) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(getCsvFilePath()))) {
            writer.writeAll(data);
        }
    }

    public static List<String[]> readCsv() {
        try {
            try (CSVReader reader = new CSVReader(new FileReader(getCsvFilePath()))) {
                // Skip the header row
                reader.readNext();

                // Read the rest of the rows
                return reader.readAll();
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return Collections.emptyList(); 
        }
    }

    private static String getCsvFilePath() throws IOException {
        // Use ClassPathResource to get the absolute path of the CSV file
        File file = new ClassPathResource(CSV_FILE_PATH).getFile();
        return file.getAbsolutePath();
    }
}




