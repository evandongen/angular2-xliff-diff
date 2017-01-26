package nl.evandongen.xliffdiff;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Example:
 * <xliff version="1.2" xmlns="urn:oasis:names:tc:xliff:document:1.2">
 * <file source-language="en" datatype="plaintext" original="ng2.template">
 * <body>
 * <trans-unit /> ..
 * </body>
 * </file
 *
 * @author evandongen
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class I18nMessages {

	@JsonProperty("file")
	private I18nMessagesFile i18nMessagesFile;

	public I18nMessagesFile getI18nMessagesFile() {
		return i18nMessagesFile;
	}

	public void setI18nMessagesFile(I18nMessagesFile i18nMessagesFile) {
		this.i18nMessagesFile = i18nMessagesFile;
	}
}
