public class GoldCard extends Card{

    protected int depositLimit = 5000;
    protected int withdrawLimit = 2500;

    GoldCard(String name){
        super(name);
    }

    public boolean isDepositLimitExceeded(int money){
        if(money > depositLimit){
            error = "limit deposit terlampaui";
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isOperationValid(String operation, int money){
        if(operation.equals("add")){
            return !isDepositLimitExceeded(money);
        }else if(operation.equals("sub")){
            return !isWithdrawLimitExceeded(money) && isEnoughMoney(money);
        }else{
            return false;
        }
    }
}
