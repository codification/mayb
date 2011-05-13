package mayb;

public class Has<T> {

	private final T value;

	public Has(T val) {
		this.value = val;
	}

	public T get() {
		return value;
	}

}
