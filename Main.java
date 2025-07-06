import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {
    final static int TAX_6 = 6;
    final static int TAX_15 = 15;
    final static String TAX_6_DESCR = "УСН доходы";
    final static String TAX_15_DESCR = "УСН Доходы-минус-Расходы";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int incomeSum = 0; //доходы
        int outcomeSum = 0; //расходы


        boolean isRunning = true;
        while (isRunning) {
            printActions();

            String action = sc.nextLine();
            switch (action) {
                case ("end"):
                    isRunning = false;
                    break;
                case "1":
                    System.out.print("Введите сумму дохода:");
                    incomeSum += Integer.parseInt(sc.nextLine());
                    break;
                case "2":
                    System.out.print("Введите сумму расхода:");
                    outcomeSum += Integer.parseInt(sc.nextLine());
                    ;
                    break;
                case "3":
                    System.out.println("Выберите систему налогообложения:");
                    selectTax(incomeSum, outcomeSum);
                    break;
                default:
                    System.out.println("Неизвестная команда!");
            }
        }
        System.out.println("\nПрограмма завершила работу!");
    }

    public static void selectTax(int incomeSum, int outcomeSum) {
        int taxAmoun6 = calcTax6(incomeSum);
        int taxAmoun15 = calcTax15(incomeSum, outcomeSum);

        if (taxAmoun6 > taxAmoun15) {
            System.out.println(getCalcTaxInfo(TAX_15_DESCR, taxAmoun15, taxAmoun6));
        } else if (taxAmoun6 < taxAmoun15) {
            System.out.println(getCalcTaxInfo(TAX_6_DESCR, taxAmoun6, taxAmoun15));
        } else {
            if (taxAmoun6 != 0) {
                System.out.printf("Можете выбрать любую систему УСН\n" +
                        "Ваш налог: %d\n", taxAmoun6);
            } else System.out.println("В данном периоде невозможно посчитать налоги!");
        }

    }

    public static int calcTax6(int incomeSum) {
        return incomeSum * TAX_6 / 100;
    }

    public static int calcTax15(int incomeSum, int outcomeSum) {
        int taxAmount = (incomeSum - outcomeSum) * TAX_15 / 100;
        return Math.max(taxAmount, 0);
    }

    public static String getCalcTaxInfo(String taxDescr, int TaxAmountOne, int TaxAmountTwo) {
        return String.format("""
                        Мы рекомендуем %s
                        Ваш налог: %d (руб.)
                        Налог на другой системе: %d (руб.)
                        Экономия: %d (руб.)
                        """,
                taxDescr, TaxAmountOne, TaxAmountTwo, abs(TaxAmountOne - TaxAmountTwo));
    }

    public static void printActions() {

        System.out.println("Выберите операцию и введите её номер:\n" +
                "                    1 - Добавить новый доход\n" +
                "                    2 - Добавить новый расход\n" +
                "                    3 - Выбрать систему налогообложения\n" +
                "                    Наберите end для выхода из программы\n");
    }
}
