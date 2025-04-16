package PERSONA;

import java.util.List;

public class PersonHashTableDemo {
    public static void main(String[] args) {
        // Создаем хеш-функцию для фамилий с размером таблицы 20
        PersonHashFunction hashFunction = new PersonHashFunction(20);

        // Создаем хеш-таблицу для Person с ключом по фамилии
        HashTable<String, Person> personTable = new HashTable<>(
                hashFunction,
                Person::getLastName
        );

        // Добавляем людей в таблицу
        personTable.insert(new Person("Иванов", "Иван", 30, "123-456", "ivanov@mail.com"));
        personTable.insert(new Person("Петров", "Петр", 25, "234-567", "petrov@mail.com"));
        personTable.insert(new Person("Сидоров", "Сергей", 35, "345-678", "sidorov@mail.com"));
        personTable.insert(new Person("Иванов", "Алексей", 28, "456-789", "ivanov2@mail.com"));
        personTable.insert(new Person("Кузнецов", "Дмитрий", 40, "567-890", "kuznetsov@mail.com"));

        // Выводим распределение элементов
        personTable.printDistribution();

        // Поиск по фамилии
        System.out.println("\nПоиск по фамилии 'Иванов':");
        List<Person> ivanovs = personTable.search("Иванов");
        for (Person person : ivanovs) {
            System.out.println(person);
        }

        // Удаление
        boolean removed = personTable.remove("Сидоров");
        System.out.println("\nУдаление 'Сидоров': " + removed);

        // Поиск после удаления
        System.out.println("\nПоиск по фамилии 'Сидоров' после удаления:");
        List<Person> sidorovs = personTable.search("Сидоров");
        System.out.println("Найдено: " + sidorovs.size() + " записей");
    }
}