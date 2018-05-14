public class Card extends Account{

    protected String name;
    protected int money;

    public String error;
    protected int withdrawLimit;
    protected String key = "123456";

    Card(String name){
        this.name = name;
        this.money = 0;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getMoney(){
        return this.money;
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

    public boolean subMoney(int money, boolean isBypass){
        if(isBypass && isEnoughMoney(money)){
            this.money -= money;
            return true;
        }else{
            return subMoney(money);
        }
    }
}
