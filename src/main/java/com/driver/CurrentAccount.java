package com.driver;

import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        if(balance < 5000)
            throw new InsufficientBalanceException("Insufficient Balance");
        this.tradeLicenseId = tradeLicenseId;

    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!checkValidityOfLicense(tradeLicenseId)){
            if(!rearrangedLicensedId(tradeLicenseId))
                    throw new InvalidLicenseIdException("Valid License can not be generated");

        }


    }

    private boolean rearrangedLicensedId(String str) {

        //store the frequency of each character
        int[] freqArr = new int[str.length()];
        for(int i=0;i<freqArr.length;i++){
            freqArr[str.charAt(i)-'A']++;
        }

        //add the char to priority queue in decreasing order of their frequencies
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((Pair p1, Pair p2)->{
            return p2.freq - p1.freq;
        });

        for(char c = 'A';c<='Z';c++){
            int val = c - 'A';
            if(freqArr[val]>0)
                pq.add(new Pair(c,freqArr[val]));
        }

        String resStr = ""; //to store rearranged string
        Pair prev = new Pair('#',-1); //dummy pair
        while (pq.size() > 0){

            Pair p = pq.peek();
            pq.poll();
            resStr+=p.ch;

            if(prev.freq >0)
                pq.add(prev);

            p.freq--; //reduce the frequency of current pair
            prev = p;  // make current pair as previous pair
        }

        if(resStr.length() != str.length())
            return false;
        this.tradeLicenseId = resStr; // assign rearranged string as tradeLicensedId and return true
        return true;
    }

    private boolean checkValidityOfLicense(String str) {
        for(int i=1;i<str.length();i++){
            if(str.charAt(i-1) == str.charAt(i))
                return false;
        }
        return true;
    }

}
