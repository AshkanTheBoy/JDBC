public class Employer {
    private String name;
    private float salary;
    private String job;

    public Employer(String name,String job, float salary) {
        this.name = name;
        this.job = job;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", job='" + job + '\'' +
                '}';
    }
}