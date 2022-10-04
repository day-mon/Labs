package Labs.CS_1760_AOOP.Assign3;

import java.util.Random;

public class RectangleBuffer
{
    private Rect rect;
    private boolean avail = true;
    private Random random = new Random();

    public RectangleBuffer() {
    }


    public void produce(int index) throws InterruptedException {

            synchronized (this)
            {
                while (!avail)
                {
                    wait();
                }
                var rectC = new Rect(random.nextInt(200), random.nextInt(200));
                System.out.printf("Starting to write %s to buffer on iteration %d%n", rectC, index + 1);
                rect = rectC;
                System.out.printf("Finished writing %s to buffer on iteration %d%n", rect, index + 1);
                notifyAll();
                avail = false;
                Thread.sleep(500);
            }

    }

    public void consume(int index) throws InterruptedException {

            synchronized (this)
            {
                while (avail)
                {
                    wait();
                }
                System.out.printf("Starting to read %s on iteration %d%n", rect, index + 1);
                System.out.printf("Finished reading %s on iteration %d%n", rect, index + 1);
                notifyAll();
                avail = true;
                Thread.sleep(500);
            }

    }


    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public boolean isAvail() {
        return avail;
    }

    public void setAvail(boolean avail) {
        this.avail = avail;
    }

    public static class Rect implements Comparable<Rect> {
        private double length;
        private double width;

        public Rect() {
            length = 0.0;
            width = 0.0;
        }

        public Rect(double l, double w) {
            length = l;
            width = w;
        }

        public Rect(Rect r) {
            this.length = r.length;
            this.width = r.width;
        }

        public double getLength() {
            return length;
        }

        public double getWidth() {
            return width;
        }

        public void setLength(double l) {
            length = l;
        }

        public void setWidth(double w) {
            width = w;
        }

        public double calcArea() {
            return length * width;
        }

        public boolean equals(Rect r) {
            boolean temp = false;
            if ((this.length == r.getLength()) && (this.width == r.getWidth()))
                temp = true;
            return temp;
        }

        public int compareTo(Rect r) {
            if (this.calcArea() < r.calcArea())
                return -1;
            else if (this.calcArea() > r.calcArea())
                return 1;
            else
                return 0;
        }

        public String toString() {
            return "[Length = " + length + " Width = " + width + "]";
        }
    }
}
