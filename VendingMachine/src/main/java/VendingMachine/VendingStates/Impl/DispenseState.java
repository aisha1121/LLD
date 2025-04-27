package VendingMachine.VendingStates.Impl;

import VendingMachine.Coin;
import VendingMachine.Item;
import VendingMachine.VendingMachine;
import VendingMachine.VendingStates.State;

import java.util.List;

public class DispenseState implements State {

    public DispenseState(VendingMachine machine, int code) throws Exception{
        System.out.println("Vending machine is in dispense state");
        dispenseProduct(machine, code);
    }

    @Override
    public void pressInsertCoins(VendingMachine machine) throws Exception {
        throw new Exception("press insert coins not allowed in dispense state.");
    }

    @Override
    public void pressProductSelectionButton(VendingMachine machine) throws Exception {
        throw new Exception("press product selection not allowed in dispense state.");
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        throw new Exception("insert coin not allowed in dispense state.");
    }

    @Override
    public void selectProduct(VendingMachine machine, int codeNumber) throws Exception {
        throw new Exception("select product not allowed in dispense state.");
    }

    @Override
    public List<Coin> refundAllMoney(VendingMachine machine) throws Exception {
        throw new Exception("refund money not allowed in dispense state.");
    }

    @Override
    public int getChange(int amt) throws Exception {
        throw new Exception("get change method not allowed in dispense state.");
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception {
        Item item = machine.getInventory().getItem(codeNumber);
        machine.getInventory().updateSoldOutItem(codeNumber);
        machine.setVendingMachineState(new IdleState(machine));
        return item;
    }
}
