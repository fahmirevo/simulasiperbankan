public abstract class Account{
    protected String name;
    protected int money;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getMoney(){
        return this.money;
    }

    public abstract boolean authorize(String key);

}
