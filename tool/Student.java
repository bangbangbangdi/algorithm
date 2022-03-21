package tool;

public class Student {
    private int age;
    private int id;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(int age, int id, String name) {
        this.age = age;
        this.id = id;
        this.name = name;
    }

    public static void printStudent(Student stu){
        System.out.println(stu.name + "--" + stu.age + "--" + stu.id);
    }

    public static void printAllStudent(Student[] students) {
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].name + "--" + students[i].age + "--" + students[i].id);
        }
    }
}
