package biz.majorov.camel.component;

public interface SocketHandler {

	void messageReceived(Object message) throws Exception;
}
