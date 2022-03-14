package bank;

public class InvalidCurrencyException extends InvalidMoneyException{
    public InvalidCurrencyException(String message) {
        super(message);
    }
}
