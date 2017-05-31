package nl.evandongen.xliffdiff.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * @author evandongen
 */
public class DiffResultModifiedChanged {

	private Integer numberOfItems;

	@JacksonXmlElementWrapper(localName = "modifiedChanged")
	@JacksonXmlProperty(localName = "trans-unit")
	private List<String> replaceCommand;

	public Integer getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(Integer numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public List<String> getReplaceCommand() {
		return replaceCommand;
	}

	public void setReplaceCommand(List<String> replaceCommand) {
		this.replaceCommand = replaceCommand;
	}
}
