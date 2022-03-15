package rinat.isangulov.lr5;

import static java.lang.Math.*;
import static java.lang.Math.abs;

/**
 * Метод половинного деления
 *
 * @author ст.гр. БПО-18-01 Исангулов Ринат
 */
public class LR5_2 {

    public static void main(String[] args) {
        double a = 0, b = 1, e = 0.000001, N = 1000;
        double delta = e / 3, alpha, beta, x;

        for (int k = 1; k < N; k++) {
            alpha = (a + b) / 2 - delta;
            beta = (a + b) / 2 + delta;
            if (f(alpha) < f(beta)) {
                b = beta;
                x = alpha;
            } else {
                a = alpha;
                x = beta;
            }

            if (b - a < e) {
                System.out.println("Результат:\nx = " + x);
                System.out.println("Количество итераций = " + k);
                System.out.println("Минимум функции = " + f(x));
                break;
            }
        }
    }

    static double f(double x) {
        return abs(sqrt(abs(cos(x))) - x);
    }

}