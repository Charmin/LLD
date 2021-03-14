package flipkart;

import flipkart.digitalwallet.DigitalBank;

import java.util.List;

/*
Requirements
    The smallest amount that the users can transfer is F₹ 0.0001. The description of the wallet operations follows.
    The command CreateWallet <accountHolder1> <amount> creates a new wallet with a balance of F₹ <amount> in the name of <accountHolder1>.
    The command TransferMoney <accountHolder1> <accountHolder2> <amount> would decrease F₹ <amount> from accountHolder1’s account and add the same amount in accountHolder2’s account.
    The command Statement <accountHolder1> should display the account statement for accountHolder1’s  account.
    The account statement should contain all the transactions made in that account.
    The command Overview should display the current balance of all the accounts.

    Your wallet system also provides some offers to the customers.
    Offer 1: When customer A transfers money to customer B and both the account holders have the same balance after the transaction then both the customers get F₹ 10 as a reward
    Bonus: Whenever the command Offer2 is fired 3 customers with the highest number of transactions will get F₹ 10, F₹ 5, and F₹ 2 as rewards.
    If there is a tie (customers having the same number of transactions excluding offer transactions) then the customer having higher account balance should be given preference.
    If there is still a tie then the customer whose account was created first should be given preference

    DigitalBank (Singleton)
      - GlobalTransactionsView
      - Get all offers applied
      - AccountService


    Account
      - Wallet
      - AccountId
      - Name
      - Creation Time

    Wallet
      - Balance
      - List<Transactions>

    Transaction
       TransactionType
       Amount

    TransactionType
       CREDIT
       DEBIT
       OFFER

    OfferService
       - apply(Account)

    OfferService : Offer1
    OfferService : Offer2


    AccountService
       TransferMoney()
       CreateWallet()
       ShowStatement()
       BalanceOverview()
*/
public class Driver {
    public static void main(String[] args) {
        DigitalBank digitalBank = DigitalBank.getInstance();

        digitalBank.createWallet("ann", 120.00);
        digitalBank.createWallet("ben", 800.00);
        digitalBank.createWallet("cal", 100.00);
        digitalBank.createWallet("don", 200.00);
        digitalBank.createWallet("ema", 500.00);

        digitalBank.transferMoney("ann", "ben", 20.00);
        digitalBank.transferMoney("ann", "cal", 50.00);
        System.out.println(digitalBank.getOverview("cal"));
        digitalBank.transferMoney("ann", "don", 50.00); //error
        System.out.println(digitalBank.getOverview("don"));
        digitalBank.transferMoney("ema", "ann", 50.00);
        digitalBank.transferMoney("don", "ann", 100.00);

        System.out.println(digitalBank.getOverview("ben"));
        System.out.println(digitalBank.getOverview("ema"));
        System.out.println(digitalBank.getOverview("ann"));

        digitalBank.applyOffer2();
        System.out.println("Ben");
        printStatement(digitalBank.getStatement("ben"));
        System.out.println("Ema");
        printStatement(digitalBank.getStatement("ema"));
        System.out.println("Ann");
        printStatement(digitalBank.getStatement("ann"));

    }

    private static void printStatement(List<String> statements) {
        statements.forEach(System.out::println);
    }
}
