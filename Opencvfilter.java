package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Opencvfilter {

	public static void main(String args[])throws IOException{
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		/* Input image file location */
		String s="E:/blog/featured.jpg";
		
		Mat src=new Mat();
		Mat dst=new Mat();
		/* reading the input image in the src matrix */
		src=Imgcodecs.imread(s);
		
		/*Applying Bilateral Filter */
		Imgproc.bilateralFilter(src, dst, 30, 150, 150, Core.BORDER_DEFAULT);
		Imgcodecs.imwrite("E:/blog/featuredbifilter.jpg",dst);

		/*Applying Box Filter */
		Imgproc.boxFilter(src, dst, 30, new Size(30,30), new Point(-1,-1), true, Core.BORDER_DEFAULT);
		Imgcodecs.imwrite("E:/blog/featuredboxfilter.jpg",dst);

		/*Applying Square Box Filter */
		Imgproc.sqrBoxFilter(src, dst, -1, new Size(1,1));
		Imgcodecs.imwrite("E:/blog/featuredsqrboxfilter.jpg",dst);

		MatOfByte matofbyte=new MatOfByte();
		Imgcodecs.imencode(".jpg", dst, matofbyte);
		byte[] byt=matofbyte.toArray();
		InputStream in=new ByteArrayInputStream(byt);
		BufferedImage bf=ImageIO.read(in);
		
		JFrame jf=new JFrame();
		jf.getContentPane().add(new JLabel(new ImageIcon(bf)));
		jf.pack();
		jf.setVisible(true);
	}

}
