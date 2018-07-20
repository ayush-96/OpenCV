package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Opencvthresh {

	public static void main(String[] args)throws Exception {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		String s="E:/blog/featured.jpg";
		Mat src=new Mat();
		Mat dst=new Mat();
		src=Imgcodecs.imread(s,0);

		Imgproc.threshold(src, dst, 50, 255, Imgproc.THRESH_BINARY);
		Imgcodecs.imwrite("E:/blog/featuredthreshbin.jpg",dst);

		Imgproc.adaptiveThreshold(src, dst, 125, Imgproc.ADAPTIVE_THRESH_MEAN_C,Imgproc.THRESH_BINARY, 11, 12);
		Imgcodecs.imwrite("E:/blog/featuredadpthresh.jpg",dst);

		Core.copyMakeBorder(src, dst, 20, 20, 20, 20, Core.BORDER_CONSTANT);
		Imgcodecs.imwrite("E:/blog/featuredborder.jpg",dst);

		MatOfByte matofbyte=new MatOfByte();
		Imgcodecs.imencode(".jpg", dst, matofbyte);
		byte[] b=matofbyte.toArray();
		InputStream in=new ByteArrayInputStream(b);
		BufferedImage bf=ImageIO.read(in);
		JFrame jf=new JFrame();
		jf.getContentPane().add(new JLabel(new ImageIcon(bf)));
		jf.setVisible(true);
		jf.pack();

	}

}
