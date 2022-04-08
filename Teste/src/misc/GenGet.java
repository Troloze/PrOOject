package misc;

public class GenGet<T> {
	
	private final Class<T> type;
	
	public GenGet(Class<T> type) {
		this.type = type;
	}
	
	@SuppressWarnings("unchecked")
	public T get(Object obj, T defValue) {
		if (obj != null && (obj.getClass() == type)) 
			return (T) obj;
		else
			return defValue;
	}
}
