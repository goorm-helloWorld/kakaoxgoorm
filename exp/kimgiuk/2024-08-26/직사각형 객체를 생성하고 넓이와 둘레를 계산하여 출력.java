public class Main {
    public static void main(String[] args) {
        Rectangle r = new Rectangle(10, 5);
        r.show();
    }
}

class Rectangle {
    int width, height;
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void show() {
        System.out.println("넓이 : " + width*height);
        System.out.println("둘레 : " + 2*(width+height));
    }
}