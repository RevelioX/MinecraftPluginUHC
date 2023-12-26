package org.example.tok.uhcdragon;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.example.tok.uhcdragon.auxiliar.Tupla;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvGestor {

    public static List<Tupla> leerCSV(String filePath) throws IOException, CsvException {
        List<Tupla> tuplas = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = reader.readAll();

            for (String[] record : records) {
                if (record.length == 2) {
                    String idJugador = record[0].trim();
                    int cantVidas = Integer.parseInt(record[1].trim());

                    Tupla tupla = new Tupla(idJugador, cantVidas);
                    tuplas.add(tupla);
                }
            }
        }

        return tuplas;
    }

    public void agregarLinea(String filepath, String idJugador, int cantVidas) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filepath, true))) {
            String[] nuevaLinea = {idJugador, Integer.toString(cantVidas)};
            writer.writeNext(nuevaLinea);
        }
    }

    public void cambiarVidaJugador(String filePath, String idJugador, int nuevaCantidadVidas) throws IOException, CsvException {
        List<Tupla> datos = leerCSV(filePath);
        boolean jugadorEncontrado = false;

        for (Tupla tupla : datos) {
            if (tupla.getIdJugador().equals(idJugador)) {
                jugadorEncontrado = true;
                tupla.setCantVidas(nuevaCantidadVidas);
                break;
            }
        }

        if (!jugadorEncontrado) {
            // Si el jugador no se encuentra, puedes manejarlo según tus necesidades
            System.out.println("Jugador no encontrado: " + idJugador);
        }

        // Actualizar el archivo CSV con los nuevos datos
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            for (Tupla tupla : datos) {
                String[] nuevaLinea = {tupla.getIdJugador(), Integer.toString(tupla.getCantVidas())};
                writer.writeNext(nuevaLinea);
            }
        }
    }
}
