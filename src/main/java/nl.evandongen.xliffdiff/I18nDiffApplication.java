package nl.evandongen.xliffdiff;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Application entry point for {@link I18nDiffService}
 *
 * @author evandongen
 */
public class I18nDiffApplication {

	private static final String DEFAULT_LATEST = "messages.latest.xlf";
	private static final String DEFAULT_PREVIOUS = "messages.previous.xlf";

	public static void main(String[] args) {

		InputStream latestInputStream = null;
		InputStream otherInputStream = null;

		// These can be passed as parameters when running the class
		String latestFileParameter = System.getProperty("latestFile");
		String otherFileParameter = System.getProperty("otherFile");

		if (StringUtils.isNotEmpty(latestFileParameter)
				&& StringUtils.isNotEmpty(otherFileParameter)) {

			System.out.println("Parameters found, opening:");
			System.out.println("  " + latestFileParameter);
			System.out.println("  " + otherFileParameter);

			try {
				latestInputStream = new FileInputStream(latestFileParameter);
				otherInputStream = new FileInputStream(otherFileParameter);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}


		} else {
			StringBuffer buffer = new StringBuffer();
			buffer.append("\nYou can specifiy your own files with:\n");
			buffer.append("  -DlatestFile=/path/to/your/latest-file.xlf\n");
			buffer.append("  -DotherFile=/path/to/your/previous-file.xlf\n");
			System.out.println(buffer.toString());

			System.out.println("No parameters set, using default:");
			ClassLoader classLoader = I18nDiffApplication.class.getClassLoader();
			latestInputStream = I18nDiffApplication.class.getResourceAsStream("/messages.latest.xlf");
			otherInputStream = I18nDiffApplication.class.getResourceAsStream("/messages.previous.xlf");

			System.out.println("  " + DEFAULT_LATEST);
			System.out.println("  " + DEFAULT_PREVIOUS);
		}

		I18nDiffService i18nDiffService = new I18nDiffService();

		if (latestInputStream != null && otherInputStream != null) {
			i18nDiffService.compareFiles(latestInputStream, otherInputStream);
		} else {
			System.out.println("Couldn't create input stream for one of the given files");
		}
	}
}
