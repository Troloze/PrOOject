package engine;

public class Core {
	public static void main (String[] args) {

		if (ImageBufferHandler.getInstance() == null) return;
		
		new MyFrame();
	}
	
}
