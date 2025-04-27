package VendingMachine.VendingStates.Impl;

import VendingMachine.Coin;
import VendingMachine.Item;
import VendingMachine.VendingMachine;
import VendingMachine.VendingStates.State;

import java.util.List;

public class HasMoneyState implements State {

    public HasMoneyState() {
        System.out.println("Currently vending machine is in HasMoneyState. It is ready to collect money");
    }

    @Override
    public void pressInsertCoins(VendingMachine machine) throws Exception {
    }

    @Override
    public void pressProductSelectionButton(VendingMachine machine) throws Exception {
        machine.setVendingMachineState(new SelectionState());
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        machine.getCoins().add(coin);
        System.out.println("Total money paid: " + machine.getTotalAmount());
    }

    @Override
    public void selectProduct(VendingMachine machine, int codeNumber) throws Exception {
        throw new Exception("You cannot select product in HasMoney state. Please click on pressProductSelection to proceed further!");
    }

    @Override
    public List<Coin> refundAllMoney(VendingMachine machine) throws Exception {
        System.out.println("Refunded all coins");
        machine.setVendingMachineState(new IdleState(machine));
        return machine.getCoins();
    }

    @Override
    public int getChange(int amt) throws Exception {
        throw new Exception("You cannot get change in hasMoney state");
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception {
        throw new Exception("You cannot dispense product in hasMoney State. Please click on pressProductSelection to proceed further!");
    }
}
