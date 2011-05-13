package mayb;

public interface Result<T> {
	public void onSuccess(Action<T> action);

	public <V> Result<V> chain(Transform<T,V> transform);
}
class Success<S> implements Result<S>{

	private final S value;

	public Success(S val) {
		this.value = val;
	}

	public void onSuccess(Action<S> action) {
		action.executeOn(value);
	}

	@Override
	public <V> Result<V> chain(Transform<S, V> transform) {
		return transform.transform(value);
	}

}
class Failure<F> implements Result<F> {

	@Override
	public void onSuccess(Action<F> action) {}

	@Override
	public <V> Result<V> chain(Transform<F, V> transform) {
		return new Failure<V>();
	}
	
}