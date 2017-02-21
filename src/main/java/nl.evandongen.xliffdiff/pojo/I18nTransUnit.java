package nl.evandongen.xliffdiff.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Example:
 * <trans-unit id="575898911c3911b6e5807d09e21cadfffc5f6664" datatype="html">
 * <source>Please enter an e-mailadress</source>
 * <target/>
 * <note priority="1" from="description">Error: enter e-mail</note>
 * </trans-unit>
 *
 * @author evandongen
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class I18nTransUnit {

	@JacksonXmlProperty(isAttribute = true)
	private String id;

	@JacksonXmlProperty(isAttribute = true)
	private String datatype;

	private String source;

	private String target;

	private String note;

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
