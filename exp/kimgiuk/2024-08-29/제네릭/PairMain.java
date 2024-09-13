class Pair<K, V> {
    K key;
    V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

public class PairMain {
    public static void main(String[] args) {
        // String과 Integer 타입의 데이터를 관리하는 Pair 객체 생성
        Pair<String, Integer> p = new Pair<>("Hello", 10);

        // 저장된 데이터 출력
        System.out.println(p.getKey());   // "Hello"
        System.out.println(p.getValue()); // 10
    }
}
