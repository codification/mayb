package mayb;

public interface Transform<S1, S2> {
	Result<S2> transform(S1 value);
}
