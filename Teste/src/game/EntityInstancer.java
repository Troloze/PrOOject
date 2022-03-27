package game;

public final class EntityInstancer {
	
	public static final int ENT_REGULAR = 0;
	
	private static EntityInstancer instance;
		
	private EntityInstancer () {}
	
	public static EntityInstancer getInstance() {
		if (instance == null) {
			instance = new EntityInstancer();
		}
		return instance;
	}
	
	public static void instance(int type, InstanceParams params) {
		switch (type) {
			case ENT_REGULAR:
			
		}
	}
}
