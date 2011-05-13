package mayb;

public abstract class Maybe<T> {
	  private Maybe() {
	  }
	 
	  public abstract <Q> Q maybe(JustC<Q, T> jc, NothingC<Q> nc);
	 
	  public static abstract class Nothing<T> extends Maybe<T> {
	    private Nothing() {
	    }
	  }
	 
	  public static abstract class Just<T> extends Maybe<T> {
	    private Just() {
	    }
	 
	    public abstract T just();
	  }
	 
	  public static <R> Maybe<R> _just(final R r) {
	    return new Just<R>() {
	      @Override
	      public R just() {
	        return r;
	      }
	 
	      @Override
	      public <Q> Q maybe(final JustC<Q, R> jc, final NothingC<Q> nc) {
	        return jc.c(r);
	      }
	    };
	  }
	 
	  public static <R> Maybe<R> _nothing() {
	    return new Nothing<R>() {
	      @Override
	      public <Q> Q maybe(final JustC<Q, R> jc, final NothingC<Q> nc) {
	        return nc.c();
	      }
	    };
	  }
	}
	 
	interface JustC<Q, R> {
	  Q c(R r);
	}
	 
	interface NothingC<Q> {
	  Q c();
	}