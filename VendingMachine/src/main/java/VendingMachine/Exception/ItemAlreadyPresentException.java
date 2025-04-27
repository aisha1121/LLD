package VendingMachine.Exception;

public class ItemAlreadyPresentException extends Exception {
    public ItemAlreadyPresentException(String message) {
        super(message);
    }
}
