package org.vsanyc.sandbox.univocity;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.vsanyc.sandbox.univocity.domain.Car;
import org.vsanyc.sandbox.univocity.domain.CarResult;

import java.io.*;
import java.util.List;

public class UnivocityMain {

    public static void main(String[] args) {
        System.out.println("Start...");
        UnivocityMain main = new UnivocityMain();
        List<String[]> cars1 = main.parseSimpleExample();
        List<Record> cars2 = main.getRecords();
        List<Car> cars3 = main.getCars();
        List<Car> carsWithoutProcessing = main.getWrongCarsWithoutProcessing();

        CarService carService = new CarService();
        CarResult carResult1 = carService.getCars();
        System.out.println("Stop");
    }

    private List<Car> getCars() {
        try (Reader inputReader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("example.csv"))) {


            BeanListProcessor<Car> rowProcessor = new BeanListProcessor<Car>(Car.class);

            CsvParserSettings parserSettings = new CsvParserSettings();
            //parserSettings.getFormat().setLineSeparator("\n");
            parserSettings.setProcessor(rowProcessor);
            parserSettings.setHeaderExtractionEnabled(true);

            CsvParser parser = new CsvParser(parserSettings);
            parser.parse(inputReader);

            return rowProcessor.getBeans();
        } catch (IOException e) {
            System.out.println("IO .Exception: " + e.getMessage());
            return null;
        }


    }

    private List<Car> getWrongCarsWithoutProcessing() {
        try (Reader inputReader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("example_error.csv"))) {


            BeanListProcessor<Car> rowProcessor = new BeanListProcessor<>(Car.class);

            CsvParserSettings parserSettings = new CsvParserSettings();
            parserSettings.setProcessor(rowProcessor);
            parserSettings.setHeaderExtractionEnabled(true);

            CsvParser parser = new CsvParser(parserSettings);
            parser.parse(inputReader);

            return rowProcessor.getBeans();
        } catch (IOException e) {
            System.out.println("IO .Exception: " + e.getMessage());
            return null;
        }


    }

    private List<Record> getRecords() {
        try (Reader inputReader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("example.csv"))) {
            CsvParserSettings settings = new CsvParserSettings();
            settings.setHeaderExtractionEnabled(true);
            CsvParser parser = new CsvParser(settings);
            return parser.parseAllRecords(inputReader);
        } catch (IOException e) {
            System.out.println("IO .Exception: " + e.getMessage());
            return null;
        }

    }

    private List<String[]> parseSimpleExample() {


        try (Reader inputReader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("example.csv"))) {
            CsvParserSettings settings = new CsvParserSettings();

            CsvParser parser = new CsvParser(settings);
            List<String[]> parsedRows = parser.parseAll(inputReader);
            return parsedRows;
        } catch (IOException e) {
            System.out.println("IO .Exception: " + e.getMessage());
            return null;
        }

    }
}
