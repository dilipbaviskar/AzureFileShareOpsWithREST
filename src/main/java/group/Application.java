package group;

import java.io.File;
import java.io.FileInputStream;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;

public class Application {
	public static void main(String[] args) {
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
//		System.out.println("A => " + excelColumnTitleToNumber("A"));
//		System.out.println("1 => " + excelColumnNoToTitle(1));
//
//		System.out.println("XFD => " + excelColumnTitleToNumber("XFD"));
//		System.out.println("16384 => " + excelColumnNoToTitle(16384));

		String str = "This#string%contains^special*characters&.(aa)*";

		str = str.replaceAll("[^a-zA-Z0-9]", " ");
		System.out.println(str);

		try {
			FileInputStream file = new FileInputStream(new File("C:\\tmp\\Book1.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			workbook = new XSSFWorkbook(file);

			// * : Matches any number of characters including special characters.
			// [0-9]* : Matches any number of digits.
			// [a-zA-Z]* : Matches any number of alphabets.
			// [a-zA-Z0-9]* : Matches any number of alphanumeric characters.
			// Pattern.compile(".*").matcher("abcd").matches(); // true
			// Pattern.compile("[a-zA-Z]*").matcher("abcd").matches(); // true
			// Pattern.compile("[0-9]*").matcher("01234").matches(); // true
			// Pattern.compile("[a-zA-Z0-9]*").matcher("a1b2c3").matches(); // true
			//
			// System.out.println("1Q2022 =>"+
			// Pattern.compile("\\d[Q]\\d{4}").matcher("1Q1202").matches());
			// System.out.println("2022Q1 =>"+
			// Pattern.compile("\\d{4}[Q]\\d").matcher("2022Q1").matches());
			// // Decimal no
			// System.out.println(Pattern.compile("^(10|\\d)(\\.\\d{1,2})?$").matcher("1234.56").matches());
			// System.out.println(Pattern.compile("^(10|\\d)(\\.\\d{1,2})?$").matcher("1234.56E2").matches());

			// Get first/desired sheet from the workbook
			sheet = workbook.getSheet("Sheet1");
			iterateAllTables(sheet); // sheet1

			sheet = workbook.getSheet("Sheet2");
			iterateAllTables(sheet); // sheet2
			//
			// System.out.println("\tAddress" + "\tCell");
			//
			// // Iterate through each rows one by one
			// Iterator<Row> rowIterator = sheet.iterator();
			// while (rowIterator.hasNext()) {
			// Row row = rowIterator.next();
			// // For each row, iterate through all the columns
			// Iterator<Cell> cellIterator = row.cellIterator();
			// //Cell cell = row.getCell(cellIndex,
			// Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			//
			// while (cellIterator.hasNext()) {
			// Cell cell = cellIterator.next();
			// Object value = null;
			// switch (cell.getCellType()) {
			// case NUMERIC:
			// value = String.valueOf(cell.getNumericCellValue());
			// System.out.print("\t" + value.toString());
			// break;
			// case BOOLEAN:
			// value = String.valueOf(cell.getBooleanCellValue());
			// System.out.print("\t" + value.toString());
			// break;
			// case FORMULA:
			// value = getCellValueAsString(cell, cell.getCachedFormulaResultType());
			// System.out.print("\t" + value.toString());
			// break;
			// case STRING:
			// value = cell.getStringCellValue();
			// System.out.print("\t" + value.toString());
			// break;
			// default:
			// value = StringUtils.EMPTY;
			// System.out.print("\t" + value.toString());
			// break;
			// }
			// }
			// System.out.println();
			// }
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			workbook.unLockStructure();
			workbook.unLockRevision();
			workbook.unLockWindows();
			workbook = null;
			sheet = null;
		}

	}

	static String getCellValueAsString(Cell cell, CellType cellType) {
		switch (cellType) {
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case FORMULA:
			return getCellValueAsString(cell, cell.getCachedFormulaResultType());
		case STRING:
			return cell.getStringCellValue();
		default:
			return StringUtils.EMPTY;
		}
	}

	static void iterateAllTables(XSSFSheet sheet) {
		List<XSSFTable> tables = sheet.getTables();
		System.out.println("**********************Sheet Name=" + sheet.getSheetName() + "**********************");
		for (XSSFTable t : tables) {
			System.out.println("\t ---------------------------------");
			System.out.println("\t getDisplayName=" + t.getDisplayName());
			System.out.println("\t getName=" + t.getName());

			AreaReference areaReference = t.getCellReferences();
			System.out.println("\t getAreaReference=" + t.getCellReferences().getFirstCell().formatAsString() + ":"
					+ t.getCellReferences().getLastCell().formatAsString());
			CellRangeAddress[] regions = {
					CellRangeAddress.valueOf(t.getCellReferences().getFirstCell().formatAsString() + ":"
							+ t.getCellReferences().getLastCell().formatAsString()) };

			String[] startCellAddress = t.getCellReferences().getFirstCell().formatAsString().split("(?<=\\D)(?=\\d)");
			System.out.println("StartCell 	ColumnNo=" + (excelColumnTitleToNumber(startCellAddress[0]) - 1) + " RowNo="
					+ startCellAddress[1]);

			String[] lastCellAddress = t.getCellReferences().getLastCell().formatAsString().split("(?<=\\D)(?=\\d)");
//			System.out.println("LastCell 	ColumnNo=" + (excelColumnTitleToNumber(lastCellAddress[0]) - 1) + " RowNo="
//					+ lastCellAddress[1]);

			final Map<String, Integer> columnNameIndexMap = new LinkedHashMap<String, Integer>();
			final Map<Integer, String> columnIndexNameMap = new LinkedHashMap<Integer, String>();
			Set<SurveyMetric> surveyMetricDataSet = new LinkedHashSet<SurveyMetric>();
			String subClassification = "";
			for (int startRowIndex = t.getStartRowIndex(),
					endRowIndex = t.getEndRowIndex(); startRowIndex <= endRowIndex; startRowIndex++) {
				SurveyMetric record = null;

				for (int startColIndex = t.getStartColIndex(),
						endColIndex = t.getEndColIndex(); startColIndex <= endColIndex; startColIndex++) {
					String value = readCellData(sheet, startRowIndex, startColIndex);
					if (startRowIndex == t.getStartRowIndex()) {
						columnNameIndexMap.putIfAbsent(value, startColIndex);
						columnIndexNameMap.putIfAbsent(startColIndex, value);
						if (startColIndex == t.getStartColIndex() && value != null)
							subClassification = value;

					}
					if (startRowIndex > t.getStartRowIndex() && startColIndex == t.getStartColIndex()) {
						record = new SurveyMetric("OFF010", 2022, 1, OverallCapRatesTableEnum.getSectionName(),
								subClassification);
					}

					if (startRowIndex > t.getStartRowIndex()  && columnIndexNameMap.get(startColIndex) != null && (value != null && !value.isEmpty())
							&& record != null) {
						String currentColumnn = columnIndexNameMap.get(startColIndex);
						switch (currentColumnn.toLowerCase()) {
						case "quarterid":
							record.setStatisticName(value);
							;
							break;
						case "low":
							record.setLow(value);
							break;
						case "high":
							record.setHigh(value);
							break;
						case "average":
							record.setAverage(value);
							break;
						case "value":
							record.setValue(value);
							break;
						default:
						}

					}

				}
				if (record != null && record.isSurveyMetricDataValid()) {
					System.out.println("added record successfully" + record);
					surveyMetricDataSet.add(record);
				}

			}

 
		}
		System.out.println("**********************Sheet Name=" + sheet.getSheetName() + "**********************");

	}

	/**
	 * 
	 * @param sheet
	 * @param vRow    Row no starts with 0
	 * @param vColumn column no starts with 0
	 * @return
	 */
	public static String readCellData(Sheet sheet, int vRow, int vColumn) {
		Object value = ""; // variable for storing the cell value
		// getting the XSSFSheet object at given index
		Row row = sheet.getRow(vRow);
		Cell cell = row.getCell(vColumn, MissingCellPolicy.RETURN_BLANK_AS_NULL);
		NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
		nf.setMaximumFractionDigits(1);
		nf.setMinimumFractionDigits(2);
		nf.setRoundingMode(RoundingMode.UP);
		if (cell != null) {
			switch (cell.getCellType()) {
			case NUMERIC:
				value = String.valueOf(nf.format(cell.getNumericCellValue()));
				break;
			case BOOLEAN:
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case FORMULA:
				value = getCellValueAsString(cell, cell.getCachedFormulaResultType());
				break;
			case STRING:
				value = cell.getStringCellValue();
				break;
			default:
				value = StringUtils.EMPTY;
				break;
			}
		}
		return (String) value;
	}

	static void generateCellName(int columnNo, int rowNo) {
		int maxNoOfColumns = 16384;
		String currentLabel = "";
		char ch = 'A';
		int ascii = ch;
		// You can also cast char to int
		int castAscii = (int) ch;

		System.out.println("The ASCII value of " + ch + " is: " + ascii);

		for (int start = 0; start < maxNoOfColumns; start++)
			for (char c = 'A'; c <= 'Z'; ++c) {

				// System.out.print(c + " ");
			}
	}

	boolean isAlphabet(char c) {
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @param columnNumber Excel column index starts with 1.
	 * @return excel column label
	 */
	static String excelColumnNoToTitle(int columnNumber) {
		StringBuilder columnName = new StringBuilder();
		while (columnNumber > 0) {
			int rem = columnNumber % 26;
			if (rem == 0) {
				columnName.append("Z");
				columnNumber = (columnNumber / 26) - 1;
			} else {
				columnName.append((char) ((rem - 1) + 'A'));
				columnNumber = columnNumber / 26;
			}
		}
		return columnName.reverse().toString();
	}

	/**
	 * 
	 * @param s Excel column title
	 * @return columnNo
	 */
	static int excelColumnTitleToNumber(String s) {
		// This process is similar to
		// binary-to-decimal conversion
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			result *= 26;
			result += s.charAt(i) - 'A' + 1;
		}
		return result;
	}
}
