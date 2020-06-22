package com.testing.os.data.readers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class CSVFileReader {
	private CSVReader reader;

	public CSVReader getReader() {
		return reader;
	}

	public void setReader(CSVReader reader) {
		this.reader = reader;
	}
	
	public CSVFileReader(String fileName) throws FileNotFoundException {
		this.reader = new CSVReaderBuilder(new FileReader(fileName)).withSkipLines(1).build();
	}

	public List<String[]> readAll() throws IOException, CsvException {
		return this.reader.readAll();
	}
	
	public String[] readNext() throws CsvValidationException, IOException {
		return reader.readNext();
	}
}
