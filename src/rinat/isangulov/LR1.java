package rinat.isangulov;

import java.io.*;
import java.util.*;

/**
 * @author ст.гр. БПО-18-01 Исангулов Ринат
 */
public class LR1 {

    static final int N = 3;
    static final double E = 0.00001;
    static final String PATH = "C:\\Users\\risangulov\\IdeaProjects\\KM\\src\\rinat\\isangulov\\lr1.txt";
    static final String AUTHOR = "Выполнил ст.гр. БПО-18-01 Исангулов Ринат";

    public static void main(String[] args) {
        double[][] a = new double[N][N];
        double[] b = new double[N];

        readAndPrint(a, b);
        methodKramer(a, b);

        readAndPrint(a, b);
        methodGauss(a, b);

        readAndPrint(a, b);
        methodMatrix(a, b);

        readAndPrint(a, b);
        methodJacobi(a, b);

        readAndPrint(a, b);
        methodZeid(a, b);
    }

    static void readAndPrint(double[][] a, double[] b) {
        String underLine = "_____________________________";
        System.out.println("\n" + AUTHOR);
        System.out.println(underLine + "\nИсходные данные\n" + underLine);
        try {
            Scanner sc = new Scanner(new File(PATH));
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N + 1; j++) {
                    if (j == N) {
                        b[i] = sc.nextDouble();
                        System.out.printf("| %.1f \t", b[i]);
                    } else {
                        a[i][j] = sc.nextDouble();
                        System.out.printf("%.1f \t", a[i][j]);
                    }
                }
                System.out.println();
            }
            sc.close();
            System.out.println(underLine);
        } catch (FileNotFoundException | InputMismatchException e) {
            e.printStackTrace();
        }
    }

    static double getDelta(double[][] a) {
        int ignoreRow = 0;
        double total = 0, partDelt;
        double[] deltaElements;

        while (ignoreRow < 3) {
            deltaElements = getDeltaElements(a, ignoreRow, 0);
            partDelt = a[0][ignoreRow] * (deltaElements[0] * deltaElements[3] - deltaElements[1] * deltaElements[2]);
            total = (ignoreRow != 1) ? total + partDelt : total - partDelt;
            ignoreRow++;
        }
        return total;
    }

    static double[] getDeltaElements(double[][] a, int ignoreRow, int ignoreColumn) {
        int count = 0;
        double[] elements = new double[4];
        for (int i = 0; i < N; i++) {
            if (i != ignoreColumn) {
                for (int j = 0; j < N; j++) {
                    if (j != ignoreRow) {
                        elements[count] = a[i][j];
                        count++;
                    }
                }
            }
        }
        return elements;
    }

    static double[][] replaceWithVector(double[][] a, double[] v, int replaceColumn) {
        double[][] arr = new double[N][N];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == replaceColumn) {
                    arr[i][j] = v[i];
                } else {
                    arr[i][j] = a[i][j];
                }
            }
        }
        return arr;
    }

    static double getX(double Dn, double D) {
        return Dn / D;
    }

    static void check(double[] x) {
        double[][] a = getA();
        double t;
        double sum = 0;

        System.out.println("\nПроверка:\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                t = a[i][j] * x[j];
                sum += t;
                System.out.print("(" + a[i][j] + " * " + x[j] + ")");
                if (j < N - 1) {
                    System.out.print(" + ");
                }
            }
            System.out.print(" = " + sum);
            System.out.println();
            sum = 0;
        }
    }

    static void printAnswers(double[] x) {
        System.out.println("\nОтветы:\n");
        for (int i = 0; i < N; i++) {
            System.out.println("x[" + i + "] = " + x[i]);
        }
    }

    static double[] getB() {
        double[] b = new double[N];
        try {
            Scanner sc = new Scanner(new File(PATH));
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N + 1; j++) {
                    if (j == N) {
                        b[i] = sc.nextDouble();
                    } else {
                        sc.nextDouble();
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException | InputMismatchException e) {
            e.printStackTrace();
        }
        return b;
    }

    static double[][] getA() {
        double[][] a = new double[N][N];
        try {
            Scanner sc = new Scanner(new File(PATH));
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N + 1; j++) {
                    if (j == N) {
                        sc.nextDouble();
                    } else {
                        a[i][j] = sc.nextDouble();
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException | InputMismatchException e) {
            e.printStackTrace();
        }
        return a;
    }

    static void printB() {
        double[] b = getB();
        for (int i = 0; i < N; i++) {
            System.out.println("b[" + i + "] = " + b[i]);
        }
    }

    static void printArray(double[] m, String str) {
        for (int i = 0; i < N; i++) {
            System.out.println(str + "[" + i + "] = " + m[i]);
        }
    }

    static void printTwoDimensArray(double[][] m) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%.5f \t", m[i][j]);
            }
            System.out.println();
        }
    }

    /* Выполнил ст.гр. БПО-18-01 Исангулов Ринат */
    static void methodKramer(double[][] a, double[] b) {
        System.out.println("\n1. Метод Крамера\n");

        double[] x = new double[N];
        double D = getDelta(a);

        double D1 = getDelta(replaceWithVector(a, b, 0));
        x[0] = getX(D1, D);

        double D2 = getDelta(replaceWithVector(a, b, 1));
        x[1] = getX(D2, D);

        double D3 = getDelta(replaceWithVector(a, b, 2));
        x[2] = getX(D3, D);

        printAnswers(x);
        check(x);
        printB();
    }

    /* Выполнил ст.гр. БПО-18-01 Исангулов Ринат */
    static void methodGauss(double[][] a, double[] b) {
        System.out.println("\n2. Метод Гаусса\n");

        for (int k = 0; k < N; k++) {
            int maxIndex = getMaxIndex(k, a);

            double[] t = a[k];
            a[k] = a[maxIndex];
            a[maxIndex] = t;

            double tt = b[k];
            b[k] = b[maxIndex];
            b[maxIndex] = tt;

            for (int i = k + 1; i < N; i++) {
                double q = a[i][k] / a[k][k];
                b[i] -= q * b[k];

                for (int j = k; j < N; j++) {
                    a[i][j] -= q * a[k][j];
                }
            }
        }

        double[] x = reversePass(a, b);

        printAnswers(x);
        check(x);
        printB();

    }

    static int getMaxIndex(int k, double[][] a) {
        int maxIndex = k;
        double maxValue = Double.MIN_VALUE;

        for (int i = k + 1; i < N; i++) {
            double maxLineValue = Arrays.stream(a[i]).max().getAsDouble();
            if (maxLineValue > maxValue) {
                maxIndex = i;
                maxValue = maxLineValue;
            }
        }
        return maxIndex;
    }

    static double[] reversePass(double[][] a, double[] b) {
        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += a[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / a[i][i];
        }
        return x;
    }

    /* Выполнил ст.гр. БПО-18-01 Исангулов Ринат */
    static void methodMatrix(double[][] a, double[] b) {
        System.out.println("\n3.Матричный метод\n");

        double D = getDelta(a);

        double[][] m = getMatrixOfAlgebraicAdditions(a);
        //printTwoDimensArray(m);

        double[][] inv = getInverseMatrix(m, D);
        System.out.println("\nОбратная матрица:\n");
        printTwoDimensArray(inv);

        double[] x = getX(inv, b);
        printAnswers(x);
        check(x);
        printB();
    }

    static double[][] getMatrixOfAlgebraicAdditions(double[][] a) {
        double[][] m = new double[N][N];
        double[] deltaElements;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                deltaElements = getDeltaElements(a, i, j);
                m[i][j] = deltaElements[0] * deltaElements[3] - deltaElements[1] * deltaElements[2];
                if ((i == 0 && j == 1) || (i == 1 && j == 0) || (i == 1 && j == 2) || (i == 2 && j == 1)) {
                    m[i][j] *= -1;
                }
            }
        }
        return m;
    }

    static double[][] getInverseMatrix(double[][] a, double delt) {
        double[][] inv = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                inv[i][j] = (double) 1 / delt * a[i][j];
            }
        }
        return inv;
    }

    static double[] getX(double[][] inv, double[] b) {
        double[] x = new double[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                x[i] += inv[i][j] * b[j];
            }
        }
        return x;
    }

    static boolean checkApplicability(double[][] arr) {
        for (int i = 0; i < N; i++) {
            double sum = 0;
            for (int j = 0; j < N; j++) {
                sum += Math.abs(arr[i][j]);
            }
            if (sum >= 1) {
                System.out.println("Невозможно применить метод!");
                return false;
            }
        }
        return true;
    }

    static Tuple2<double[][], double[]> getC(double[][] a, double[] b) {
        double[] d = new double[N];
        double[][] c = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    c[i][j] = 0;
                    continue;
                }
                c[i][j] = -a[i][j] / a[i][i];
            }
            d[i] = b[i] / a[i][i];
        }
        return new Tuple2(c, d);
    }

    static double getE(double[] eps) {
        double e = 0;
        for (int i = 0; i < N; i++) {
            if (eps[i] > e) {
                e = eps[i];
            }
        }
        return e;
    }

    /* Выполнил ст.гр. БПО-18-01 Исангулов Ринат */
    static void methodJacobi(double[][] a, double[] b) {
        System.out.println("\n4.Метод Якоби\n");

        Tuple2 tuple = getC(a, b);
        double[][] c = (double[][]) tuple.get1();
        double[] d = (double[]) tuple.get2();
        printTwoDimensArray(c);

        if (checkApplicability(c)) {
            double[] x = Arrays.copyOf(d, N);
            double[] x1 = new double[N];
            double[] eps = new double[N];
            double e = 1;
            int count = 1;

            while (e > E) {
                System.out.print(count + "\t");
                for (int i = 0; i < N; i++) {
                    x1[i] = 0;
                    for (int j = 0; j < N; j++) {
                        x1[i] += c[i][j] * x[j];
                    }
                    eps[i] = Math.abs(x1[i] + d[i] - x[i]);
                    x[i] = x1[i] + d[i];
                }
                e = getE(eps);
                count++;
            }
            printAnswers(x);
            check(x);
            printB();
        }
    }

    /* Выполнил ст.гр. БПО-18-01 Исангулов Ринат */
    static void methodZeid(double[][] a, double[] b) {
        System.out.println("\n5.Метод Зейделя\n");

        Tuple2 tuple = getC(a, b);
        double[][] c = (double[][]) tuple.get1();
        double[] d = (double[]) tuple.get2();
        printTwoDimensArray(c);

        if (checkApplicability(c)) {
            double[] x = Arrays.copyOf(d, N);
            double[] x1 = new double[N];
            double[] x2 = new double[N];
            double[] eps = new double[N];
            double e = 1;
            int count = 1;

            while (e > E) {
                System.out.print(count + "\t");
                for (int i = 0; i < N; i++) {
                    x1[i] = 0;
                    for (int j = 0; j <= i - 1; j++) {
                        x1[i] += c[i][j] * x[j];
                    }
                    x2[i] = 0;
                    for (int j = i + 1; j < N; j++) {
                        x2[i] += c[i][j] * x[j];
                    }
                    eps[i] = Math.abs(x1[i] + x2[i] + d[i] - x[i]);
                    x[i] = x1[i] + x[i] + d[i];
                }
                e = getE(eps);
                count++;
            }
            printAnswers(x);
            check(x);
            printB();
        }
    }

    /* Выполнил ст.гр. БПО-18-01 Исангулов Ринат */
    static void methodRelaxation(double[][] a, double[] b) {
        System.out.println("\n6.Метод релаксации\n");

        Tuple2 tuple = getC(a, b);
        double[][] c = (double[][]) tuple.get1();
        double[] d = (double[]) tuple.get2();
        printTwoDimensArray(c);

        if (checkApplicability(c)) {
            double[] x = Arrays.copyOf(d, N);
            double[] x1 = Arrays.copyOf(d, N);
            double[] x2 = new double[N];
            double[] z = Arrays.copyOf(d, N);
            double[] eps = new double[N];
            double e = 1, w = 0.5, z0;
            int count = 1;

            while (e > E) {
                System.out.print(count + "\t");
                for (int i = 0; i < N; i++) {
                    x1[i] = 0;
                    for (int j = 0; j <= i - 1; j++) {
                        x1[i] += c[i][j] * x[j];
                    }
                    x2[i] = 0;
                    for (int j = i + 1; j < N; j++) {
                        x2[i] += c[i][j] * z[j];
                    }
                    z0 = z[i];
                    x2[i] = x1[i] + x2[i] + d[i];
                    x[i] = x2[i] + (w - 1) * (z[i] - z0);
                    eps[i] = Math.abs(x[i] - z0);
                }
                e = getE(eps);
                count++;
            }
            printAnswers(x);
            check(x);
            printB();
        }
    }
}