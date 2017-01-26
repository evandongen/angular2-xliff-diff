package nl.evandongen.xliffdiff;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * @author evandongen
 */
public class DiffResultAdded {
	@JacksonXmlElementWrapper(localName = "added")
	@JacksonXmlProperty(localName = "trans-unit")
	private List<I18nTransUnit> added;

	public List<I18nTransUnit> getAdded() {
		return added;
	}

	public void setAdded(List<I18nTransUnit> added) {
		this.added = added;
	}
}
