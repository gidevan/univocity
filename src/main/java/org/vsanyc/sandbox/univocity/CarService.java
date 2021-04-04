package org.vsanyc.sandbox.univocity;

import com.univocity.parsers.common.DataProcessingException;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.RowProcessorErrorHandler;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.vsanyc.sandbox.univocity.domain.Car;
import org.vsanyc.sandbox.univocity.domain.CarImproved;
import org.vsanyc.sandbox.univocity.domain.CarResult;
import org.vsanyc.sandbox.univocity.processor.CustomBeanListProcessor;
import org.vsanyc.sandbox.univocity.validation.BeanValidator;
import org.vsanyc.sandbox.univocity.validation.CarImprovedValidator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarService {

    public CarResult getCars() {
        try (Reader inputReader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("example_car_improved_error.csv"))) {

            List<String> messages = new ArrayList<>();
            CustomBeanListProcessor<CarImproved> rowProcessor = new CustomBeanListProcessor<>(CarImproved.class, new CarImprovedValidator());

            CsvParserSettings parserSettings = new CsvParserSettings();
            parserSettings.setProcessor(rowProcessor);
            parserSettings.setHeaderExtractionEnabled(true);
            parserSettings.setProcessorErrorHandler(
                    (DataProcessingException error, Object[] inputRow, ParsingContext context) -> {
                        System.out.println("Error processing row: " + Arrays.toString(inputRow));
                        String msg = "Error details: column '" + error.getColumnName() + "' (index " + error.getColumnIndex() + ") has value '" + inputRow[error.getColumnIndex()] + "'";
                        messages.add(msg);
                        System.out.println(msg);


                    });
            CsvParser parser = new CsvParser(parserSettings);
            parser.parse(inputReader);

            List<CarImproved> cars = rowProcessor.getBeans();
            messages.addAll(rowProcessor.getMessages());
            CarResult carResult = new CarResult();
            carResult.setCarImproveds(cars);
            carResult.setMessages(messages);
            return carResult;
        } catch (IOException e) {
            System.out.println("IO .Exception: " + e.getMessage());
            return null;
        }

    }
}
