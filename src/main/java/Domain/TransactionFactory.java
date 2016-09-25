package Domain;

import Properties.TransactionType;

/**
 * Created by Raymond Phua on 20-9-2016.
 */
public class TransactionFactory {

    public Transaction createTransaction(TransactionType transactionType) {

        switch(transactionType) {
            case SALE: return new Sale();
            case REFUND: return new Refund();
            case RESERVATION: return new Reservation();
            default: return null;
        }
    }
}
