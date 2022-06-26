package group;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyMetric {
	@Override
	public String toString() {
		return "SurveyMetric [quarterId=" + quarterId + ", year=" + year + ", marketId=" + marketId + ", sectionName="
				+ sectionName + ", statisticName=" + statisticName + ", subClassification=" + subClassification
				+ ", low=" + low + ", high=" + high + ", average=" + average + ", value=" + value + "]";
	}

	public SurveyMetric(String _marketId, int _year, int _quarterId, String _sectionName, String _subClassification) {
		this.marketId = _marketId;
		this.year = _year;
		this.quarterId = _quarterId;
		this.sectionName=_sectionName;
		this.subClassification = _subClassification;
	}

	public SurveyMetric(String _marketId, int _year, int _quarterId, String _sectionName) {
		 this(_marketId,_year,_quarterId,_sectionName,"");
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(marketId, statisticName, quarterId, sectionName, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SurveyMetric)) {
			return false;
		}
		SurveyMetric other = (SurveyMetric) obj;
		return Objects.equals(marketId, other.marketId) && Objects.equals(statisticName, other.statisticName)
				&& quarterId == other.quarterId && Objects.equals(sectionName, other.sectionName) && year == other.year;
	}

	public boolean isSurveyMetricDataValid()
	{
		return (((value !=null)||(low!=null)||(high!=null)||(average!=null)));
	}

	@JsonProperty("quarterId")
	public int getQuarterId() {
		return this.quarterId;
	}

	public void setQuarterId(int quarterId) {
		this.quarterId = quarterId;
	}

	int quarterId;

	@JsonProperty("year")
	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	int year;

	@JsonProperty("marketId")
	public String getMarketId() {
		return this.marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	String marketId;

	@JsonProperty("sectionName")
	public String getSectionName() {
		return this.sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	String sectionName;

	@JsonProperty("statisticName")
	public String getStatisticName() {
		return this.statisticName;
	}

	public void setStatisticName(String metricName) {
		this.statisticName = metricName;
	}

	String statisticName;

	@JsonProperty("subClassification")
	public String getSubClassification() {
		return this.subClassification;
	}

	public void setSubClassification(String subClassification) {
		this.subClassification = subClassification;
	}

	String subClassification;

	@JsonProperty("low")
	public String getLow() {
		return this.low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	String low;

	@JsonProperty("high")
	public String getHigh() {
		return this.high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	String high;

	@JsonProperty("average")
	public String getAverage() {
		return this.average;
	}

	public void setAverage(String average) {
		this.average = average;
	}

	String average;

	@JsonProperty("value")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	String value;
}
