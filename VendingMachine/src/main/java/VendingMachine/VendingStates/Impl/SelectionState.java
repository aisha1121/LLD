package VendingMachine.VendingStates.Impl;

import VendingMachine.Coin;
import VendingMachine.Item;
import VendingMachine.VendingMachine;
import VendingMachine.VendingStates.State;

import java.util.List;

public class SelectionState implements State {

    public SelectionState() {
        System.out.println("Currently vending machine is in product selection state");
    }

    @Override
    public void pressInsertCoins(VendingMachine machine) throws Exception {
        throw new Exception("You cannot click on press insert coins.");
    }

    @Override
    public void pressProductSelectionButton(VendingMachine machine) throws Exception {
        return;
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        throw new Exception("You cannot insert coins in selection state");
    }

    @Override
    public void selectProduct(VendingMachine machine, int codeNumber) throws Exception {
        // get the item
        Item item = machine.getInventory().getItem(codeNumber);
        System.out.println("Product selected is: " + item.getType());
        // get total amount paid by user
        int totalAmountPaidByUser = machine.getTotalAmount();

        // check if any refund applicable
        int priceOfItem = item.getPrice();
        if (priceOfItem > totalAmountPaidByUser) {
            System.out.println("Insufficient amount paid! VendingMachine.Item's price is: "
                    + priceOfItem + " and you paid: " + totalAmountPaidByUser);
            refundAllMoney(machine);
            throw new Exception("Insufficient amount");
        } else if (priceOfItem < totalAmountPaidByUser) {
            int returnAmt = totalAmountPaidByUser - priceOfItem;
            getChange(returnAmt);
        }
        machine.setVendingMachineState(new DispenseState(machine, codeNumber));
    }

    @Override
    public List<Coin> refundAllMoney(VendingMachine machine) throws Exception {
        System.out.println("Refunded all amount in the tray: " + machine.getTotalAmount());
        List<Coin> refundedAmount = machine.getCoins();
        machine.setVendingMachineState(new IdleState(machine));
        return refundedAmount;
    }

    @Override
    public int getChange(int amt) throws Exception {
        System.out.println("Refunded change in the tray: " + amt);
        return amt;
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception {
        throw new Exception("Product cannot be dispensed in selection state");
    }
}
