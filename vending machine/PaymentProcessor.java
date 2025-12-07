public class PaymentProcessor {
    long amountDue = 0;
    long amountPaid = 0; 
    boolean Cancel = false;

    public PaymentProcessor (long amountDue) {
        this.amountDue = amountDue;
    }

    public boolean isCancelled() {
        return Cancel;
    }

    public void cancel() {
        Cancel = true;
    }

    public long refundAmount() {
        return amountPaid;
    }

    public boolean isCompleted() {
        return amountPaid >= amountDue;
    }

    public long getAmountPaid() {
        return amountPaid;
    }

    public long getAmoutRemaining() {
        return Math.max(amountDue - amountPaid, 0);
    }

    public boolean insert(long amount) {
        if(isCancelled()) {
            return false;
        }

        if(amount <= 0) {
            System.out.println("entered amount must be positive");
            return false;
        }

        if (amountPaid + amount > amountDue) {
            // will implement the changeMaker later
            return false;
        }
        amountPaid += amount;
        return true;
    }
}
