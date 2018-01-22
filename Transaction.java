public class Transaction{

    private Card issuer;
    private String action;
    private int money;
    private String key;
    private boolean force = false;

    Transaction(Card issuer, String action, int money, String key){
        this.issuer = issuer;
        this.action = action;
        this.money = money;
        this.key = key;
    }

    Transaction(Card issuer, String action, int money, boolean force){
        this.issuer = issuer;
        this.action = action;
        this.money = money;
        this.force = force;
    }

    public int getMoney(){
        return money;
    }

    public String getIssuerName(){
        return issuer.getName();
    }

    public boolean isValid(){
        if(force && action.equals("sub")){
            return issuer.isEnoughMoney(money);
        }else if(action.equals("sub")){
            return issuer.isOperationValid(action, money, key);
        }else{
            return issuer.isOperationValid(action, money);
        }
    }

    public String getError(){
        String act;

        if(action.equals("add")){
            act = "GAGAL deposit ";
        }else{
            act = "GAGAL menarik ";
        }
        act += money + " pada " + issuer.getName();
        return act + " [" + issuer.error + "]";
    }

    public String getSuccessMessage(){
        String msg;
        if(action.equals("add")){
            msg = "BERHASIL deposit " + money + " pada " + issuer.getName();
        }else{
            msg = "BERHASIL menarik " + money + " pada " + issuer.getName();
        }

        return msg;
    }

    public void serve(){
        if(isValid()){
            if(force && action.equals("sub")){
                issuer.subMoney(money, issuer.FORCE);
            }else if(action.equals("sub")){
                issuer.subMoney(money);
            }else{
                issuer.addMoney(money);
            }
        }
    }
}
