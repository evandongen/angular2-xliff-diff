package nl.evandongen.xliffdiff.pojo;

/**
 * @author evandongen
 */
public class ChangedTransUnit {
	private String originalKey;

	private String newKey;

	public ChangedTransUnit(String originalKey, String newKey) {
		this.originalKey = originalKey;
		this.newKey = newKey;
	}

	public String getNewKey() {
		return newKey;
	}

	public void setNewKey(String newKey) {
		this.newKey = newKey;
	}

	public String getOriginalKey() {
		return originalKey;
	}

	public void setOriginalKey(String originalKey) {
		this.originalKey = originalKey;
	}
}
