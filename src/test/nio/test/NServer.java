package test.nio.test;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NServer {

	static int size = 4;
	
	public static void main(String[] args) throws Exception {
		
		ByteBuffer bb = ByteBuffer.allocate(size);
		
		FileOutputStream out = new FileOutputStream("nio_out.txt");
		FileChannel c =  out.getChannel();
		
		
		byte[] bs = "1234567890".getBytes();
		
		int index = 0;
		while (index < bs.length - 1) {
			int length = Math.min(size, bs.length - index);
			bb.put(bs, index, length);
			bb.flip();
			
			System.out.println(new String(bs, index, length));
			System.out.println(new String(bb.array()));
			c.write(bb);
			bb.clear();
			index += size;
		}
		out.close();
	}
	
}
