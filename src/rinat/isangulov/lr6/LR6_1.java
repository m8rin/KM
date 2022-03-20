package rinat.isangulov.lr6;

import static java.lang.Math.*;

/**
 * Метод покоординатного спуска
 *
 * @author ст.гр. БПО-18-01 Исангулов Ринат
 */
public class LR6_1 {

    public static void main(String[] args) {
        double x1, x2, x10 = 1, x20 = 1, h = 0.01, e = 1e-9, N = 100;
        x1 = x10;
        x2 = x20;

        for (int i = 1; i < N; i++) {
            do {
                x1 += h;
            } while (f(x1, x2) - f(x10, x20) < 0);

            do {
                x2 += h;
            } while (f(x1, x2) - f(x10, x20) < 0);

            if (abs(h) < e / 2) {
                System.out.printf("x1 = %f\nx2 = %f\nF = %f\nКоличество итераций: %s", x1, x2, f(x1, x2), i);
                break;
            } else {
                h = -h / 2;
            }
        }
    }

    static double f(double x1, double x2) {
        return -x1 * x2 * (3 - x1 - x2);
    }

}