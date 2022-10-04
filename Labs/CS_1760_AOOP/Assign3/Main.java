import Labs.CS_1760_AOOP.Assign3.RectangleBuffer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        var buffer = new RectangleBuffer();


        var t1 = new Thread(() -> {
            for (var i = 0; i < 10; i++)
            {
                try {
                    buffer.produce(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Producer");

        var t2 = new Thread(() -> {
            for (var i = 0; i < 10; i++)
            {
                try {
                    buffer.consume(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Consumer");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}