package Properties;

/**
 * Created by Raymond Phua on 20-9-2016.
 */
public enum TransactionType {
    SALE, RESERVATION, REFUND;

    public static TransactionType fromString(String type) {

        String answer = type.toLowerCase();
        switch(answer) {
            case "sale": return TransactionType.SALE;
            case "reservation": return TransactionType.RESERVATION;
            case "refund": return TransactionType.REFUND;
            default: return TransactionType.SALE;
        }
    }
}
