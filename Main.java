import java.util.InputMismatchException;
import java.util.Scanner;

// BankAccount class to represent a user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Rs " + amount + " has been deposited successfully.");
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Rs " + amount + " has been withdrawn successfully.");
        } else if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void checkBalance() {
        System.out.println("Your current balance is: Rs " + balance);
    }
}

// ATM class to interact with the user
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void userInterface() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to the ATM Interface");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");

            System.out.print("Please choose an option: ");
            int option = 0;

            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // Consume the invalid input
                continue;
            }

            switch (option) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = getValidAmount(scanner);
                    if (depositAmount >= 0) {
                        account.deposit(depositAmount);
                    }
                    break;
                case 3:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = getValidAmount(scanner);
                    if (withdrawAmount >= 0) {
                        account.withdraw(withdrawAmount);
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private double getValidAmount(Scanner scanner) {
        double amount = -1;
        try {
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Invalid amount. Amount must be non-negative.");
                return -1;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Consume the invalid input
        }
        return amount;
    }
}

// Main class to run the ATM
public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(5000.00); // Initialize with a starting balance
        ATM atm = new ATM(account);
        atm.userInterface();
    }
}
