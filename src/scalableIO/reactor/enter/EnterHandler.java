package scalableIO.reactor.enter;

import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import scalableIO.reactor.Handler;

public class EnterHandler extends Handler {
	
	private static final String ENTER = "\r\n";
	private static final String QUIT = "quit";

	EnterHandler(Selector selector, SocketChannel clientChannel){
		super(selector, clientChannel);
	}
	
	@Override
	public int byteBufferSize() {
		return 1;
	}
	
	@Override
	public boolean readIsComplete() {
		return readData.lastIndexOf(ENTER) != -1;
	}
	
	@Override
	public boolean isQuit(){
		return QUIT.equals(readData.toString().trim());
	}
	
	@Override
	public boolean writeIsComplete() {
		return !writeBuf.hasRemaining();
	}

}
