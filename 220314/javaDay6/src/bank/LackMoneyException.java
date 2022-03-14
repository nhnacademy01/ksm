package bank;

public class LackMoneyException extends InvalidMoneyException {
    public LackMoneyException(String message) {
        super(message);
    }
}
