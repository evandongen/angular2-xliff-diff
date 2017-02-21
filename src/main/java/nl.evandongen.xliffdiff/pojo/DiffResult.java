package nl.evandongen.xliffdiff.pojo;

/**
 * Result class to contain the added and removed items.
 * <p>
 * Has to contain an additional layer to avoid
 * "Conflicting getter definitions for property "trans-unit"" when serializing to XML
 *
 * @author evandongen
 */
public class DiffResult {

	private DiffResultAdded diffResultAdded;

	private DiffResultRemoved diffResultRemoved;

	private DiffResultChanged diffResultChanged;

	public DiffResultAdded getDiffResultAdded() {
		return diffResultAdded;
	}

	public void setDiffResultAdded(DiffResultAdded diffResultAdded) {
		this.diffResultAdded = diffResultAdded;
	}

	public DiffResultChanged getDiffResultChanged() {
		return diffResultChanged;
	}

	public void setDiffResultChanged(DiffResultChanged diffResultChanged) {
		this.diffResultChanged = diffResultChanged;
	}

	public DiffResultRemoved getDiffResultRemoved() {
		return diffResultRemoved;
	}

	public void setDiffResultRemoved(DiffResultRemoved diffResultRemoved) {
		this.diffResultRemoved = diffResultRemoved;
	}
}
