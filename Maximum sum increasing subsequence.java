/*
Given an array of n positive integers. Write a program to find the sum of maximum sum subsequence of the given array such that the integers in the subsequence are sorted in increasing order.

Input:

The first line of input contains an integer T denoting the number of test cases.
The first line of each test case is N,N is the size of array.
The second line of each test case contains N input A[].

Output:

Print the sum of maximum sum sequence of the given array.

Constraints:

1 ≤ T ≤ 100
1 ≤ N ≤ 100
1 ≤ A[] ≤ 1000

Example:

Input:
2
7
1 101 2 3 100 4 5
4
10 5 4 3

Output:
106
10

Explanation:
For input:
7
1 101 2 3 100 4 5
All the increasing subsequences : (1,101); (1,2,3,100); (1,2,3,4,5), out of this (1,2,3,100) has maximum sum,i.e., 106. Hence the output is stated as 106.
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
                int[] maxNumbers = new int[numbers.length];
                for(int i=0; i<numbers.length; i++)
                    numbers[i] = maxNumbers[i] = IO.readInt();
                int max = numbers[0];
                for(int i=1; i<numbers.length; i++) 
                    for(int j=0; j<i; j++)
                        if(numbers[i]>numbers[j] && maxNumbers[i]<maxNumbers[j]+numbers[i]) {
                            maxNumbers[i]=maxNumbers[j]+numbers[i];
                            if(maxNumbers[i] > max) max = maxNumbers[i];
                        }
                IO.println(max);
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
