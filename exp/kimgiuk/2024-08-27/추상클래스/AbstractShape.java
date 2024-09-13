abstract class AbstractShape implements Shape {
    String name;

    public AbstractShape(String name) {
        this.name = name;
    }

    @Override
    public abstract double calculateArea(); // 구체적인 면적 계산은 각 도형 클래스에서 정의
}
