import java.util.*;

class Account {
    protected String accountHolderName; 
    protected String accountNumber;     
    protected double balance;           
    
    Account(String name, String accNumber, double initialBalance) {
        this.accountHolderName = name;
        this.accountNumber = accNumber;
        this.balance = initialBalance;
    }

    void displayAccountDetails() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: ₹" + balance);
    }
}


class Savings extends Account {
    private final double interestRate = 4.0; 

    Savings(String name, String accNumber, double initialBalance) {
        super(name, accNumber, initialBalance);
    }

    void deposit(double amount) {
        balance += amount;
        balance += (balance * interestRate / 100);
        System.out.println("₹" + amount + " deposited. Balance after interest: ₹" + balance);
    }

    void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn. \nRemaining Balance: ₹" + balance);
        }
    }
}

class Current extends Account {
    private final double interestRate = 2.0;

    Current(String name, String accNumber, double initialBalance) {
        super(name, accNumber, initialBalance);
    }

    void deposit(double amount) {
        balance += amount;
        balance += (balance * interestRate / 100);
        System.out.println("₹" + amount + " deposited. \nBalance after interest: ₹" + balance);
    }

    void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn. \nRemaining Balance: ₹" + balance);
        }
    }
}


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = null;

        System.out.println("Welcome to the Bank Management System");
        System.out.println("Enter 1 to create a Savings Account");
        System.out.println("Enter 2 to create a Current Account");
        int choice = scanner.nextInt();

    
        System.out.print("Enter Account Holder Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter Account Number: ");
        String accNumber = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();

        if (choice == 1) {
            account = new Savings(name, accNumber, initialBalance);
            System.out.println("Savings Account Created Successfully.");
        } else if (choice == 2) {
            account = new Current(name, accNumber, initialBalance);
            System.out.println("Current Account Created Successfully.");
        } else {
            System.out.println("Invalid choice! Exiting...");
            return;
        }

        
        while (true) {
            System.out.println("\nEnter 1 to Deposit");
            System.out.println("Enter 2 to Withdraw");
            System.out.println("Enter 3 to Display Account Details");
            System.out.println("Enter 4 to Exit");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    if (choice==1) {
                        ((Savings) account).deposit(depositAmount);
                    } else if (choice==2) {
                        ((Current) account).deposit(depositAmount);
                    }
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    if (choice==1) {
                        ((Savings) account).withdraw(withdrawAmount);
                    } else if (choice==2) {
                        ((Current) account).withdraw(withdrawAmount);
                    }
                    break;
                case 3:
                    account.displayAccountDetails();
                    break;
                case 4:
                    System.out.println("Thank you for using the Bank Management System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
