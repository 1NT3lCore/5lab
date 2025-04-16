import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChange{

    static class DList<T1, T2> {
        private final List<T1> amounts;
        private final List<List<List<T2>>> combinations;

        public DList() {
            this.amounts = new ArrayList<>();
            this.combinations = new ArrayList<>();
        }

        public void add(T1 amount, List<List<T2>> combos) {
            amounts.add(amount);
            List<List<T2>> copiedCombos = new ArrayList<>();
            for (List<T2> combo : combos) {
                copiedCombos.add(new ArrayList<>(combo));
            }
            combinations.add(copiedCombos);
        }

        public List<List<T2>> getCombinations(int index) {
            if (index < 0 || index >= combinations.size()) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + combinations.size());
            }
            List<List<T2>> result = new ArrayList<>();
            for (List<T2> combo : combinations.get(index)) {
                result.add(new ArrayList<>(combo));
            }
            return result;
        }

        public void setCombinations(int index, List<List<T2>> newCombos) {
            if (index < 0 || index >= combinations.size()) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + combinations.size());
            }
            combinations.get(index).clear();
            for (List<T2> combo : newCombos) {
                combinations.get(index).add(new ArrayList<>(combo));
            }
        }

        public int size() {
            return amounts.size();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("DList:\n");
            for (int i = 0; i < amounts.size(); i++) {
                sb.append("Сумма: ").append(amounts.get(i)).append(" -> ");
                sb.append("Комбинации: ").append(combinations.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    public static DList<Integer, Integer> findMinCoinCombinations(int[] coins, int targetAmount) {
        Arrays.sort(coins);
        DList<Integer, Integer> dp = new DList<>();

        // Базовый случай: сумма 0
        dp.add(0, List.of(new ArrayList<>()));

        for (int currentAmount = 1; currentAmount <= targetAmount; currentAmount++) {
            List<List<Integer>> currentCombinations = new ArrayList<>();
            int minCoins = Integer.MAX_VALUE;

            for (int coin : coins) {
                if (coin > currentAmount) break;

                int remaining = currentAmount - coin;
                if (remaining >= 0 && remaining < dp.size()) {
                    List<List<Integer>> prevCombinations = dp.getCombinations(remaining);

                    for (List<Integer> combo : prevCombinations) {
                        List<Integer> newCombo = new ArrayList<>(combo);
                        newCombo.add(coin);

                        if (newCombo.size() < minCoins) {
                            currentCombinations.clear();
                            currentCombinations.add(newCombo);
                            minCoins = newCombo.size();
                        } else if (newCombo.size() == minCoins) {
                            currentCombinations.add(newCombo);
                        }
                    }
                }
            }

            dp.add(currentAmount, currentCombinations);
        }

        return dp;
    }

    public static void main(String[] args) {
        // Тест 1: Монеты 1, 2, 5, 10 для суммы 8
        System.out.println("=== Тест 1: Монеты [1, 2, 5, 10], сумма 8 ===");
        int[] coins1 = {1, 2, 5, 10};
        int amount1 = 8;
        DList<Integer, Integer> result1 = findMinCoinCombinations(coins1, amount1);
        System.out.println(result1);
        System.out.println("Оптимальные комбинации для суммы " + amount1 + ":");
        result1.getCombinations(amount1).forEach(System.out::println);

        // Тест 2: Монеты 1, 4, 7, 9 для суммы 12
        System.out.println("\n=== Тест 2: Монеты [1, 4, 7, 9], сумма 12 ===");
        int[] coins2 = {1, 4, 7, 9};
        int amount2 = 12;
        DList<Integer, Integer> result2 = findMinCoinCombinations(coins2, amount2);
        System.out.println(result2);
        System.out.println("Оптимальные комбинации для суммы " + amount2 + ":");
        result2.getCombinations(amount2).forEach(System.out::println);

        // Тест 3: Монеты 1, 2, 3 для суммы 5 (пример из задания 7)
        System.out.println("\n=== Тест 3: Монеты [1, 2, 3], сумма 5 ===");
        int[] coins3 = {1, 2, 3};
        int amount3 = 5;
        DList<Integer, Integer> result3 = findMinCoinCombinations(coins3, amount3);
        System.out.println(result3);
        System.out.println("Оптимальные комбинации для суммы " + amount3 + ":");
        result3.getCombinations(amount3).forEach(System.out::println);
    }
}