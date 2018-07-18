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

public class Opencvblur {

	public static void main(String[] args)throws IOException {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		/* Input image file location */
		String s="E:/blog/featured.jpg";
		
		Mat src=new Mat();
		Mat dst=new Mat();
		src=Imgcodecs.imread(s);
		Size ksize = new Size(45, 45);
        	Point anchor=new Point(20,30);
        	int borderType=Core.BORDER_DEFAULT;

		/* Applying a basic blur */
	        Imgproc.blur(src, dst, ksize, anchor, borderType);
        	Imgcodecs.imwrite("E:/blog/featuredblurred.jpg", dst);

		/* Applying a Gaussian Blur */
        	Imgproc.GaussianBlur(src, dst, ksize, 0);
        	Imgcodecs.imwrite("E:/blog/featuredGaussian.jpg",dst);

		/* Applying a Median Blur */
        	Imgproc.medianBlur(src, dst, 15);
        	Imgcodecs.imwrite("E:/blog/featuredmedian.jpg",dst);

        	MatOfByte matofbyte=new MatOfByte();
        	Imgcodecs.imencode(".jpg", dst, matofbyte);
        	byte[] bt=matofbyte.toArray();
        	InputStream in=new ByteArrayInputStream(bt);
        	BufferedImage bi=ImageIO.read(in);
		
		/* Setting up JFrame for output image */
        	JFrame j2=new JFrame();
        	j2.getContentPane().add(new JLabel(new ImageIcon(bi)));
        	j2.pack();
        	j2.setVisible(true);
	}

}
