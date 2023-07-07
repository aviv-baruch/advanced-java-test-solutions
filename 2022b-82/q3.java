//a.

public static<T extends Comparable<T>>boolean isSorted(ArrayList<T>list)throws IllegalData{if(list.isEmpty()){throw new IllegalData("List is empty");}T prev=list.get(0);for(int i=1;i<list.size();i++){if(prev.compareTo(list.get(i))>0)return false;prev=list.get(i);}return true;}

//b.
public class Student implements Comparable<Student> {
    private String name;
    private int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public int getGrade() {
        return this.grade;
    }

    @Override
    public int compareTo(Student otherStudent) {
        if (this.grade > otherStudent.getGrade())
            return 1;
        if (this.grade == otherStudent.getGrade())
            return 0;

        return -1;
    }

}

    // c.
public static void main(String[] args) {
    ArrayList<Student> students = new ArrayList<Student>();
    students.add(new Student("Aviv", 100));
    students.add(new Student("Yuval", 90));
    students.add(new Student("Tamar", 80));
    students.add(new Student("Yakir", 70));

    try {
        boolean res = isSorted(students);
        System.out.println(res ? "The list is sorted" : "List is not sorted");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

// d.
// 1. שני השווינות לא נכונים, מכיוון שאומנם GRAD יורש מSTUDENT, אבל המחלקות
// העוטפות שלהן לא קשורות זו לזו בהיבטי ירושה.