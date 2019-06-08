package myExceptions;

public class FileException extends Exception {

    public FileException(String ms, Throwable cause) {
        super(ms, cause);
    }
}
