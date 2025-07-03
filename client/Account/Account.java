package client.Account;

public class Account {

    //check user input
    public static boolean isAmountNegative(int income) {
        return income < 0;
    }

    public static String incomeOutcomeErr() {
        return "Некорректный ввод суммы дохода или расхода!";
    }

    // next account value cannot be negative
    public static boolean isOutcomeOverrun(int outcome, int incomeSum) {
        return (incomeSum - outcome) < 0;
    }

    public static String outcomeErr() {
        return "Сумма расходов превыщает баланс счёта! Операция отменена!";
    }

    // show current account info
    public static String getAccountInfo(int incomeSum, int outcomeSum) {
        int balance = incomeSum - outcomeSum;
        return "Баланс: " + balance + ", Приходы: " + incomeSum + ", Расходы :" + outcomeSum;
    }

    // increase sum of incoming bills
    public static int calcIncomeSum(int income, int incomeSum) {

        if (Account.isAmountNegative(income)) {
            System.out.println(Account.incomeOutcomeErr());
            return incomeSum;
        } else return incomeSum + income;
    }

    // increase sum of outgoing bills prevent the account from overrunning
    public static int calcOutcomeSum(int outcome, int incomeSum, int outcomeSum) {
        int calcOutcomeSum = outcomeSum + outcome;
        if (Account.isAmountNegative(outcome)) {
            System.out.println(Account.incomeOutcomeErr());
            return outcomeSum;
        } else if (isOutcomeOverrun(calcOutcomeSum, incomeSum)) {
            System.out.println(outcomeErr());
            return outcomeSum;
        } else return calcOutcomeSum;
    }

}




