public class GoldCard extends DepositRestrictedCard{

    GoldCard(String name){
        super(name);
        depositLimit = 5000;
        withdrawLimit = 2500;
    }
}
