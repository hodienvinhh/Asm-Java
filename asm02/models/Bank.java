package vn.funix.FX21678.asm02.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private String id;
    private List<Customer> customers;

    public Bank() {
        this.id = String.valueOf(UUID.randomUUID());
        this.customers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
    public void addCustomer(Customer newCustomer){
        if (!customers.contains(newCustomer.getCustomerId())){
            customers.add(newCustomer);
        }else {
            System.out.println("CCCD này đã được sử dụng");
        }
    }
    public void addAccount(String customerId , Account account){
        for(Customer customer : customers){
            if (customer.getCustomerId().equals(customerId)){
                customer.addAccount(account);
                return;
            }
        }
    }

    public boolean isCustomerExisted(String customerId) {
        if (customers.contains(customerId)) {
            return true;
        }
        return false;
    }

}
