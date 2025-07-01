import client.Account.Account;
import client.Account.Tax.TaxCalc;

import java.math.BigDecimal;
import java.util.Scanner;

public class TaxHelper {

   final public static String  title = "TAX HELPER : КАЛЬКУЛЯТОР НАЛОГОВ для ИП (ПО УСН)";
   final public static String  info = """
        Выберите операцию и введите её номер:
        1 - Добавить новый доход
        2 - Добавить новый расход
        3 - Выбрать систему налогообложения
        4 - Вывести информацию о счёте
        Наберите end для выхода из программы
    """;

    public static void main(String[] args) {

        System.out.println("-".repeat(60)+"\n"+title+"\n"+"-".repeat(60));
        System.out.println(info+"-".repeat(60));

        Scanner scanner = new Scanner(System.in);

        BigDecimal incomeSum = new BigDecimal("0.00");
        BigDecimal outcomeSum = new BigDecimal("0.00");
        int        taxSystemId = 0;

        boolean isRunning = true;

        while(isRunning) {

            System.out.print("Выберите операцию:");
            String action = scanner.next();
            action = action.toLowerCase().trim();

            switch (action){
                case "1" : System.out.print("Введите сумму дохода:");
                    BigDecimal incomeCur = new BigDecimal(scanner.next());
                    incomeSum = Account.calcIncomeSum(incomeCur, incomeSum);
                    break;
                case "2" : System.out.print("Введите сумму расхода:");
                    BigDecimal outcomeCur = new BigDecimal(scanner.next());
                    outcomeSum = Account.calcOutcomeSum(outcomeCur, incomeSum, outcomeSum);
                    break;
                case "3" : System.out.println("Выберите систему налогообложения:");
                    taxSystemId = TaxCalc.taxRateSelect(incomeSum, outcomeSum);
                    break;
                case "4" : System.out.println(Account.getAccountInfo(incomeSum, outcomeSum));
                    break;
                case ("end"): isRunning = false;
                    break;
                default : System.out.println("Некорректный тип операции!");
            }

        }

        scanner.close();
        System.out.println("Работа завершена! Хорошего дня!");

    }




}
