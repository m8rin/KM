package rinat.isangulov.lr5;

import static java.lang.Math.*;

/**
 * Оптимальный пассивный поиск
 *
 * @author ст.гр. БПО-18-01 Исангулов Ринат
 */
public class LR5_1 {

    public static void main(String[] args) {
        double a = 0, b = 1, h = 0.1, x = a, y = 1000;

        for (int j = 1; j <= 6; j++) {
            System.out.printf("Cерия %s:\n", j);
            for (double i = a; i < b; i = i + h) {
                System.out.printf("f(%s) = %f\n", i, f(i));
                if (f(i) < y) {
                    y = f(i);
                    x = i;
                }
            }
            if (f(x - h) < f(x + h)) {
                b = x;
                a = x - h;
            } else {
                a = x;
                b = x + h;
            }
            h = h / 10;
        }
        System.out.println("Результат:\nx = " + x);
        System.out.println("Минимум функции = " + f(x));
    }

    static double f(double x) {
        return abs(sqrt(abs(cos(x))) - x);
    }

}