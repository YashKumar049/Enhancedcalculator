import java.util.Scanner;

class BankAccount {
    private String accountHolder;
    private double balance;

    /**
     * @param name
     * @param initialBalance
     */
    public BankAccount(String name, double initialBalance) {
        accountHolder = name;
        balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public void displayBalance() {
        System.out.println(accountHolder + "'s Current Balance: " + balance);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        BankAccount account = new BankAccount(name, 0);

        while (true) {
            System.out.println("\n1. Deposit  2. Withdraw  3. Balance  4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double deposit = sc.nextDouble();
                    account.deposit(deposit);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdraw = sc.nextDouble();
                    account.withdraw(withdraw);
                    break;
                case 3:
                    account.displayBalance();
                    break;
                case 4:
                    System.out.println("Thank you! Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}
