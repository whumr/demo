package test.gif;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gif4j.GifDecoder;
import com.gif4j.GifFrame;
import com.gif4j.GifImage;

public class GifDecodeTest {

	public static void main(String[] args) throws IOException {
		GifImage gif = GifDecoder.decode(new File("C:\\Users\\Administrator\\Desktop\\girl.gif"));
		for (int i = 0; i < gif.getNumberOfFrames(); i++) {
			GifFrame frame = gif.getFrame(i);
			
			BufferedImage img = frame.getAsBufferedImage();
			ImageIO.write(img, "jpg", new File("C:\\Users\\Administrator\\Desktop\\g\\girl" + i + ".jpg"));
		}
//		BufferedImage img = ImageIO.read(new File("test.gif"));
//		System.out.println(img.getHeight());
//		System.out.println(img.getClass().getName());
	}

}
