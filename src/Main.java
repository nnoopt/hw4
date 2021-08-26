public class Main {

    volatile int a = 1;

    public static void main(String[] args) {

        Main aa = new Main();

        new Thread(() -> {
                aa.printA();
        }).start();

        new Thread(() -> {
                aa.printB();
        }).start();
        new Thread(() -> {
                aa.printC();
        }).start();
    }

    public synchronized void printA() {
        for (int i = 0; i < 5; i++) {
            try {
                while (a != 1) {
                    this.wait();
                }
                System.out.print("A");
                a = 2;
                this.notifyAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void printB() {
        for (int i = 0; i < 5; i++) {

            try {
            while (a != 2) {
                this.wait();
            }
                System.out.print("B");
                a = 3;
                this.notifyAll();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void printC() {
        for (int i = 0; i < 5; i++) {

            try {
            while (a != 3) {
                this.wait();
            }
                System.out.print("C");
                a = 1;
                this.notifyAll();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
}
