package app;

public class OTHRestException extends RuntimeException {

    private int httpStatuscode;

    public OTHRestException(int httpStatuscode, String msg) {
        super(msg);
        this.httpStatuscode = httpStatuscode;
    }

    public int getHttpStatuscode() {
        return httpStatuscode;
    }
}
