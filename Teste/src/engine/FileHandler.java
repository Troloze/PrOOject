package engine;

import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import game.Rank;

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
	
	public void rewriteFile(String directory, ArrayList<Rank> rank) {
		PrintWriter myFile = fileWriter.openFile(directory);
		fileWriter.rewriteFile(myFile, rank);
	}
	
	public ArrayList<String> readFile(String directory) {
		BufferedReader myFile = fileReader.openFile(directory);
		return fileReader.readFile(myFile);
	}
	
	public Image openImage(String directory) {	
		return fileReader.loadImage(directory);
	}
	
	public Font openFont(String directory) {
		return fileReader.loadFont(directory);
	}
}
