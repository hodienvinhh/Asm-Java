package vn.funix.FX21678.asm02.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Customer extends User{

     List<Account> accounts;

    public Customer() {
        this.accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public boolean isPremium(){
        for (Account account : accounts) {
            if (account.isPremium()) {
                return true;
            }
        }
        return false;
    }
    public void addAccount(Account newAccount){
        if (!accounts.contains(newAccount.getAccountNumber())){
            accounts.add(newAccount);
        }else {
            System.out.println("STK đã được sử dụng");
        }
    }
    public double getBalance(){
        double total = 0;
        for (Account account : accounts){
            total += account.getBalance();
        }

        return total;
    }
    public void displayInformation() {
        Locale locale = new Locale("vi", "VI");//Tạo một đối tượng đại diện cho Locale để lưu trữ thông tin vị trí địa lý
        String pattern = "###,###,###,###";  //Tạo một pattern để đặt định dạng cho số
        /* Tạo một thể hiện của DecimalFormat,
        sau đó dùng hàm getNumberInstance để định dạng số
        theo tiêu chuẩn của locale được truyền vào*/
        DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        dcf.applyPattern(pattern); // Áp dụng mẫu pattern = "###,###.##" cho decimalFormat

        System.out.printf("%18s  |%18s  |%18s  |%18s\n", this.getCustomerId(), this.getName(), this.isPremium()?"Premium":"Normal", dcf.format(this.getBalance())+"đ");
        int i = 1;
        for (Account acc : accounts) {
            System.out.printf("%-3s%15s  |%60s\n", i, acc.getAccountNumber(),  dcf.format(acc.getBalance())+"đ");
            i++;
        }
    }

}
