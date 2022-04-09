package misc;

public class PatternParams {
	public String[] keys;
	public Object[] arguments;
	public int n;
	
	PatternParams() {}
	
	PatternParams(int n, String[] keys, Object[] arguments) {
		this.n = n;
		this.keys = keys;
		this.arguments = arguments;
	}
	
	@Override
	public String toString() {
		for (int i = 0; i < n; i++) {
			System.out.println(i + ": <" + keys[i] + ", " + arguments[i] + ">");
		}
		return "PatternParams: [n: " + n + "\nkeys: " + keys + ",\narguments: " + arguments + "]";
	}
}
