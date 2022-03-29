package engine;

import java.io.File;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MyFileReader {
	
	private static MyFileReader instance;
	
	private MyFileReader() {
		
	}
	
	public static MyFileReader getInstance() {
		if(instance == null) {
			instance = new MyFileReader();
		}
		
		return instance;
	}
	
	public Image loadImage(String directory) {
		Image image = new ImageIcon(directory).getImage();
		
		return image;
	}
	
	public String toString(BufferedReader fileToRead) {
		
		String text;
		
		try {
			text = fileToRead.readLine();
			fileToRead.close();
			return text;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public BufferedReader openFile(String directory) {
		File openFile = new File(directory);
		BufferedReader file;
		
		if(openFile.exists()) {
			try {
				file = new BufferedReader(new InputStreamReader(new FileInputStream(openFile)));
				return file;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			MyFileWriter.createFile(directory);
			
			try {
				file = new BufferedReader(new InputStreamReader(new FileInputStream(openFile)));
				return file;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
}

