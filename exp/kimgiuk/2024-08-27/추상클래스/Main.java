public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[3]; // 도형 객체 배열 생성

        shapes[0] = new Circle(5); // 반지름이 5인 원
        shapes[1] = new Rectangle(4, 6); // 가로 4, 세로 6인 사각형
        shapes[2] = new Triangle(3, 4); // 밑변 3, 높이 4인 삼각형

        // 배열에 저장된 각 도형의 면적 출력
        for (Shape shape : shapes) {
            System.out.println(shape.getClass().getSimpleName() + "의 면적: " + shape.calculateArea());
        }
    }
}

//Circle의 면적: 78.53981633974483
//Rectangle의 면적: 24.0
//Triangle의 면적: 6.0
