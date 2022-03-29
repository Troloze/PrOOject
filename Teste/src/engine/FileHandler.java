package engine;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class FileHandler {
	private static FileHandler instance;
	private MyFileReader fileReader;
	private MyFileWriter fileWriter;
	
	private FileHandler() {
		fileReader = MyFileReader.getInstance();
		fileWriter = MyFileWriter.getInstance();
	}
	
	public static FileHandler getInstance() {
		if(instance == null) {
			instance = new FileHandler();
		}
		
		return instance;
	}
	
	public void writeFile(String directory, String text) {
		PrintWriter myFile = fileWriter.openFile(directory);
		
		fileWriter.writeFile(myFile, text);
	}
	
	public String readFile(String directory) {
		BufferedReader myFile = fileReader.openFile(directory);
		
		return fileReader.toString(myFile);
	}
	
	public Image openImage(String directory) {	
		return fileReader.loadImage(directory);
	}
}
