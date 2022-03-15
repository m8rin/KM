package rinat.isangulov.lr5;

import static java.lang.Math.*;

/**
 * Метод бисекции
 *
 * @author ст.гр. БПО-18-01 Исангулов Ринат
 */
public class LR5_4 {

    public static void main(String[] args) {
        double a = 0, b = 1, e = 0.000001, x;
        System.out.println("x\t\t\t\t\tf(x)\t\t\t\tf1(x)");
        x = (a + b) / 2;
        do {
            if (f1(x) < 0) {
                a = x;
            } else {
                b = x;
            }
            x = (a + b) / 2;
            System.out.printf("%.9f\t\t\t%.9f\t\t\t%.9f\n", x, f(x), f1(x));
        } while ((b - a) / 2 > e);

        System.out.println("Результат:\nx = " + x);
        System.out.println("Минимум функции = " + f(x));
    }

    static double f(double x) {
        return pow(sqrt(abs(cos(x))) - x, 2);
    }

    static double f1(double x) {
        return (sqrt(cos(x)) - x) * (-sin(x) / sqrt(cos(x)) - 2);
    }
}