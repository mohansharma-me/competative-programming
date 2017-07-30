/*

Given an array A your task is to tell at which position the equilibrium first occurs in the array. Equilibrium position in an array is a position such that the sum of elements below it is equal to the sum of elements after it.

Input:
The first line of input contains an integer T denoting the no of test cases then T test cases follow. First line of each test case contains an integer N denoting the size of the array. Then in the next line are N space separated values of the array A.

Output:
For each test case in a new  line print the position at which the elements are at equilibrium if no equilibrium point exists print -1.

Constraints:
1<=T<=100
1<=N<=100

Example:
Input:
2
1
1
5
1 3 5 2 2

Output:
1
3

Explanation:
1. Since its the only element hence its the only equilibrium point
2. For second test case equilibrium point is at position 3 as elements below it (1+3) = elements after it (2+2)
 

*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        IO.open();
        try {
            int T = IO.readInt();
            while(T-->0) {
                int[] numbers = new int[IO.readInt()];
                for(int i=0; i<numbers.length; i++)
                    numbers[i] = IO.readInt();
                if(numbers.length>1) {
                    int l=0, r=numbers.length-1;
                    int sumL=numbers[l], sumR=numbers[r];
                    while(l<r) {
                        if(sumL < sumR) {
                            l++;
                            if(l<=r)
                                sumL += numbers[l];
                        } else if(sumL > sumR) {
                            r--;
                            if(r>=0)
                                sumR += numbers[r];
                        } else {
                            l++; r--;
                            if(l<=r)
                                sumL += numbers[l];
                            if(r>=0)
                            sumR += numbers[r];
                        }
                    }
                    if(sumL == sumR) {
                        IO.println(l+1);
                    } else {
                        IO.println("-1");
                    }
                } else {
                    IO.println("1");
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace(System.out);
            throw ex;
        }
        IO.flush();
        IO.close();
    }  
}

class Brain {
    // Graph variables
    // HashMap<Integer, Node> nodes = new HashMap<>();
    // HashMap<Integer, HashMap<Integer, Edge>> edges = new HashMap<>(); // -- Edge object
    // HashMap<Integer, HashMap<Integer, Integer>> edges = new HashMap<>(); // -- Direct weight
}

class Edge implements Comparable<Edge>{
    public int weight, source, dest;
    public Edge(int source, int dest) { this.source = source; this.dest = dest; this.weight = 0; }
    public Edge(int source, int dest, int weight) { this(source, dest); this.weight = weight; }
    public int compareTo(Edge edge) { return this.weight - edge.weight; }
}

class Node implements Comparable<Node> {
    public int value;
    public Node(int value) { this.value = value; }
    public int compareTo(Node node) { return this.value - node.value; }
}

class TreeNode<E> {
    public E value;
    public TreeNode<E> left, right, parent;
    public TreeNode(E value) { this.value = value; }
}

class IO {
    // reader related variables
    private static InputStream stream;
    private static byte[] buf = new byte[1024];
    private static int curChar;
    private static int numChars;
    private static SpaceCharFilter filter; 
    // writer realted variables
    private static PrintWriter writer;
    // constructer
    private IO() {
        this.stream = System.in;
        this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }
    private IO(InputStream inputStream, OutputStream outputStream) {
        this.stream = inputStream;
        this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public static IO open() {
        return open(System.in, System.out);
    }

    public static IO open(InputStream is, OutputStream os) {
        return new IO(is, os);
    }

    // helper methods

    // reader methods
    public static boolean isEOL() {
        try {
            return read(true) == '\r' || read(true) == '\n' || read(true) == -1;
        } catch(Exception ex) {
            return false;
        }
    }
    public static int read() {
        return read(false);
    }
    public static int read(boolean lookahead) {
        if (numChars == -1) {
            if(lookahead) return -1;
            throw new InputMismatchException();
        }
        if (curChar >= numChars)
        {
            curChar = 0;
            try
            {
                numChars = stream.read(buf);
            } catch (IOException e)
            {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        if(lookahead) return buf[curChar];
        return buf[curChar++];
    }

    public static int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-')
        {
            sgn = -1;
            c = read();
        } else if(c == '+') {
            c = read();
        }
        int res = 0;
        do
        {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read(true);
            if(isSpaceChar(c)) break;
            c = read();
        } while (true);
        return res * sgn;
    }
    public static String readString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do
        {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }
    public static String readLine() {
        int c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while(c!='\n');
        return res.toString();  
    }
    public static double readDouble() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        } else if(c == '+') {
            c = read();
        }
        double res = 0;
        while (!isSpaceChar(c) && c != '.') {
            if (c == 'e' || c == 'E')
                return res * Math.pow(10, readInt());
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        }
        if (c == '.') {
            c = read();
            double m = 1;
            while (!isSpaceChar(c)) {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, readInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                m /= 10;
                res += (c - '0') * m;
                c = read();
            }
        }
        return res * sgn;
    }
    public static long readLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        } else if(c == '+') {
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }
    public static boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
    public static String next() {
        return readString();
    }
    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
    // writer methods
    public static void print(Object... objects) {
        for (int i = 0; i < objects.length; i++)
        {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }
    public static void println(Object... objects) {
        print(objects);
        writer.println();
    }
    public static void close() {
        writer.flush();
        writer.close();
    }
    public static void flush() {
        writer.flush();
    }

}
