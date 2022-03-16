package bank;

public class InvalidMoneyException extends IllegalArgumentException {
    public InvalidMoneyException(String message) {
        super(message);
    }
}