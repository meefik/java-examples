package javagc;

public class TestGC {

    public static void main(String[] args) {
        // java -verbose:gc -jar TestGC.jar
        for(long i=0;i<20000000;i++) {
            new Test(Math.random());
            if (i % 10000000 == 0) System.gc();
        }
    }
}
