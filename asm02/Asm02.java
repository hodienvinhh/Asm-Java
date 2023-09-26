package vn.funix.FX21678.asm02;

import vn.funix.FX21678.asm01.Asm01;
import vn.funix.FX21678.asm02.models.Account;
import vn.funix.FX21678.asm02.models.Bank;
import vn.funix.FX21678.asm02.models.Customer;

import java.util.*;
import java.util.stream.Collectors;

public class Asm02 {
    public static final String AUTHOR = "FX21678";
    public static final String VERSION = "v2.0.0";

    private static final Bank bank = new Bank();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("+----------+-------------------------+---------+");
            System.out.println("| NGÂN HÀNG SỐ | " + AUTHOR + "@" + VERSION);
            System.out.println("+----------+-------------------------+---------+");
            System.out.print("|   1. Thêm khách hàng \n");
            System.out.print("|   2. Thêm tài khoản khách hàng \n");
            System.out.print("|   3. Hiện thị danh sách khách hàng \n");
            System.out.print("|   4. Tìm theo CCCD \n");
            System.out.print("|   5. Tìm theo tên khách hàng \n");
            System.out.print("|   0. Thoát \n");
            System.out.println("+----------+-------------------------+---------+");
            System.out.println("Xin mời lựa chọn chức năng : ");
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    addNewCustomer();
                    break;
                case 2:
                    addAccountCustomer();
                    break;
                case 3:
                    viewListCustomer();
                    break;
                case 4:
                    findCustomerByCCCD();
                    break;
                case 5:
                    findCustomerByName();
                    break;
                case 0:
                    System.out.println("Thoát khỏi chương trình!");
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 0 -> 5 để sử dụng chức năng");
            }
        }
    }

    // Chức năng 1: Thêm khách hàng vào ngân hàng
    public static void addNewCustomer() {
        scanner.nextLine();
        System.out.println("Mời nhập tên khách hàng :");
        String name = scanner.nextLine();
        System.out.println("Mời nhập số CCCD :");
        Customer customer = new Customer();
        List<String> listCCCD = bank.getCustomers().stream().map(cus -> cus.getCustomerId()).collect(Collectors.toList());
//        List<String> listCCCD1 = new ArrayList<>();
//        for (Customer cus : bank.getCustomers()) {
//            listCCCD1.add(cus.getCustomerId());
//        }
        while (true) {
            String cccd = scanner.nextLine();
            if (cccd.equals("No")) {
                return;
            }

            if (!Asm01.checkCCCD(cccd)) {  // Kiểm tra điều kiện nhập CCCD ở Asm01
                System.out.println("Số CCCD không hợp lệ .");
                System.out.println("Vui lòng nhập lại hoặc chọn 'No' để thoát !");
            } else if (listCCCD.contains(cccd)) { // Kiểm tra số CCCD đã tồn tại trong danh sách hay chưa
                System.out.println("Số CCCD đã tồn tại.");
                System.out.println("Vui lòng nhập lại : ");
            } else {
                customer.setCustomerId(cccd);
                customer.setName(name);
                bank.getCustomers().add(customer);
                System.out.println("Đã thêm khách hàng " + cccd + " vào danh sách");
                return;
            }
        }
    }

    // Kiểm tra STK nhập vào có hợp lệ hay không
    public static boolean checkSTK(String stk) {
        List<String> listCCCD = bank.getCustomers().stream().map(cus -> cus.getCustomerId()).collect(Collectors.toList());
        if (stk.length() != 6) {
            return false;
        }
        if (!Asm01.checkInt(stk)) {
            return false;
        }
        if (listCCCD.contains(stk))
            return false;
        return true;
    }

    // Chức năng 2: Thêm tài khoản cho khách hàng
    public static void addAccountCustomer() {
        scanner.nextLine();
        System.out.println("Nhập số CCCD :");
        List<String> listCCCD = bank.getCustomers().stream().map(cus -> cus.getCustomerId()).collect(Collectors.toList());
        while (true) {
            String cccd = scanner.nextLine();
            if (!listCCCD.contains(cccd)) {
                System.out.println("Số CCCD không tồn tại trong hệ thống ");
                System.out.println("Vui lòng nhập lại :");
            } else {
                System.out.println("Nhập mã STK gồm 6 chữ số :");
                while (true) {
                    String stk = scanner.nextLine();
                    if (!checkSTK(stk)) {
                        System.out.println("STK không hợp lệ !");
                    } else {
                        // Kiểm tra xem khách hàng có tồn tại hay không
                        Customer customer = bank.getCustomers().stream().filter(c -> c.getCustomerId().equals(cccd)).findFirst().get();
//                        Customer rs = new Customer();
//                        for (Customer cus : bank.getCustomers()) {
//                            if (cus.getCustomerId().equals(cccd)){
//                                rs = cus;
//                                break;
//                            }
//                        }

                        Account account = new Account();
                        account.setAccountNumber(stk);
                        System.out.println("Nhập số dư tài khoản :");
                        while (true) {
                            double soDu = scanner.nextDouble();
                            if (soDu < 50000) {
                                System.out.println("Số dư phải lớn hơn 50000");
                            } else {
                                account.setBalance(soDu);
                                break;
                            }
                        }
                        customer.getAccounts().add(account);
                        System.out.println("Thêm tài khoản thành công !");
                        return;
                    }
                }
            }
        }
    }

    // Chức năng 3: Hiển thị danh sách khách hàng
    public static void viewListCustomer() {
        System.out.println("Danh sách khách hàng : ");
        for (Customer customer : bank.getCustomers()) {
            customer.displayInformation();
        }
    }

    // Chức năng 4: Tìm theo CCCD khách hàng
    public static void findCustomerByCCCD() {
        scanner.nextLine();
        System.out.println("Nhập CCCD khách hàng : ");
        String cccd = scanner.nextLine();
        Optional<Customer> oCustomer = bank.getCustomers().stream().filter(c -> c.getCustomerId().equals(cccd)).findFirst();
           if (oCustomer.isPresent()) {
               System.out.println("Thông tin của khách hàng có số CCCD " + cccd + " là :");
               oCustomer.get().displayInformation();
           } else {
               System.out.println("Không tìm thấy khách hàng có số CCCD là " + cccd);
        }

    }

    // Chức năng 5: Tìm theo tên khách hàng
    public static void findCustomerByName() {
        scanner.nextLine();
        System.out.println("Nhập tên của khách hàng :");
        String name = scanner.nextLine();
        List<Customer> customers = bank.getCustomers().stream().filter(c -> c.getName().contains(name)).collect(Collectors.toList());
        if (customers.size() != 0) {
            for (Customer customer : customers) {
                System.out.println("Thông tin khách hàng :");
                customer.displayInformation();
            }
        } else {
            System.out.println("Không có thông tin của khách hàng tên là " + name);
        }
    }
//        List<Customer> customers = new ArrayList<>();
//        if (customers.size() != 0) {
//            for (Customer customer : bank.getCustomers()) {
//                if (customer.getName().contains(name)) {
//                    System.out.println("Thông tin khách hàng :");
//                    customer.displayInformation();
//                } else {
//                    System.out.println("Không có thông tin của khách hàng tên là " + name);
//                }
//            }
//
//        }
//    }
}
