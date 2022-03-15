package rinat.isangulov.lr5;

import static java.lang.Math.*;

/**
 * Метод квадратичной интерполяции
 *
 * @author ст.гр. БПО-18-01 Исангулов Ринат
 */
public class LR5_7 {

    public static void main(String[] args) {
        double e = 0.000001, x = 1, h = 0.001, xk;
        int k = 0;

        do {
            xk = x;
            double f1 = f(x - h);
            double f0 = f(x);
            double f2 = f(x + h);
            x = x - h / 2 * (f2 - f1) / (f2 - 2 * f0 + f1);
            k++;
        } while (abs(x - xk) > e);

        System.out.println("Результат:\nx = " + x);
        System.out.println("f(x) = " + f(x));
        System.out.println("Количество итераций = " + k);
    }

    static double f(double x) {
        return pow(sqrt(abs(cos(x))) - x, 2);
    }

}