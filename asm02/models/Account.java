package vn.funix.FX21678.asm02.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Account {
    private String accountNumber;
    private double balance;

    public Account() {
    }

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public  boolean isPremium(){
        if (this.balance >= 10000000 ){
            return true;
        }
        return false;
    }

    public String toString(){

        Locale locale = new Locale("vi", "VI");
        String pattern = "###,###,###,###";
        DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        dcf.applyPattern(pattern);

        String accountString = "\t\t    "+this.accountNumber+" |\t\t\t\t                       "+ dcf.format(this.balance);
        return accountString;
    }
}
