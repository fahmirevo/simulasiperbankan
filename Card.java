public class Card extends Account{

    public String error;
    public String FORCE = "BYPASS VALIDATION";
    protected int withdrawLimit;
    protected String key = "123456";

    Card(String name){
        this.name = name;
        this.money = 0;
    }

    public boolean authorize(String key){
        if(key.equals(this.key)){
            return true;
        }else{
            error = "pin salah";
            return false;
        }
    }

    public boolean isEnoughMoney(int money){
        if(this.money >= money){
            return true;
        }else{
            error = "saldo tidak cukup";
            return false;
        }
    }

    public boolean isWithdrawLimitExceeded(int money){
        if(money > withdrawLimit){
            error = "limit tarik tunai terlampaui";
            return true;
        }else{
            return false;
        }
    }

    public boolean isOperationValid(String operation, int money){
        if(operation.equals("add")){
            return true;
        }else if(operation.equals("sub")){
            return isEnoughMoney(money) && !isWithdrawLimitExceeded(money);
        }else{
            error = "Not valid operation";
            return false;
        }
    }

    public boolean isOperationValid(String operation, int money, String key){
        if(operation.equals("add")){
            return true;
        }else if(operation.equals("sub")){
            return authorize(key) && isEnoughMoney(money) && !isWithdrawLimitExceeded(money);
        }else{
            error = "Not valid operation";
            return false;
        }
    }

    public boolean setMoney(int money){
        if(money < 0){
            return false;
        }else{
            this.money = money;
            return true;
        }
    }

    public boolean addMoney(int money){
        this.money += money;
        return true;
    }

    public boolean subMoney(int money){
        String operation = "add";
        if(isOperationValid(operation, money)){
            this.money -= money;
            return true;
        }else{
            return false;
        }
    }

    public boolean subMoney(int money, String force){
        if(force == this.FORCE && isEnoughMoney(money)){
            this.money -= money;
            return true;
        }else{
            return subMoney(money);
        }
    }

}

