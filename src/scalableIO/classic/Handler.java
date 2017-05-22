package scalableIO.classic;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Handler implements Runnable{

	private final Socket clientSocket;
	private static int BUF_SIZE = 1024;
	
	public Handler(Socket clientSocket){
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
		int readSize;
		byte[] readBuf = new byte[BUF_SIZE];
		try {
			System.out.println(Thread.currentThread().toString() + "正在处理读写");
			InputStream in = clientSocket.getInputStream();
			OutputStream out = clientSocket.getOutputStream();
			while ((readSize = in.read(readBuf)) != -1) {
				out.write(readBuf, 0, readSize);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
