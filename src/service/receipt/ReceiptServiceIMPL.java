package service.receipt;

import config.ConfigReadAndWriteFile;
import model.Receipt;

import java.util.List;

public class ReceiptServiceIMPL implements IReceiptService {

    public static String PATH_RECEIPT = ConfigReadAndWriteFile.PATH + "receipt.txt";
    public static List<Receipt> receipts = new ConfigReadAndWriteFile<Receipt>().readFromFile(PATH_RECEIPT);


    @Override
    public void edit(int id, Receipt receipt) {

    }

    @Override
    public void delete(int id, Receipt receipt) {

    }

    @Override
    public List<Receipt> findAll() {
        new ConfigReadAndWriteFile<Receipt>().writeToFile(PATH_RECEIPT, receipts);
        return receipts;
    }

    @Override
    public void save(Receipt receipt) {
        receipts.add(receipt);
    }

    @Override
    public Receipt findById(int id) {
        for (int i = 0; i < receipts.size(); i++) {
            if (id == receipts.get(i).getReceiptID()) {
                return receipts.get(i);
            }
        }
        return null;
    }
}