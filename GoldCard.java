public class GoldCard extends DepositRestrictedCard{

    protected int depositLimit = 5000;
    protected int withdrawLimit = 2500;

    GoldCard(String name){
        super(name);
    }
}
