public class SilverCard extends DepositRestrictedCard{

    protected int depositLimit = 2000;
    protected int withdrawLimit = 1000;

    SilverCard(String name){
        super(name);
    }
}
