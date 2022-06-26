package group;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum OverallCapRatesTableEnum {

	MARKET_AS_WHOLE("Market (as whole)"), SUBURBAN("SUBURBAN)"), CBD("CBD"),

	LOW("low", 2, true), HIGH("high", 3, true), AVERAGE("average", 4, true);

	private int columnIndex = -1;
	private int rowIndex = -1;
	private final String propertyName;

	private final boolean isColumnName;

	private final Map<String, Integer> columnNameIndexMap = new LinkedHashMap<String, Integer>();
	private final Map<Integer, String> columnIndexNameMap = new LinkedHashMap<Integer, String>();
	private final List<String> subClassificationNameList = new LinkedList<String>();

	private static final String sectionName = "OverallCapRates";

	OverallCapRatesTableEnum(String _propertyName) {
		this.columnIndex = -1;
		this.rowIndex = -1;
		this.isColumnName = false;
		this.propertyName = _propertyName;

	}

	OverallCapRatesTableEnum(String _propertyName, int _columnIndex, int _rowIndex, boolean isColumnName) {
		this.rowIndex = _rowIndex;
		this.columnIndex = _columnIndex;
		this.isColumnName = isColumnName;
		this.propertyName = _propertyName;

		if (isColumnName) {
			columnNameIndexMap.putIfAbsent(_propertyName, _columnIndex);
			columnIndexNameMap.putIfAbsent(_columnIndex, _propertyName);
		} else {
			subClassificationNameList.add(_propertyName);
		}

	}

	OverallCapRatesTableEnum(String _propertyName, int _columnIndex, boolean isColumnName) {
		this.rowIndex = -1;
		this.isColumnName = isColumnName;
		this.propertyName = _propertyName;
		this.columnIndex = _columnIndex;
		if (isColumnName) {
			this.columnNameIndexMap.putIfAbsent(_propertyName, _columnIndex);
			this.columnIndexNameMap.putIfAbsent(_columnIndex, _propertyName);
		}
	}

	public String getPropertyName()
	{
		return propertyName;
	}
	public static String getSectionName() {
		return sectionName;
	}

	public String getColumnNameByIndex(int idx) {
		return columnIndexNameMap.get(idx);
	}

	public int getIndexForColumnName(String _propertyName) {
		return columnNameIndexMap.get(_propertyName);
	}

	public List<String> getSubClassificationName() {
		return Collections.unmodifiableList(subClassificationNameList);
	}
}
