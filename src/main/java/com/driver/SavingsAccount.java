package com.driver;

import javax.naming.LimitExceededException;

public class SavingsAccount extends BankAccount{
    double rate;
    double maxWithdrawalLimit;

    double balance;

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        // minimum balance is 0 by default
        super(name,balance,0);

    }
    public void withdraw(double amount) throws Exception {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance
        if(maxWithdrawalLimit < amount)
            throw new LimitExceededException("Maximum Withdraw Limit Exceed");
        else if (amount > balance) {
            throw  new InsufficientBalanceException("Insufficient Balance");
        }else
            balance-=amount;

    }

    public double getSimpleInterest(int years){
        // Return the final amount considering that bank gives simple interest on current amount
        double SI=(balance*rate*years)/100;
        return balance+=SI;

    }

    public double getCompoundInterest(int times, int years){
        // Return the final amount considering that bank gives compound interest on current amount given times per year
        double amount = balance*(Math.pow((1+(rate/years)),(years*times)));
        return amount;
    }

}
