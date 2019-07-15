package com.rabobank.csp.testcase;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.rabobank.csp.domain.Record;
import com.rabobank.csp.services.ExtractorService;
import com.rabobank.csp.services.ExtractorServiceImpl;
import com.rabobank.csp.services.ValidatorService;
import com.rabobank.csp.services.ValidatorServiceImpl;

import junit.framework.Assert;

/**
 * 
 * @author Laxman Jadi
 * 
 *
 */
public class CSPTestCases {

	@Test
	public void testExtractStatmentFromCSVTestCase() {
		ExtractorService extractorServiceImpl = new ExtractorServiceImpl();
		try {
			Path path = Paths.get("records.csv");
			long lineCount = Files.lines(path).count();
			List<Record> records = extractorServiceImpl.extractCustomerStatmentFromCSV(new File("records.csv"));
			assertEquals(lineCount - 1, records.size());
		} catch (IOException e) {
			Assert.fail("Error in processing file : " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testExtractStatmentFromXMLTestCase() {
		ExtractorService extractorServiceImpl = new ExtractorServiceImpl();
		File inputFile = new File("records.xml");
		try {
			int lineCount = 10;
			List<Record> records = extractorServiceImpl.extractCustomerStatmentFromXML(inputFile);
			assertEquals(lineCount, records.size());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testGetRepeatedRecordsTestCase() {
		List<Record> inputList = Arrays.asList(
				new Record(new BigInteger("112806"), "NL27SNSB0917829871", new BigDecimal(91.23), new BigDecimal(+15.57), "Clothes for Willem Dekker", new BigDecimal(106.8)),
				new Record(new BigInteger("112806"), "NL69ABNA0433647324", new BigDecimal(90.83), new BigDecimal(-10.91), "Clothes for Richard de Vries", new BigDecimal(79.92)));
		ValidatorService validatorServiceImpl = new ValidatorServiceImpl();
		List<Record> repeatedRecords = validatorServiceImpl.getRepeatedRecords(inputList);
		assertEquals(inputList.size(), repeatedRecords.size());

	}

	@Test
	public void testGetRepeatedRecordsTestCaseWithOutRepeatedRecords() {
		List<Record> inputList = Arrays.asList(
				new Record(new BigInteger("112806"), "NL27SNSB0917829871", new BigDecimal(91.23), new BigDecimal(+15.57), "Clothes for Willem Dekker", new BigDecimal(106.8)),
				new Record(new BigInteger("195446"), "NL74ABNA0248990274", new BigDecimal(26.32), new BigDecimal(+48.98), "Flowers for Willem Dekker", new BigDecimal(75.3)));
		ValidatorService validatorServiceImpl = new ValidatorServiceImpl();
		List<Record> repeatedRecords = validatorServiceImpl.getRepeatedRecords(inputList);
		assertEquals(0, repeatedRecords.size());
	}

	@Test
	public void testGetEndBalanceInvalidRecordsTestCaseWithWrongValue() {
		List<Record> inputList = Arrays.asList(
				new Record(new BigInteger("112806"), "NL27SNSB0917829871", new BigDecimal(91.23), new BigDecimal(+15.57), "Clothes for Willem Dekker", new BigDecimal(106.8)),
				new Record(new BigInteger("195446"), "NL74ABNA0248990274", new BigDecimal(26.32), new BigDecimal(+48.98), "Flowers for Willem Dekker", new BigDecimal(75.3)));
		ValidatorService validatorServiceImpl = new ValidatorServiceImpl();
		List<Record> endBalanceInvalidRecords = validatorServiceImpl.getEndBalanceInvalidRecords(inputList);
		assertEquals(inputList.size(), endBalanceInvalidRecords.size());

	}

}