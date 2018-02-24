package qqduan.test.enu;

public enum PortType {
	appserver(7100),web(8080),eye(40161);
	
	public int port;

	private PortType(int port) {
		this.port = port;
	}
	
}
