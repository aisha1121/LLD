package VendingMachine;

import VendingMachine.Exception.InvalidCodeException;
import VendingMachine.Exception.ItemAlreadyPresentException;
import VendingMachine.Exception.ItemSoldOutException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Inventory {
    private ItemShelf[] inventory;

    Inventory(int itemCount) {
        inventory = new ItemShelf[itemCount];
        initialiseEmptyInventory();
    }

    public List<ItemShelf> getInventory() {
        return Collections.unmodifiableList(Arrays.asList(inventory));
    }

    public void setInventory(ItemShelf[] inventory) {
        this.inventory = inventory;
    }

    private void initialiseEmptyInventory() {
        int startCode = 101;
        for (int i = 0; i < inventory.length; i++) {
            ItemShelf shelf = new ItemShelf();
            shelf.setCodeNumber(startCode);
            shelf.setSoldOut(true);
            startCode++;
            inventory[i] = shelf;
        }
    }

    public void fillUpInventory(VendingMachine vendingMachine) throws Exception {
        for (int i = 0; i < inventory.length; i++) {
            if (i < 3) addItem(new Item(ItemType.PEPSI, 30), inventory[i].getCodeNumber());
            else if (i < 5) addItem(new Item(ItemType.COKE, 40), inventory[i].getCodeNumber());
            else if (i < 8) addItem(new Item(ItemType.COFFEE, 25), inventory[i].getCodeNumber());
            else addItem(new Item(ItemType.JUICE, 15), inventory[i].getCodeNumber());
        }
    }

    public void addItem(Item item, int codeNumber) throws ItemAlreadyPresentException {
        for (ItemShelf shelf : inventory) {
            if (codeNumber == shelf.getCodeNumber()) {
                if (shelf.isSoldOut()) {
                    shelf.setItem(item);
                    shelf.setSoldOut(false);
                } else {
                    throw new ItemAlreadyPresentException("VendingMachine.Item is already present, you cannot add item here.");
                }
            }
        }
    }

    public Item getItem(int codeNumber) throws ItemSoldOutException,InvalidCodeException {
        for (ItemShelf shelf : inventory) {
            if (shelf.getCodeNumber() == codeNumber) {
                if (!shelf.isSoldOut()) return shelf.getItem();
                else throw new ItemSoldOutException("VendingMachine.Item sold out");
            }
        }
        throw new InvalidCodeException("Invalid codeNumber entered");
    }

    public void updateSoldOutItem(int codeNumber) {
        for (ItemShelf shelf : inventory) {
            if (codeNumber == shelf.getCodeNumber()) {
                shelf.setSoldOut(true);
            }
        }
    }
}
