package nl.evandongen.xliffdiff;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import nl.evandongen.xliffdiff.pojo.ChangedTransUnit;
import nl.evandongen.xliffdiff.pojo.DiffResult;
import nl.evandongen.xliffdiff.pojo.DiffResultAdded;
import nl.evandongen.xliffdiff.pojo.DiffResultChanged;
import nl.evandongen.xliffdiff.pojo.DiffResultRemoved;
import nl.evandongen.xliffdiff.pojo.I18nMessages;
import nl.evandongen.xliffdiff.pojo.I18nTransUnit;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author evandongen
 */
public class I18nDiffService {

	public DiffResult compareFiles(InputStream latestFile, InputStream otherFile) {
		XmlMapper xmlMapper = new XmlMapper();

		try {
			I18nMessages latestI18n = getMessagesFromFile(latestFile, xmlMapper);
			I18nMessages otherI18n = getMessagesFromFile(otherFile, xmlMapper);

			DiffResult diffResult = processMessages(latestI18n, otherI18n);

			generateXmlOutput(xmlMapper, diffResult);

			return diffResult;

		} catch (IOException ioe) {
			System.out.println("Error while reading xml files: " + ioe);
			ioe.printStackTrace();
		}

		return null;
	}

	/**
	 * Make sure the interpolation tags are correctly placed again in the output. Somehow the (de)serialization
	 * process already replaced the > / &gt; at the end, that's why the Pattern is defined like this.
	 *
	 * @param encoded
	 * @return
	 */
	protected String decodeInterpolation(String encoded) {
		Pattern pattern = Pattern.compile("(?i)&lt;(x([^>]+))/>");

		String replaced = pattern.matcher(encoded).replaceAll("<$1/>");

		return replaced;
	}

	/**
	 * trans-unit source and target may contain '<x id="INTERPOLATION"/>'. This is hard to decode with Jackson,
	 * so it's encoded here to make sure the deserialization works like it should
	 *
	 * @param decoded
	 * @return
	 */
	protected String encodeInterpolation(String decoded) {
		Pattern pattern = Pattern.compile("(?i)<(x([^>]+))/>");

		String replaced = pattern.matcher(decoded).replaceAll("&lt;$1/&gt;");

		return replaced;
	}

	private void generateXmlOutput(XmlMapper xmlMapper, DiffResult diffResult) {
		xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		try {
			String output = xmlMapper.writeValueAsString(diffResult);

			System.out.println(decodeInterpolation(output));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the given file and tries to map it to a POJO
	 *
	 * @param inputStream
	 * @param xmlMapper
	 * @return
	 * @throws IOException
	 */
	private I18nMessages getMessagesFromFile(InputStream inputStream, XmlMapper xmlMapper) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
		}
		bufferedReader.close();

		// Get file contents and convert to Object representation
		String fileContentsXml = encodeInterpolation(stringBuilder.toString());

		I18nMessages i18nMessages = xmlMapper.readValue(fileContentsXml, I18nMessages.class);

		return i18nMessages;
	}

	/**
	 * Compares the latest collection with the other collection. Keeps track of all added messages and  calculates
	 * the items that aren't in the latest version anymore
	 *
	 * @param latestI18n
	 * @param otherI18n
	 *
	 * @return DiffResult
	 */
	private DiffResult processMessages(I18nMessages latestI18n, I18nMessages otherI18n) {
		List<I18nTransUnit> addedMessages = new ArrayList<>();
		List<ChangedTransUnit> changedTranslations = new ArrayList<>();
		List<I18nTransUnit> removedTranslations = otherI18n.getI18nMessagesFile().getBody();

		final List<I18nTransUnit> latestTranslationUnits = latestI18n.getI18nMessagesFile().getBody();
		final List<I18nTransUnit> otherTranslationUnits = otherI18n.getI18nMessagesFile().getBody();

		for (I18nTransUnit unit : latestTranslationUnits) {
			Optional<I18nTransUnit> itemFound = otherTranslationUnits.stream()
					.filter(i18nTransUnit -> i18nTransUnit.getId().equals(unit.getId()))
					.findFirst();

			if (itemFound.isPresent()) {
				// The item was found. Remove from the list
				removedTranslations.remove(itemFound.get());
			} else {
				List<I18nTransUnit> changedUnits = null;

				if (StringUtils.isNotBlank(unit.getNote())) {
					// Check if the key has changed by finding matches on the note.
					changedUnits = otherTranslationUnits.stream()
							.filter(i18nTransUnit -> StringUtils.isNotBlank(i18nTransUnit.getNote())
									&& i18nTransUnit.getNote().equals(unit.getNote()))
							.collect(Collectors.toList());
				}

				// If there's a match (not usefull if there are more), the key has probably changed
				if (changedUnits != null && !changedUnits.isEmpty() && changedUnits.size() == 1) {
					changedTranslations.add(new ChangedTransUnit(changedUnits.get(0).getId(), unit.getId()));
				} else {

					// If the item is not present in the other list, this key was added
					addedMessages.add(unit);
				}
			}
		}

		if (addedMessages.size() > 0 || removedTranslations.size() > 0 || changedTranslations.size() > 0) {
			DiffResult diffResult = new DiffResult();

			DiffResultAdded diffResultAdded = new DiffResultAdded();
			diffResultAdded.setAdded(addedMessages);
			diffResult.setDiffResultAdded(diffResultAdded);

			DiffResultChanged diffResultChanged = new DiffResultChanged();
			diffResultChanged.setChanged(changedTranslations);
			diffResult.setDiffResultChanged(diffResultChanged);

			DiffResultRemoved diffResultRemoved = new DiffResultRemoved();
			diffResultRemoved.setRemoved(removedTranslations);
			diffResultRemoved.setRemovedKeys(removedTranslations.stream().map(unit -> unit.getId()).collect(Collectors.toList()));
			diffResult.setDiffResultRemoved(diffResultRemoved);

			return diffResult;
		}

		return null;
	}
}