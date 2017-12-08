package qqduan.test.core;

public class CaseResult {
	private String caseName;
	private String data;

	public CaseResult(String data, String caseName) {
		super();
		this.caseName = caseName;
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

}
