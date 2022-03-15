package rinat.isangulov.lr5;

import static java.lang.Math.*;

/**
 * Метод Ньютона
 *
 * @author ст.гр. БПО-18-01 Исангулов Ринат
 */
public class LR5_5 {

    public static void main(String[] args) {
        double x = 0.1, e = 0.000001, xk;
        do {
            xk = x;
            x = x - f1(x) / f2(x);
        }
        while (abs(x - xk) > e);
        System.out.println("Результат:\nx = " + x);
        System.out.println("Минимум функции = " + f(x));
    }

    static double f(double x) {
        return pow(sqrt(abs(cos(x))) - x, 2);
    }

    static double f1(double x) {
        return (sqrt(cos(x)) - x) * (-sin(x) / sqrt(cos(x)) - 2);
    }

    static double f2(double x) {
        return (pow(2 + sin(x) / sqrt(cos(x)), 2) + (x - sqrt(cos(x))) * (2 * sqrt(cos(x)) + pow(sin(x), 2) / pow(cos(x), 3 / 2.))) / 2;
    }
}