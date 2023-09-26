package vn.funix.FX21678.asm02.models;

import vn.funix.FX21678.asm01.Asm01;

public abstract class User {
    private String name;
    private String customerId;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        try{
            if (Asm01.checkCCCD(customerId) && Asm01.checkInt(customerId)){
                this.customerId = customerId;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
