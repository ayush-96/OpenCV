package camera;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public class Hellocamera extends Application{

	@Override
	public void start(Stage stage)throws FileNotFoundException, IOException{
		Hellocamera obj=new Hellocamera();
		WritableImage wt=obj.takeSnapshot();
		ImageView im=new ImageView(wt);
		im.setFitHeight(400);
		im.setFitWidth(600);
		im.setPreserveRatio(true);
		Group root=new Group(im);
		Scene scene=new Scene(root,600,400);
		stage.setTitle("Camera");
		stage.setScene(scene);
		stage.show();
	}

	public WritableImage takeSnapshot()throws IOException{
		WritableImage writableImage=null;
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		VideoCapture capture=new VideoCapture(0);
		Mat matrix=new Mat();
		capture.read(matrix);
		if(capture.isOpened()){
			if(capture.read(matrix)){
				MatOfByte matofbyte=new MatOfByte();
				Imgcodecs.imencode(".jpg", matrix, matofbyte);
				byte[] bt=matofbyte.toArray();
				InputStream in=new ByteArrayInputStream(bt);
				BufferedImage bf=ImageIO.read(in);
				writableImage=SwingFXUtils.toFXImage(bf, null);
				Imgcodecs.imwrite("E:/Projects Res/snapshot.jpg",matrix);

			}
		}
		return writableImage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
