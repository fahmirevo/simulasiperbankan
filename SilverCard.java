public class SilverCard extends Card{

    private int depositLimit = 2000;

    SilverCard(String name){
        super(name);
        withdrawLimit = 1000;
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
