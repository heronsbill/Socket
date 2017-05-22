package scalableIO.classic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

	private final int port;
	private ServerSocket serverSocket;
	
	public Server(int port){
		this.port = port;
		try {
			this.serverSocket = new ServerSocket(port);
			System.out.println(Thread.currentThread().toString() + "正在监听端口-" + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		try {
			Socket socket;
			while (!Thread.interrupted()) {
				socket = serverSocket.accept();
				System.out.println(Thread.currentThread().toString() + "创建线程处理读写");
				new Thread(new Handler(socket)).start();
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getPort() {
		return port;
	}
	
	public static void main(String[] args) {
		new Thread(new Server(12345)).start();
	}
	
}
