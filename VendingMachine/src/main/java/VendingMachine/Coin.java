package VendingMachine;

public enum Coin {
    FIVE(5),
    TEN(10),
    FIFTY(50);

    final int value;

    Coin(int value) {
        this.value = value;
    }
}
