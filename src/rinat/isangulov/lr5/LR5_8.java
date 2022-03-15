package rinat.isangulov.lr5;

import static java.lang.Math.*;

/**
 * Метод Фибоначчи
 *
 * @author ст.гр. БПО-18-01 Исангулов Ринат
 */
public class LR5_8 {

    public static void main(String[] args) {
        double a = 0, b = 1, delta = 0.000001, x1, x2, min = 0;
        int n, n0 = 0;
        double[] f = new double[100];
        f[0] = 1;
        f[1] = 1;
        for (int k = 2; k < 100; k++) {
            f[k] = f[k - 2] + f[k - 1];
            if (f[k] > (b - a) / 2 / delta) {
                n = k;
                n0 = n;
                x1 = a + f[n - 2] / f[n] * (b - a);
                x2 = a + f[n - 1] / f[n] * (b - a);
                do {
                    if (f(x1) <= f(x2)) {
                        b = x2;
                        x2 = x1;
                        x1 = a + f[n - 2] / f[n] * (b - a);
                    } else {
                        a = x1;
                        x1 = x2;
                        x2 = a + f[n - 1] / f[n] * (b - a);
                    }
                    n--;
                } while (n >= 2);
                if (f(x1) <= f(x2)) {
                    min = x1;
                } else {
                    min = x2;
                }
            }
        }
        System.out.println("Результат:\nx = " + min);
        System.out.println("f(x) = " + f(min));
        System.out.println("n0 = " + n0);
    }

    static double f(double x) {
        return pow(sqrt(abs(cos(x))) - x, 2);
    }

}