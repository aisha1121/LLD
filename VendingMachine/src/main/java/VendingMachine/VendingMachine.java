package VendingMachine;

import VendingMachine.VendingStates.Impl.IdleState;
import VendingMachine.VendingStates.State;

import java.util.ArrayList;

public class VendingMachine {

    private static VendingMachine vendingMachine;

    public static VendingMachine getInstance() {
        if (vendingMachine == null) {
            vendingMachine = new VendingMachine();
        }
        return vendingMachine;
    }
    private State vendingMachineState;
    private ArrayList<Coin> coins;
    private Inventory inventory;

    private VendingMachine() {
        this.vendingMachineState = new IdleState();
        this.coins = new ArrayList<>();
        this.inventory = new Inventory(10);
    }

    public State getVendingMachineState() {
        return vendingMachineState;
    }

    public void setVendingMachineState(State vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getTotalAmount() {
        int amount = 0;
        for (Coin it : coins) {
            amount += it.value;
        }
        return amount;
    }
}
