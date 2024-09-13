public class Test {
    public static void main(String[] args) {
        // 직원 객체 생성
        Employee e = new Employee("직원", 1000, "대리");
        System.out.println("직원의 메소드 : " + e.risingSalary());

        // 관리자 객체 생성
        Administrator a = new Administrator("관리자", 1000, "대리", 1);
        System.out.println("관리자의 메소드 : " + a.risingSalary());
    }
}

// 직원 클래스 정의
class Employee {
    String name;
    int salary;
    String rank;

    public Employee(String name, int salary, String rank) {
        this.name = name;
        this.salary = salary;
        this.rank = rank;
    }

    // 급여 인상 메서드
    public int risingSalary() {
        return this.salary * 2;  // 기본적으로 급여를 2배로 인상
    }
}

// 관리자 클래스 정의 (직원 클래스를 상속)
class Administrator extends Employee {
    int number;

    public Administrator(String name, int salary, String rank, int number) {
        super(name, salary, rank);
        this.number = number;
    }

    // 급여 인상 메서드 오버라이딩
    @Override
    public int risingSalary() {
        return this.salary * 3;  // 관리자는 급여를 3배로 인상
    }
}
