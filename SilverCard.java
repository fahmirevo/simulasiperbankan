public class SilverCard extends DepositRestrictedCard{

    SilverCard(String name){
        super(name);
        depositLimit = 2000;
        withdrawLimit = 1000;
    }
}
