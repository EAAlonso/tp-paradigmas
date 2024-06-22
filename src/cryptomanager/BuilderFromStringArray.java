package cryptomanager;

public interface BuilderFromStringArray<T,Q> {
	public Q NewFromStringArray(String[] params);
	public T NewKeyFromStringArray(String[] params);
}
