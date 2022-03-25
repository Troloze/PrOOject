package engine;

import java.io.File;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyFileWriter {
	private static MyFileWriter instance;
	
	private MyFileWriter() {
		
	}
	
	public static MyFileWriter getInstance() {
		if(instance == null) {
			instance = new MyFileWriter();
		}
		
		return instance;
	}
	
	public void writeFile(PrintWriter file, String text) {
		file.format(text);
		file.close();
	}
	
	public PrintWriter openFile(String directory) {
		File openFile = new File(directory);
		PrintWriter file;
		
		if(openFile.exists()) {
			try {
				file = new PrintWriter(new OutputStreamWriter(new FileOutputStream(openFile)));
				return file;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			MyFileWriter.createFile(directory);

			try {
				file = new PrintWriter(new OutputStreamWriter(new FileOutputStream(openFile)));
				return file;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static void createFile(String directory) {
		
		File newFile = new File(directory);
			
		try {
			newFile.createNewFile();
			System.out.println("Path " + newFile.getAbsolutePath() + " don't exists...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Path " + newFile.getAbsolutePath() + " created...");
		}
		
	}
}

