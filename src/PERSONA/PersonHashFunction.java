package PERSONA;

public class PersonHashFunction extends HashFunction<String> {
    public PersonHashFunction(int tableSize) {
        super(tableSize);
    }

    @Override
    public int hash(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("Фамилия не может быть null");
        }

        int hash = 0;
        for (int i = 0; i < lastName.length(); i++) {
            hash = (31 * hash + lastName.charAt(i)) % tableSize;
        }
        return Math.abs(hash);
    }
}