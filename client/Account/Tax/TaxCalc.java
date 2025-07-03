package client.Account.Tax;

import static java.lang.Math.abs;

public class TaxCalc {
    final static int TAX_RATE_INCOME = 6;
    final static int TAX_RATE_BALANCE = 15;
    final static String TAX_RATE_INCOME_NAME = "УСН по доходам";
    final static String TAX_RATE_BALANCE_NAME = "УСН Доходы-минус-Расходы";


    // merge tax rate amounts info for next selection
    public static void taxRateSelect(int incomeSum, int outcomeSum) {
        int taxAmountIncomeSum, taxAmountBalance, deltaTaxAmount;
        String taxRateInfoBegin = "";
        String taxRateInfoEnd = "";

        taxAmountIncomeSum = taxAmountIncomeSum(incomeSum);
        taxAmountBalance = taxAmountBalance(incomeSum, outcomeSum);
        deltaTaxAmount = taxAmountIncomeSum - taxAmountBalance;
        if (deltaTaxAmount > 0) {
            taxRateInfoBegin += "Мы рекомендуем " + TAX_RATE_BALANCE_NAME + "\n";
            taxRateInfoBegin += "Ваш налог: " + taxAmountBalance + " (руб.)" + "\n";
            taxRateInfoEnd += "Налог на другой системе: " + taxAmountIncomeSum + " (руб.)" + "\n";
        } else if (deltaTaxAmount < 0) {
            taxRateInfoBegin += "Мы рекомендуем " + TAX_RATE_INCOME_NAME + "\n";
            taxRateInfoBegin += "Ваш налог: " + taxAmountIncomeSum + " (руб.)" + "\n";
            taxRateInfoEnd += "Налог на другой системе: " + taxAmountBalance + " (руб.)" + "\n";
        } else {
            taxRateInfoEnd += taxRateInfoBegin += "Можете выбрать любую систему УСН" + "\n";
            taxRateInfoBegin += "Ваш налог: " + taxAmountIncomeSum + " (руб.)" + "\n";
        }
        if (deltaTaxAmount != 0) taxRateInfoEnd += "Экономия: " + abs(deltaTaxAmount) + " (руб.)" + "\n";

        System.out.print(taxRateInfoBegin + taxRateInfoEnd);

    }

    //calc tax amount by TAX_RATE_INCOME
    public static int taxAmountIncomeSum(int incomeSum) {
        return incomeSum * TAX_RATE_INCOME / 100;
    }

    //calc tax amount by TAX_RATE_BALANCE
    public static int taxAmountBalance(int incomeSum, int outcomeSum) {
        int balance = incomeSum - outcomeSum;
        return balance * TAX_RATE_BALANCE / 100;
    }
}

