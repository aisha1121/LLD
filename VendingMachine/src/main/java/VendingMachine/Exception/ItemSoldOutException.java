package VendingMachine.Exception;

public class ItemSoldOutException  extends Exception {
    public ItemSoldOutException(String message) {
        super(message);
    }
}
