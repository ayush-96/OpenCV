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
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Opencvfilter2d {

	public static void main(String[] args)throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String s="E:/blog/featured.jpg";

		Mat src=new Mat();
		Mat dst=new Mat();
		src=Imgcodecs.imread(s);

		Mat kernel=Mat.ones(2, 2, CvType.CV_32F);
		for(int i = 0; i<kernel.rows(); i++) {
	         for(int j = 0; j<kernel.cols(); j++) {
	            double[] m = kernel.get(i, j);

	            for(int k = 1; k<m.length; k++) {
	               m[k] = m[k]/(2 * 2);
	            }
	            kernel.put(i,j, m);
	         }
	      }
		Imgproc.filter2D(src, dst, -1, kernel);
		Imgcodecs.imwrite("E:/blog/featuredfiter2d.jpg",dst);

		Mat kernel2=Imgproc.getStructuringElement(Imgproc.MORPH_RECT,new  Size((2*2) + 1, (2*2)+1));
		Imgproc.dilate(src,dst,kernel2);
		Imgcodecs.imwrite("E:/blog/featureddilate.jpg",dst);

		Mat kernel3=Imgproc.getStructuringElement(Imgproc.MORPH_RECT,new  Size((2*2) + 1, (2*2)+1));
		Imgproc.erode(src, dst, kernel3);
		Imgcodecs.imwrite("E:/blog/featurederode.jpg",dst);

		Mat kernel4= Mat.ones(5,5, CvType.CV_32F);
		Imgproc.morphologyEx(src, dst, Imgproc.MORPH_GRADIENT, kernel4);
		Imgcodecs.imwrite("E:/blog/featuredmorph.jpg",dst);

		Imgproc.pyrUp(src, dst, new Size(src.cols()*2,  src.rows()*2), Core.BORDER_DEFAULT);
		Imgcodecs.imwrite("E:/blog/featuredpyramidup.jpg",dst);

		Imgproc.pyrDown(src, dst, new Size(src.cols()/2,  src.rows()/2),Core.BORDER_DEFAULT);
		Imgcodecs.imwrite("E:/blog/featuredpyramiddown.jpg",dst);


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
