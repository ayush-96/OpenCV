package hellovideo;

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
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class Hellovideorec {
	JFrame frame;
	JLabel imageLabel;
	static{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args)throws IOException {
		Hellovideorec obj=new Hellovideorec();
		obj.setgui();
		obj.runmainloop(args);
	}

	/* Setting up JFrame for video capture */
	public void setgui(){
		frame = new JFrame("Camera Input Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		imageLabel = new JLabel();
		frame.add(imageLabel);
		frame.setVisible(true);
	}

	public BufferedImage toBufferedImage(Mat mat)throws IOException{
		BufferedImage bfImage;
		MatOfByte matofbyte=new MatOfByte();
		Imgcodecs.imencode(".jpg", mat, matofbyte);
		byte[] bt=matofbyte.toArray();
		InputStream in=new ByteArrayInputStream(bt);
		bfImage=ImageIO.read(in);
		return bfImage;
	}

	private void runmainloop(String args[])throws IOException{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat mat=new Mat();
		VideoCapture capture=new VideoCapture(0);
		BufferedImage tempImage;
		if(capture.isOpened()){
			/* Camera is successfully working */
			/*  Keeping camera open for capturing video */
			while(true){
				capture.read(mat);
				if( !mat.empty() ){
					tempImage=toBufferedImage(mat);
					ImageIcon imageIcon = new ImageIcon(tempImage, "Captured video");
					imageLabel.setIcon(imageIcon);
				}
				else{
					System.out.println("Cannot capture video");
					break;
				}
			}
		capture.release();
		}
		}
}
