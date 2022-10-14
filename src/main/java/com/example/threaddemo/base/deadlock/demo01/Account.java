package com.example.threaddemo.base.deadlock.demo01;

/**
 * @author 帅气的景天老师
 * @create 2021/7/9 19:44
 */
public class Account {
    private String accountName;
    private int balance;

    public Account(String accountName, int balance) {
        this.accountName = accountName;
        this.balance = balance;
    }

    public void debit(int amount) {//更新转出方的余额
        this.balance -= amount;
    }

    public void credit(int amount) {//更新转入方的余额
        this.balance += amount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
