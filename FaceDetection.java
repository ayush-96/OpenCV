package faceDetection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
/*
 * This program works for front facing with a distinct background
 * */
public class FaceDetection {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat mat=new Mat();
		/* Input image destination */
		mat=Imgcodecs.imread("E:/blog/pic1.jpg");
		
		String xmlfile="E:/Softwares/OpenCV/opencv/sources/data/lbpcascades/lbpcascade_frontalface_improved.xml";
		CascadeClassifier classifier=new CascadeClassifier(xmlfile);

		MatOfRect faces=new MatOfRect();
		classifier.detectMultiScale(mat,faces);
		System.out.println(String.format("Detected %s faces",faces.toArray().length));

		for(Rect rect:faces.toArray()){
			Imgproc.rectangle(mat, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height), new Scalar(0,0,255),5);
		}
		/* Output image location */
		Imgcodecs.imwrite("E:/blog/featuredfacedetection.jpg",mat);
	}
}
