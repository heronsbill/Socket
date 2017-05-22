package scalableIO.reactor.enter;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

import scalableIO.ServerContext;
import scalableIO.reactor.Acceptor;
import scalableIO.reactor.Reactor;

public class EnterReactor extends Reactor {
	
	public EnterReactor(int port, ServerSocketChannel serverChannel, boolean isMainReactor, boolean useMultipleReactors, long timeout){
		super(port, serverChannel, isMainReactor, useMultipleReactors, timeout);
	}

	@Override
	public Acceptor newAcceptor(Selector selector) {
		return new EnterAcceptor(selector, serverChannel, useMultipleReactors);
	}
	
	public static void main(String[] args) throws IOException {
		//new EnterReactor(9003, ServerSocketChannel.open(), true, false, TimeUnit.MILLISECONDS.toMillis(10)).start();
		ServerContext.startMultipleReactor(9003, EnterReactor.class);
		//ServerContext.startSingleReactor(9003, EnterReactor.class);
	}

}
