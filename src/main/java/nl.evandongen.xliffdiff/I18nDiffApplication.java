package nl.evandongen.xliffdiff;

/**
 * Application entry point for {@link I18nDiffService}
 *
 * @author evandongen
 */
public class I18nDiffApplication {

	public static void main(String[] args) {
		// These can be passed as parameters when running the class
		System.setProperty("latestFile", "messages.latest.xlf");
		System.setProperty("otherFile", "messages.previous.xlf");

		String latestFile = System.getProperty("latestFile");
		String otherFile = System.getProperty("otherFile");

		I18nDiffService i18nDiffService = new I18nDiffService();
		i18nDiffService.compareFiles(latestFile, otherFile);
	}
}