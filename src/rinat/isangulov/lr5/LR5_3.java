package rinat.isangulov.lr5;

import static java.lang.Math.*;

/**
 * Метод золотого сечения
 *
 * @author ст.гр. БПО-18-01 Исангулов Ринат
 */
public class LR5_3 {

    public static void main(String[] args) {
        double a = 0, b = 1, e = 0.000001, x, alpha, beta;

        for (int k = 0; k < 1000; k++) {
            alpha = a + 2 * (b - a) / (3 + sqrt(5));
            beta = a + 2 * (b - a) / (1 + sqrt(5));
            if (f(alpha) <= f(beta)) {
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