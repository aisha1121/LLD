package VendingMachine.VendingStates;

import VendingMachine.Coin;
import VendingMachine.Item;
import VendingMachine.VendingMachine;

import java.util.List;

public interface State {
    void pressInsertCoins(VendingMachine machine) throws Exception;
    void pressProductSelectionButton(VendingMachine machine) throws Exception;
    void insertCoin(VendingMachine machine, Coin coin) throws Exception;
    void selectProduct(VendingMachine machine, int codeNumber) throws Exception;
    List<Coin> refundAllMoney(VendingMachine machine) throws Exception;
    int getChange(int amt) throws Exception;
    Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception;
}
