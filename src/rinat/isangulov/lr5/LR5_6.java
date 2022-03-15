package rinat.isangulov.lr5;

import static java.lang.Math.*;

/**
 * Метод итераций
 *
 * @author ст.гр. БПО-18-01 Исангулов Ринат
 */
public class LR5_6 {

    public static void main(String[] args) {
        double e = 0.000001, x = 0, lambda = 0.001, xk;

        do {
            xk = x;
            x = x - lambda * f1(x);
        } while (abs(x - xk) > e);
        System.out.println("Результат:\nx = " + x);
        System.out.println("f(x) = " + f(x));
        System.out.println("f1(x) = " + f1(x));
    }

    static double f(double x) {
        return pow(sqrt(abs(cos(x))) - x, 2);
    }

    static double f1(double x) {
        return (sqrt(cos(x)) - x) * (-sin(x) / sqrt(cos(x)) - 2);
    }
}