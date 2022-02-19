package rinat.isangulov;

import java.io.*;
import java.util.*;

/**
 * @author ст.гр. БПО-18-01 Исангулов Ринат
 */
public class LR1 {

    public static final int N = 3;
    public static final String PATH = "C:\\Users\\risangulov\\IdeaProjects\\KM\\src\\rinat\\isangulov\\lr1.txt";
    public static final String AUTHOR = "Выполнил ст.гр. БПО-18-01 Исангулов Ринат";


    public static void main(String[] args) {
        double[][] a = new double[N][N];
        double[] b = new double[N];

        readAndPrint(a, b);
        methodKramer(a, b);

        readAndPrint(a, b);
        methodGauss(a, b);

        readAndPrint(a, b);
        methodMatrix(a, b);
    }

    private static void readAndPrint(double[][] a, double[] b) {
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

    private static double[] getDeltaElements(double[][] a, int ignoreRow, int ignoreColumn) {
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

    private static double getX(double Dn, double D) {
        return Dn / D;
    }

    private static void check(double[] x) {
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

    private static void printAnswers(double[] x) {
        System.out.println("\nОтветы:\n");
        for (int i = 0; i < N; i++) {
            System.out.println("x[" + i + "] = " + x[i]);
        }
    }

    private static double[] getB() {
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

    private static double[][] getA() {
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

    private static void printB() {
        double[] b = getB();
        for (int i = 0; i < N; i++) {
            System.out.println("b[" + i + "] = " + b[i]);
        }
    }

    private static void printArray(double[] m, String str) {
        for (int i = 0; i < N; i++) {
            System.out.println(str + "[" + i + "] = " + m[i]);
        }
    }

    private static void printTwoDimensArray(double[][] m) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%.1f \t", m[i][j]);
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

    private static int getMaxIndex(int k, double[][] a) {
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

    private static double[] reversePass(double[][] a, double[] b) {
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

        double[] x = new double[N];

        //определить матрицы
        double D = getDelta(a);

        //находим матрицу алгебраических дополнений
        double[][] m = getMatrixOfAlgebraicAdditions(a);
        printTwoDimensArray(m);

        //обратная матрица
        double[][] inv = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                inv[i][j] = 1 / D * m[i][j];
                x[i] += inv[i][j] * b[j];
            }
        }
        printAnswers(x);
        check(x);
        printB();
    }

    public static double[][] getMatrixOfAlgebraicAdditions(double[][] a) {
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


    // 4.Метод Якоби
    // 5.Метод Зейделя
    // 6.Метод релаксации
}