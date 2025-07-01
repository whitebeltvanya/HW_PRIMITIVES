package client.Account;

import java.math.BigDecimal;

public class Account {

    //check user input
    public static boolean isAmountNegative (BigDecimal income) {
        return income.signum() <0;
    }

    public static String incomeOutcomeErr(){
        return "Некорректный ввод суммы дохода или расхода!";
    }

   // next account value cannot be negative
   public static boolean isOutcomeOverrun (BigDecimal outcome, BigDecimal incomeSum){
        return incomeSum.compareTo(outcome) < 0;
   }

    public static String outcomeErr(){
        return "Сумма расходов превыщает баланс счёта! Операция отменена!";
    }

    // show current account info
    public static String getAccountInfo(BigDecimal incomeSum, BigDecimal outcomeSum){
        BigDecimal balance = incomeSum.subtract(outcomeSum);
        return "Баланс: " + balance.toString() + ", Приходы: " + incomeSum.toString() + ", Расходы :" + outcomeSum.toString();
    }
    // increase sum of incoming bills
    public static BigDecimal calcIncomeSum (BigDecimal income, BigDecimal incomeSum) {

        if(Account.isAmountNegative(income)) {
            System.out.println(Account.incomeOutcomeErr());
            return  incomeSum;
        }
        else return incomeSum.add(income);
    }
    // increase sum of outgoing bills prevent the account from overrunning
    public static BigDecimal calcOutcomeSum (BigDecimal outcome, BigDecimal incomeSum, BigDecimal outcomeSum) {
        BigDecimal calcOutcomeSum =  outcomeSum.add(outcome);
        if (Account.isAmountNegative(outcome)) {
            System.out.println(Account.incomeOutcomeErr());
            return outcomeSum;
        } else if (isOutcomeOverrun(calcOutcomeSum, incomeSum)) {
            System.out.println(outcomeErr());
            return outcomeSum;
        } else return calcOutcomeSum;
    }

}




