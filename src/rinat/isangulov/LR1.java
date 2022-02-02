package rinat.isangulov;

/**
 * @author ст.гр. БПО-18-01 Исангулов Ринат
 */

/*
Вариант 8
Коэффициенты системы A=(ai,j)n,n      | Свободные члены B=(bi)n
 4.2 -1.9  3.3                        | 2.9
 2.8  4.1  5.7                        | 0.3
 5.8 -1.5 -3.0                        | -5.8
 */

public class LR1 {

    public static void main(String[] args) {
        double[][] a = new double[3][3];
        double[] b = new double[3];

        a[0][0] = 4.2;
        a[0][1] = -1.9;
        a[0][2] = 3.3;

        a[1][0] = 2.8;
        a[1][1] = 4.1;
        a[1][2] = 5.7;

        a[2][0] = 5.8;
        a[2][1] = -1.5;
        a[2][2] = -3.0;

        b[0] = 2.9;
        b[1] = 0.3;
        b[2] = -5.8;

        methodKramer(a, b);
    }

    static double getDelta(double[][] a) {
        int ignoreRow = 0;
        double total = 0, partDelt;
        double[] deltaElements;

        while (ignoreRow < 3) {
            deltaElements = getDeltaElements(a,ignoreRow);
            partDelt = a[0][ignoreRow] * (deltaElements[0] * deltaElements[3] - deltaElements[1] * deltaElements[2]);
            total = (ignoreRow != 1) ? total + partDelt : total - partDelt;
            ignoreRow++;
        }
        return total;
    }

    private static double[] getDeltaElements(double [][] a, int ignoreRow) {
        int count = 0;
        double[] elements = new double[4];
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j != ignoreRow) {
                    elements[count] = a[i][j];
                    count++;
                }
            }
        }
        return elements;
    }

    static double[][] replaceWithVector(double[][] a, double[] v, int replaceColumn) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == replaceColumn) {
                    a[i][j] = v[i];
                }
            }
        }
        return a;
    }

    private static double getX(double Dn, double D) {
        return Dn / D;
    }

    private static double checkY(double[][] a, double[] x, double[] b, int i) {
        double y = 0;
        for (int j = 0; j < 3; j++) {
            y = a[i][j] * x[i];
        }
        y -= b[i];
        return y;
    }

    static void methodKramer(double[][] a, double[] b) {
        System.out.println("1. Метод Крамера");

        double D = getDelta(a);

        double D1 = getDelta(replaceWithVector(a, b, 0));
        double x1 = getX(D1, D);

        double D2 = getDelta(replaceWithVector(a, b, 1));
        double x2 = getX(D2, D);

        double D3 = getDelta(replaceWithVector(a, b, 2));
        double x3 = getX(D3, D);

        double[] x = new double[3];
        x[0] = x1;
        x[1] = x2;
        x[2] = x3;

        double y1 = checkY(a, x, b, 0);
        double y2 = checkY(a, x, b, 1);
        double y3 = checkY(a, x, b, 2);

        System.out.println("D = " + D);
        System.out.println("D1 = " + D1);
        System.out.println("D2 = " + D2);
        System.out.println("D3 = " + D3);

        System.out.println("x1 = " + x1);
        System.out.println("x2 = " + x2);
        System.out.println("x3 = " + x3);

        System.out.println("y1 = " + y1);
        System.out.println("y2 = " + y2);
        System.out.println("y3 = " + y3);
    }

    // 2.Метод Гаусса
    // 3.Матричный метод
    // 4.Метод Якоби
    // 5.Метод Зейделя
    // 6.Метод релаксации
}