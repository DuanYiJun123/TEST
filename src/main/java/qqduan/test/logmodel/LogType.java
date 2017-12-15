package qqduan.test.logmodel;

public enum LogType {

	All(true, true), Only_fail(false, true), None(false, false);

	private boolean success;
	private boolean fail;

	private LogType(boolean success, boolean fail) {
		this.success = success;
		this.fail = fail;
	}

	public boolean success() {
		return success;
	}

	public boolean fail() {
		return fail;
	}
}
