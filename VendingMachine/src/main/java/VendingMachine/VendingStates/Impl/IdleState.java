package VendingMachine.VendingStates.Impl;

import VendingMachine.Coin;
import VendingMachine.Item;
import VendingMachine.VendingMachine;
import VendingMachine.VendingStates.State;

import java.util.ArrayList;
import java.util.List;

public class IdleState implements State {

    public IdleState() {
        System.out.println("Currently vending machine is in idle state");
    }

    public IdleState(VendingMachine machine) {
        System.out.println("Currently vending machine is in idle state");
        machine.setCoins(new ArrayList<>());
    }

    @Override
    public void pressInsertCoins(VendingMachine machine) throws Exception {
        machine.setVendingMachineState(new HasMoneyState());
    }

    @Override
    public void pressProductSelectionButton(VendingMachine machine) throws Exception {
        throw new Exception("Please click on Insert coin button to proceed!");
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        throw new Exception("You cannot insert coin in idle state. Please click on Insert coin button to proceed!");
    }

    @Override
    public void selectProduct(VendingMachine machine, int codeNumber) throws Exception {
        throw new Exception("You cannot choose product in idle state. Please click on Insert coin button to proceed!");
    }

    @Override
    public List<Coin> refundAllMoney(VendingMachine machine) throws Exception {
        throw new Exception("You cannot get refund in idle state. Please click on Insert coin button to proceed!");
    }

    @Override
    public int getChange(int amt) throws Exception {
        throw new Exception("You cannot get change in idle state. Please click on Insert coin button to proceed!");
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception {
        throw new Exception("Product cannot be dispensed in idle state. Please click on Insert coin button to proceed!");
    }
}
