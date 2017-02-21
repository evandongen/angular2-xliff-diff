package nl.evandongen.xliffdiff.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * @author evandongen
 */
public class DiffResultChanged {
	@JacksonXmlElementWrapper(localName = "changed")
	@JacksonXmlProperty(localName = "trans-unit")
	private List<ChangedTransUnit> changed;

	public List<ChangedTransUnit> getChanged() {
		return changed;
	}

	public void setChanged(List<ChangedTransUnit> changed) {
		this.changed = changed;
	}
}
