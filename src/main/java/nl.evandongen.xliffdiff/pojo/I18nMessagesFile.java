package nl.evandongen.xliffdiff.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * @author evandongen
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class I18nMessagesFile {

	@JacksonXmlElementWrapper(localName = "body")
	@JacksonXmlProperty(localName = "trans-unit")
	private List<I18nTransUnit> body;

	public List<I18nTransUnit> getBody() {
		return body;
	}

	public void setBody(List<I18nTransUnit> body) {
		this.body = body;
	}
}
