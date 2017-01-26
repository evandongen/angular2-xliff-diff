package nl.evandongen.xliffdiff.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * @author evandongen
 */
public class DiffResultRemoved {

	@JacksonXmlElementWrapper(localName = "removed")
	@JacksonXmlProperty(localName = "trans-unit")
	private List<I18nTransUnit> removed;

	public List<I18nTransUnit> getRemoved() {
		return removed;
	}

	public void setRemoved(List<I18nTransUnit> removed) {
		this.removed = removed;
	}
}
