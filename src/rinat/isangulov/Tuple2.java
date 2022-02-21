package rinat.isangulov;

public final class Tuple2<A, B> {
    private final A v1;
    private final B v2;
    public Tuple2(A v1, B v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public A get1() {
        return v1;
    }

    public B get2() {
        return v2;
    }
}
