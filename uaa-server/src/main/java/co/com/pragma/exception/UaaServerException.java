package co.com.pragma.exception;

public class UaaServerException extends Exception {

	/** Serializer */
	private static final long serialVersionUID = -5576437600899646676L;

	private String origin;

	public UaaServerException(String message, String origin, Throwable e) {
		super(message, e);
		this.origin = origin;
	}

	public UaaServerException(String message, String origin) {
		super(message);
		this.origin = origin;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
