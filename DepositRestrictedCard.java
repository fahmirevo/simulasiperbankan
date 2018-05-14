public class DepositRestrictedCard extends Card
{
    protected int depositLimit;

    DepositRestrictedCard(String name){
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

