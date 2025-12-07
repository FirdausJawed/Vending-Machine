import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        VendingMachine vm = new VendingMachine();
        Product p1 = new Product("P1", "Chocolate", 150);
        Product p2 = new Product("P2", "chips", 200);

        vm.addSlot(new Slot("A1", p1, 10, 3));
        vm.addSlot(new Slot("A2", p2, 10, 2));

        // Menu
        System.out.println("=== Welcome to Vending Machine ===");
        System.out.println("1. Browse items");
        System.out.println("2. Order item");
        System.out.print("Enter your choice (1 or 2): ");
        String choice = sc.nextLine().trim();

        if (choice.equals("1")) {
            // Browse items
            System.out.println("\n--- Available Items ---");
            for (Slot s : vm.browse()) {
                System.out.println(s.getId() + " | " + s.getProduct().getName() + 
                                   " | Price: " + s.getProduct().getPrice() + 
                                   " | Quantity: " + s.getQuantity());
            }
        } else if (choice.equals("2")) {

            System.out.print("Enter slot id: ");
            String slotId = sc.nextLine().trim();

            Slot slot = vm.getSlot(slotId);
            if(slot == null) {
                System.out.println("invalid slot id");
                return;
            }

            boolean ok = vm.select(slot.getId());
            if (!ok) {
                System.out.println("Invalid or empty slot");
                return;
            }

            System.out.println("Insert required amount of money as : " + slot.getProduct().price);
        
            int money = Integer.parseInt(sc.nextLine());
            boolean accepted = vm.insertMoney(money);
            if(!accepted) {
                System.out.println("enter the exact amount");
            }
            vm.confirmAndDispense();
            sc.close();
        }
    }
}
