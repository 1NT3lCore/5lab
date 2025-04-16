package PERSONA;

public class Person {
    private final String lastName;
    private final String firstName;
    private final int age;
    private final String phone;
    private final String email;

    public Person(String lastName, String firstName, int age, String phone, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("%s %s (Возраст: %d, Телефон: %s, Email: %s)",
                lastName, firstName, age, phone, email);
    }
}