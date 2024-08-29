package kakaoGoorm.exp.exp0829;

public class Pair<T, M> {
    private T a;
    private M b;

    public Pair(T a, M b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
