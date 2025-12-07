import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    Map<String, Slot> slots = new HashMap<>();
    String activeSlotId = null;
    PaymentProcessor activePayment = null;

    // user will browse the items in the slot
    public List<Slot> browse() {
        return new ArrayList<>(slots.values());
    }

    // user can select the item to buy it
    public boolean select (String slotId) {
        //creates payment processor and reserves one item by decrementing quantity

        if(activePayment != null) {
            System.out.println("cancel or complete current txn");
            return false;
        }

        Slot s = slots.get(slotId);

        if (s == null) {
            System.out.println("Slot not found.");
            return false;
        }

        if(s.quantity <= 0) {
            System.out.println("out of stock");
            return false;
        }

        activePayment = new PaymentProcessor(s.getProduct().getPrice());
        activeSlotId = slotId;
        //reserve slot
        s.quantity--;
        return true;
    }

    // after selecting the user will insert money

    public boolean insertMoney(long amount) {
        if(activePayment == null) {
            System.out.println("no any active txn!!");
            return false;
        }
        boolean insertion = activePayment.insert(amount);
        return insertion;
    }

    // confirm and dispense the product

    public void confirmAndDispense() {
        //complete if fully paid
        if(activePayment == null) {
            System.out.println("no any active txn!!");
            return;
        }

        if(!activePayment.isCompleted()) {
            System.out.println("payment is due!!");
            return;
        }

        if(!dispense(activeSlotId)) {
            System.out.println("hardware failure");
            //refund 
            Slot s = slots.get(activeSlotId);
            long refund = activePayment.refundAmount();
            System.out.println("refunded amount : " + refund);
            if (s != null) s.quantity += 1;
            return;
        }
        else{
            System.out.println("dispensed the product");
        }

        activeSlotId = null;
        activePayment = null;
        System.out.println("Dispensed the product with slot id : " + activeSlotId);
    }

    private boolean dispense(String slotId) {
        // Real implementation would call hardware
        // For simplicity: always true
        System.out.println("Calling dispenser for slot " + slotId + " ...");
        return true;
    }

    public void addSlot(Slot slot) {
        slots.put(slot.id, slot);
    }

    public Slot getSlot(String slotId) {
        return slots.getOrDefault(slotId, null);
    }

    public void cancel() {
       if (activePayment == null) {
            System.out.println("No active transaction.");
            return;
        }
        int refund = (int) activePayment.refundAmount();
        activePayment.cancel();
        // restore slot quantity
        Slot s = slots.get(activeSlotId);
        if (s != null) s.quantity += 1;
        System.out.println("Transaction cancelled. Refunded: " + refund);
        activePayment = null;
        activeSlotId = null;
    }
}
