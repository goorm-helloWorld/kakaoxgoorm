public class ShapeTest {
    public static void main(String[] args) {
        // 도형 객체 생성
        Shape rectangle = new Rectangle(5, 10);
        Shape triangle = new Triangle(5, 10);
        Shape circle = new Circle(5);

        // 면적 계산 및 출력
        System.out.println("사각형의 면적: " + rectangle.calculateArea());
        System.out.println("삼각형의 면적: " + triangle.calculateArea());
        System.out.println("원의 면적: " + circle.calculateArea());
    }
}

//사각형의 면적: 50.0
//삼각형의 면적: 25.0
//원의 면적: 78.53981633974483
