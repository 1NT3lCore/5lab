
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Tournament {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int teamCount = getTeamCount(scanner);
        List<String> teams = generateTeams(teamCount);
        System.out.println("Участники турнира: " + teams);

        String winner = runTournament(teams, scanner);
        System.out.println("\nПобедитель турнира: " + winner);
        scanner.close();
    }


    private static int getTeamCount(Scanner scanner) {
        System.out.print("Введите количество команд (должно быть степенью двойки, например 2, 4, 8, 16): ");
        try {
            int count = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            // Проверяем, является ли число степенью двойки
            if (count > 0 && (count & (count - 1)) == 0) {
                return count;
            } else {
                System.out.println("Некорректное значение. Используем значение по умолчанию: 8");
                return 8;
            }
        } catch (Exception e) {
            System.out.println("Ошибка ввода. Используем значение по умолчанию: 8");
            return 8;
        }
    }

    private static List<String> generateTeams(int count) {
        List<String> teams = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            teams.add("Команда" + i);
        }
        return teams;
    }

    private static String runTournament(List<String> teams, Scanner scanner) {
        while (teams.size() > 1) {
            System.out.println("\n=== Раунд с " + teams.size() + " командами ===");

            // Перемешиваем команды
            Collections.shuffle(teams);
            System.out.println("После жеребьевки: " + teams);

            // Создаем пары команд
            GenericPairBag<String, String> pairs = new GenericPairBag<>(teams.size() / 2);
            for (int i = 0; i < teams.size(); i += 2) {
                String team1 = teams.get(i);
                String team2 = teams.get(i + 1);
                pairs.addPair(new Pair<>(team1, team2));
            }

            // Разыгрываем матчи в этом раунде
            List<String> winners = new ArrayList<>();
            while (!pairs.isEmpty()) {
                Pair<String, String> match = pairs.removeRandomPair();
                System.out.println("\nМатч: " + match.getFirst() + " vs " + match.getSecond());

                String winner = getMatchWinner(match, scanner);
                winners.add(winner);
                System.out.println("Победитель: " + winner);
            }

            teams = winners;
        }

        return teams.get(0);
    }

    private static String getMatchWinner(Pair<String, String> match, Scanner scanner) {
        while (true) {
            System.out.print("Кто победил? (1 - " + match.getFirst() + ", 2 - " + match.getSecond() + "): ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // очистка буфера

                if (choice == 1) {
                    return match.getFirst();
                } else if (choice == 2) {
                    return match.getSecond();
                } else {
                    System.out.println("Введите 1 или 2!");
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода! Введите число.");
                scanner.nextLine(); // очистка буфера
            }
        }
    }
}

