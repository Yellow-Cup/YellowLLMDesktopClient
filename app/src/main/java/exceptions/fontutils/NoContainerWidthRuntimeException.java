package exceptions.fontutils;

public class NoContainerWidthRuntimeException extends RuntimeException {
    public static String message = "\n\nCan't get container width, please set one before calling this method or provide as an argument.\n";

    public NoContainerWidthRuntimeException() {
        super(message.toUpperCase());
    }

}