package VendingMachine;

import VendingMachine.VendingStates.State;

public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachine.getInstance();
        try {
            System.out.println("*****Filling up the VendingMachine.Inventory*****");
            vendingMachine.getInventory().fillUpInventory(vendingMachine);
            displayInventory(vendingMachine);

            System.out.println("***** Clicking on Insert coins button *****");
            State vendingState = vendingMachine.getVendingMachineState();
            vendingState.pressInsertCoins(vendingMachine);

            vendingState = vendingMachine.getVendingMachineState();
            vendingState.insertCoin(vendingMachine, Coin.FIFTY);

            System.out.println("***** Clicking on Product Selection button *****");
            vendingState.pressProductSelectionButton(vendingMachine);

            vendingState = vendingMachine.getVendingMachineState();
            vendingState.selectProduct(vendingMachine, 101);

            displayInventory(vendingMachine);

        } catch (Exception e) {
            System.out.println("exception is: " + e.getMessage());
            displayInventory(vendingMachine);
        }
    }

    private static void displayInventory(VendingMachine vendingMachine) {
        for (ItemShelf shelf : vendingMachine.getInventory().getInventory()) {
            if (shelf != null && shelf.getItem() != null) {
            System.out.println("CodeNumber: " + shelf.getCodeNumber()
                                + "\tVendingMachine.Item: " + shelf.getItem().getType()
                                + "\tPrice: " + shelf.getItem().getPrice()
                                + "\tisSoldOut: " + shelf.isSoldOut());
            } else {
                System.out.println("Invalid shelf or item found in inventory.");
            }
        }
    }
}
