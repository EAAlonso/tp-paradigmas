package cryptomanager;

public interface BuilderFromStringArray<T,Q> {
	public Q NewFromStringArray(String[] params);
	public T NewKeyFromStringArray(String[] params);
	public T GetKey(Q obj);
	public String GetCSVHeader();
	public String GetCSVRow(Q obj);
}
