import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinancialCalculator {
    private static List<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Wybierz opcję:");
            System.out.println("1. Kalkulator odsetek prostych");
            System.out.println("2. Kalkulator odsetek składanych");
            System.out.println("3. Kalkulator rat kredytu");
            System.out.println("4. Pokaż historię obliczeń");
            System.out.println("5. Zapisz historię do pliku");
            System.out.println("6. Wyjdź");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    calculateSimpleInterest(scanner);
                    break;
                case 2:
                    calculateCompoundInterest(scanner);
                    break;
                case 3:
                    calculateLoanPayment(scanner);
                    break;
                case 4:
                    showHistory();
                    break;
                case 5:
                    saveHistoryToFile();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Niepoprawny wybór.");
            }
        }

        scanner.close();
    }

    private static void calculateSimpleInterest(Scanner scanner) {
        System.out.println("Podaj kwotę początkową:");
        double principal = scanner.nextDouble();
        System.out.println("Podaj roczną stopę procentową (np. 5 oznacza 5%):");
        double rate = scanner.nextDouble();
        System.out.println("Podaj okres inwestycji (w latach):");
        int years = scanner.nextInt();

        double interest = (principal * rate * years) / 100;
        double totalAmount = principal + interest;

        String result = "Odsetki proste: " + interest + " PLN, Całkowita wartość: " + totalAmount + " PLN";
        System.out.println(result);

        history.add(result);
    }

    private static void calculateCompoundInterest(Scanner scanner) {
        System.out.println("Podaj kwotę początkową:");
        double principal = scanner.nextDouble();
        System.out.println("Podaj roczną stopę procentową (np. 5 oznacza 5%):");
        double rate = scanner.nextDouble();
        System.out.println("Podaj liczbę kapitalizacji w roku:");
        int compoundingPeriods = scanner.nextInt();
        System.out.println("Podaj okres inwestycji (w latach):");
        int years = scanner.nextInt();

        double compoundFactor = Math.pow((1 + (rate / compoundingPeriods) / 100), compoundingPeriods * years);
        double futureValue = principal * compoundFactor;

        String result = "Odsetki składane: Całkowita wartość: " + futureValue + " PLN";
        System.out.println(result);

        history.add(result);
    }

    private static void calculateLoanPayment(Scanner scanner) {
        System.out.println("Podaj kwotę kredytu:");
        double loanAmount = scanner.nextDouble();
        System.out.println("Podaj roczną stopę procentową (np. 5 oznacza 5%):");
        double rate = scanner.nextDouble();
        System.out.println("Podaj liczbę rat:");
        int months = scanner.nextInt();

        double monthlyRate = rate / 12 / 100;
        double monthlyPayment = loanAmount * monthlyRate / (1 - Math.pow(1 + monthlyRate, -months));

        String result = "Miesięczna rata: " + monthlyPayment + " PLN";
        System.out.println(result);

        history.add(result);
    }

    private static void showHistory() {
        if (history.isEmpty()) {
            System.out.println("Brak obliczeń w historii.");
        } else {
            for (String entry : history) {
                System.out.println(entry);
            }
        }
    }

    private static void saveHistoryToFile() {
        try (FileWriter writer = new FileWriter("historia_obliczen.txt")) {
            for (String entry : history) {
                writer.write(entry + "\n");
            }
            System.out.println("Historia zapisana do pliku.");
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania pliku.");
        }
    }
}
