package nl.evandongen.xliffdiff;

import org.junit.Assert;
import org.junit.Test;

public class DiffServiceTest {

	@Test
	public void testDiffService() {
		I18nDiffService i18nDiffService = new I18nDiffService();

		DiffResult diffResult = i18nDiffService.compareFiles("messages.latest.test.xlf", "messages.previous.test.xlf");

		Assert.assertEquals(diffResult.getDiffResultAdded().getAdded().size(), 2);
		Assert.assertEquals(diffResult.getDiffResultRemoved().getRemoved().size(), 1);
	}

	@Test
	public void testRegularExpressions() {
		I18nDiffService i18nDiffService = new I18nDiffService();

		String encoded = i18nDiffService.encodeInterpolation("\t\t\t\t\t<x id=\"INTERPOLATION\"/>\t\t\t\t\tCC\t\t\t\t");
		Assert.assertEquals(encoded, "\t\t\t\t\t&lt;x id=\"INTERPOLATION\"/&gt;\t\t\t\t\tCC\t\t\t\t");

		String decoded = i18nDiffService.decodeInterpolation("\t\t\t\t\t&lt;x id=\"INTERPOLATION\"/>\t\t\t\t\tCC\t\t\t\t");
		Assert.assertEquals(decoded, "\t\t\t\t\t<x id=\"INTERPOLATION\"/>\t\t\t\t\tCC\t\t\t\t");
	}
}