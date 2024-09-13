public class Test {
    public static void main(String[] args) {
        // 도형 객체 배열 생성
        Shape[] shapes = new Shape[4];
        shapes[0] = new Shape();
        shapes[1] = new Rectangle(5, 5);
        shapes[2] = new Triangle(5, 5);
        shapes[3] = new Circle(5);

        // 각 도형의 면적 출력
        for (Shape s : shapes) {
            s.draw();
        }
    }
}

// 기본 도형 클래스 정의
class Shape {
    public void draw() {
        System.out.println("부모 클래스 Shape 의 show() 메소드");
    }
}

// 사각형 클래스 정의
class Rectangle extends Shape {
    int width, height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Rectangle의 면적 : " + width * height);
    }
}

// 삼각형 클래스 정의
class Triangle extends Shape {
    int width, height;

    public Triangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Triangle의 면적 : " + (width * height / 2));
    }
}

// 원 클래스 정의
class Circle extends Shape {
    int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Circle의 면적 : " + radius * radius * 3.14);
    }
}
