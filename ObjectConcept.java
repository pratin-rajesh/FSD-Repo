class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        User user = (User) obj;
        return age == user.age && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + age;
    }
}

class SecondUser {
    private String name;
    private int age;

    public SecondUser(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class ObjectConcept {
    public static void main(String[] args) {
        User user1 = new User("Alice", 25);
        User user2 = new User("Alice", 25);
        SecondUser secondUser = new SecondUser("Alice", 25);

        System.out.println(user1.toString());
        System.out.println("User1 equals User2: " + user1.equals(user2));

        System.out.println(secondUser.toString()); // Default Object toString()
    }
}