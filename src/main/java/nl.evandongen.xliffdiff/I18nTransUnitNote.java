package nl.evandongen.xliffdiff;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * <note priority="1" from="description">part of day - morning</note>
 *
 * @author evandongen
 */
public class I18nTransUnitNote {

	@JacksonXmlProperty(isAttribute = true)
	private Integer priority;

	@JacksonXmlProperty(isAttribute = true)
	private String from;

	@JacksonXmlText
	private String value;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
