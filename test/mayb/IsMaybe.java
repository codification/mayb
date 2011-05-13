package mayb;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class IsMaybe {
	protected int called = 0;

	protected void assertNumberOfCalls(int calls) {
		assertEquals("Calls",calls,called);
	}

	@Test public void 
	success() {
		Result<String> result1 = new Success<String>("yeah");
		
		result1.onSuccess(new IncCallAction());
		assertNumberOfCalls(1);
	}
	
	@Test public void 
	failure() {
		Result<String> result1 = new Failure<String>();
		
		result1.onSuccess(new IncCallAction());
		assertNumberOfCalls(0);
	}

	
	@Test public void
	withAFailure() {
		Success<String> result = new Success<String>("hello");
		result
			.chain(new StringDoubler())
			.chain(new FailingTransform())
			.chain(new StringDoubler())
			.onSuccess(new IncCallAction());
		assertNumberOfCalls(0);
	}
	@Test public void
	allSucceeding() {
		Result<String> result = new Success<String>("hello")
		.chain(new StringDoubler())
		.chain(new StringDoubler());
		result.onSuccess(new IncCallAction());
		result.onSuccess(new Action<String>() {			
			@Override
			public void executeOn(String val) {
				assertEquals("hellohellohellohello", val);
			}
		});
		assertNumberOfCalls(1);
	}
	

	private final class IncCallAction implements Action<String> {
		@Override
		public void executeOn(String val) {
			called++;
		}
	}

	private class StringDoubler implements
	Transform<String, String> {
		@Override
		public Result<String> transform(String value) {
			return new Success<String>(value + value);
		}
	}
	private class FailingTransform implements
	Transform<String, String> {
		@Override
		public Result<String> transform(String value) {
			return new Failure<String>();
		}
	}
}
