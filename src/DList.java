import java.util.ArrayList;
import java.util.List;

public class DList<T1, T2> {
    private final List<T1> primaryList;
    private final List<List<List<T2>>> secondaryLists;

    public DList() {
        this.primaryList = new ArrayList<>();
        this.secondaryLists = new ArrayList<>();
    }

    public void add(T1 primaryValue, List<List<T2>> secondaryValues) {
        if (primaryValue == null) {
            throw new IllegalArgumentException("Primary value cannot be null");
        }
        if (secondaryValues == null) {
            throw new IllegalArgumentException("Secondary values cannot be null");
        }

        primaryList.add(primaryValue);
        List<List<T2>> copiedLists = new ArrayList<>();
        for (List<T2> list : secondaryValues) {
            copiedLists.add(new ArrayList<>(list));
        }
        secondaryLists.add(copiedLists);
    }

    public void addSecondaryLists(T1 primaryValue, List<List<T2>> additionalLists) {
        int index = primaryList.indexOf(primaryValue);
        if (index == -1) {
            throw new IllegalArgumentException("Primary value not found: " + primaryValue);
        }
        if (additionalLists == null) {
            throw new IllegalArgumentException("Additional lists cannot be null");
        }

        for (List<T2> list : additionalLists) {
            secondaryLists.get(index).add(new ArrayList<>(list));
        }
    }

    public T1 getPrimaryValue(int index) {
        if (index < 0 || index >= primaryList.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + primaryList.size());
        }
        return primaryList.get(index);
    }

    public List<List<T2>> getSecondaryLists(int index) {
        if (index < 0 || index >= secondaryLists.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + secondaryLists.size());
        }

        List<List<T2>> result = new ArrayList<>();
        for (List<T2> list : secondaryLists.get(index)) {
            result.add(new ArrayList<>(list));
        }
        return result;
    }

    public List<List<T2>> getSecondaryLists(T1 primaryValue) {
        int index = primaryList.indexOf(primaryValue);
        if (index == -1) {
            return null;
        }
        return getSecondaryLists(index);
    }

    public void remove(int index) {
        if (index < 0 || index >= primaryList.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + primaryList.size());
        }
        primaryList.remove(index);
        secondaryLists.remove(index);
    }

    public boolean remove(T1 primaryValue) {
        int index = primaryList.indexOf(primaryValue);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    public int size() {
        return primaryList.size();
    }

    public boolean isEmpty() {
        return primaryList.isEmpty();
    }

    public void clear() {
        primaryList.clear();
        secondaryLists.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DList{\n");
        for (int i = 0; i < primaryList.size(); i++) {
            sb.append("  ").append(primaryList.get(i)).append(" -> ");
            sb.append(secondaryLists.get(i)).append("\n");
        }
        return sb.append("}").toString();
    }

    public static void main(String[] args) {
        DList<Integer, Integer> dList = new DList<>();

        try {
            // 1. Добавляем элементы
            System.out.println("Добавляем элементы:");
            dList.add(1, List.of(List.of(1)));
            dList.add(2, List.of(List.of(1, 1)));
            dList.add(3, List.of(List.of(1, 2), List.of(2, 1)));
            System.out.println(dList);

            // 2. Добавляем дополнительные списки
            System.out.println("\nДобавляем списки к значению 3:");
            dList.addSecondaryLists(3, List.of(List.of(3, 3), List.of(4, 4)));
            System.out.println(dList);

            // 3. Получаем списки для значения 3
            System.out.println("\nСписки для значения 3:");
            List<List<Integer>> listsFor3 = dList.getSecondaryLists(3);
            System.out.println(listsFor3);

            // 4. Пытаемся получить несуществующий элемент (обработка ошибки)
            System.out.println("\nПопытка получить несуществующий элемент:");
            try {
                List<List<Integer>> listsFor5 = dList.getSecondaryLists(5);
                System.out.println(listsFor5);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            // 5. Удаляем элемент
            System.out.println("\nУдаляем значение 2:");
            dList.remove(Integer.valueOf(2));
            System.out.println(dList);

            // 6. Проверяем размер
            System.out.println("\nТекущий размер: " + dList.size());

        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}