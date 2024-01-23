
class BankAccountDetail{

    private String accountHolderName;
    private int  Balance;


    BankAccountDetail(String accountHolderName, int Balance){
        this.accountHolderName = accountHolderName;
        this.Balance = Balance;
    }

    public String getAccountHolderName(){
        return this.accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }
}


public class Encapsulation {

    public static void main(String[] args){

        BankAccountDetail bankAccountDetail = new BankAccountDetail("Ravi",10000000);

        System.out.println(bankAccountDetail.getAccountHolderName());        //Ravi
        System.out.println(bankAccountDetail.getBalance());                  //10000000

        bankAccountDetail.setBalance(1999999999);

        System.out.println(bankAccountDetail.getBalance());                 //1999999999

    }

}
