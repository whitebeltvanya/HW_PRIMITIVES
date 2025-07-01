package client.Account.Tax;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class TaxCalc {
    final static int TAX_RATE_INCOME = 6;
    final static int TAX_RATE_BALANCE = 15;
    final static String TAX_RATE_INCOME_NAME = "УСН по доходам";
    final static String TAX_RATE_BALANCE_NAME = "УСН Доходы-минус-Расходы";
    final static int TAX_RATE_INCOME_ID =-1;
    final static int TAX_RATE_BALANCE_ID =1;

    // merge tax rate amounts info for nex selection
    public static int taxRateSelect(BigDecimal incomeSum, BigDecimal outcomeSum){
        BigDecimal taxAmountIncomeSum, taxAmountBalance, deltaTaxAmount;
        String taxRateInfoBegin ="";
        String taxRateInfoEnd ="";

        taxAmountIncomeSum = taxAmountIncomeSum(incomeSum);
        taxAmountBalance   = taxAmountBalance(incomeSum, outcomeSum);
        deltaTaxAmount     = taxAmountIncomeSum.subtract(taxAmountBalance);
        if(deltaTaxAmount.signum()>0) {
            taxRateInfoBegin += "Мы рекомендуем "+TAX_RATE_BALANCE_NAME +"\n";
            taxRateInfoBegin += "Ваш налог: " + taxAmountBalance.toString() +" (руб.)"+ "\n";
            taxRateInfoEnd += "Налог на другой системе: "+ taxAmountIncomeSum.toString()+ " (руб.)"+ "\n";
        } else if (deltaTaxAmount.signum()<0){
            taxRateInfoBegin += "Мы рекомендуем "+TAX_RATE_INCOME_NAME +"\n";
            taxRateInfoBegin += "Ваш налог: " + taxAmountIncomeSum.toString() +" (руб.)"+ "\n";
            taxRateInfoEnd += "Налог на другой системе: "+ taxAmountBalance.toString()+ " (руб.)"+ "\n";
        } else {
            taxRateInfoEnd +=  taxRateInfoBegin += "Можете выбрать любую систему УСН" + "\n";
            taxRateInfoBegin += "Ваш налог: " + taxAmountIncomeSum.toString() +" (руб.)"+ "\n";
        }
        if(deltaTaxAmount.signum()!=0)
            taxRateInfoEnd += "Экономия: "+ deltaTaxAmount.abs().toString() + " (руб.)"+ "\n";

        System.out.print(taxRateInfoBegin + taxRateInfoEnd);

        return 0;
    }

    //calc tax amount by TAX_RATE_INCOME
   public static BigDecimal taxAmountIncomeSum (BigDecimal incomeSum){
        BigDecimal taxRateDecimal, hundred;
        taxRateDecimal = BigDecimal.valueOf(TAX_RATE_INCOME);
        hundred = BigDecimal.valueOf(100);
        return incomeSum.multiply(taxRateDecimal).divide(hundred, RoundingMode.HALF_UP);
   }

    //calc tax amount by TAX_RATE_BALANCE
    public static BigDecimal taxAmountBalance (BigDecimal incomeSum, BigDecimal outcomeSum){
        BigDecimal taxRateDecimal, hundred, balance;
        taxRateDecimal = BigDecimal.valueOf(TAX_RATE_BALANCE);
        hundred = BigDecimal.valueOf(100);
        balance = incomeSum.subtract(outcomeSum);
        return balance.multiply(taxRateDecimal).divide(hundred , RoundingMode.HALF_UP);
    }
}

