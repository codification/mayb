package mayb;

public interface Action<T> {
	void executeOn(T val);
}
