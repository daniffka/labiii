package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;
import java.util.Iterator;
import java.io.Serial;
import java.io.Serializable;
import java.util.NoSuchElementException;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.DifferentLengthOfArraysException;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.ArrayIsNotSortedException;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.InterpolationException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Removable,Insertable,Serializable{

    @Serial
    private static final long serialVersionUID = 2161617884524327447L;
    private int count;
    private Node head;


    private static class Node implements Serializable {
        public double x;
        public double y;
        public Node next;
        public Node prev;


        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private void addNode(double x, double y) {
        Node new_Node = new Node(x, y);
        if (head == null) {
            head = new_Node;
            head.next = head;
            head.prev = head;
        } else {
            Node last = head.prev;
            last.next = new_Node;
            head.prev = new_Node;
            new_Node.prev = last;
            new_Node.next = head;
        }
        count++;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues);
        AbstractTabulatedFunction.checkSorted(xValues);
        if (xValues.length < 2)
            throw new IllegalArgumentException("list must contain at least two elements");
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {//конструктор
        if (count < 2)
            throw new IllegalArgumentException("array must contain at least two elements");
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        if (xFrom == xTo) {
            double y_Value = source.apply(xFrom);
            for (int i = 0; i < count; i++) {
                addNode(xFrom, y_Value);
            }
        } else {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; i++) {
                double x = xFrom + i * step;
                addNode(x, source.apply(x));
            }
        }

    }

    @Override
    public int getCount() {
        return count;
    }
    @Override
    public double leftBound() {
        return head.x;
    }
    @Override
    public double rightBound() {
        return head.prev.x;
    }
    private Node getNode(int index) {
        Node curr = head;
        if (index < count / 2) {
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else {
            curr = head.prev;
            for (int i = count - 1; i > index; i--) {
                curr = curr.prev;
            }
        }
        return curr;
    }
    @Override
    public double getX(int index) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException("index is out of bounds");
        return getNode(index).x;
    }
    @Override
    public double getY(int index) {
        return getNode(index).y;
    }
    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }
    @Override
    public int indexOfX(double x) {
        Node curr = head;
        for (int i = 0; i < count; i++) {
            if (curr.x == x) {
                return i;
            }
            curr = curr.next;
        }
        return -1;
    }
    @Override
    public int indexOfY(double y) {
        Node curr = head;
        for (int i = 0; i < count; i++) {
            if (curr.y == y) {
                return i;
            }
            curr = curr.next;
        }
        return -1;
    }
    @Override
    protected int floorIndexOfX(double x) {
        if (x < leftBound())
            throw new IllegalArgumentException("x is less than left bound");
        Node curr = head;
        for (int i = 0; i < count; i++) {
            curr = curr.next;
            if (curr.x > x)
                return i;
        }
        return 0;
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return head.y;
        }

        if (!(x > getX(floorIndex) && x < getX(floorIndex + 1))) throw new InterpolationException("X is out of range");

        Node left = getNode(floorIndex);
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    @Override
    protected double extrapolateLeft(double x) {
        Node left = head;
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        Node left = head.prev.prev;
        Node right = head.prev;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    @Override
    public void remove(int index) {
        if (count == 0)
            throw new IllegalArgumentException("list is empty");

        if (index < 0 || index >= count)
            throw new IllegalArgumentException("index is out of bounds");
        Node toRemove = getNode(index);

        if (count == 1) {
            head = null;
        } else {
            if (toRemove == head) {
                head = head.next;
            }
            Node prev = toRemove.prev;
            Node next = toRemove.next;
            prev.next = next;
            next.prev = prev;
        }
        count--;
    }

    @Override
    public void insert(double x, double y) {
        if (head == null) addNode(x,y);
        Node tmp = head;

        do{

            if(tmp.x == x) tmp.y = y;
            if (tmp.next.x > x && tmp.x <x) {
                Node node = new Node(x,y);
                Node next = tmp.next;

                tmp.next = node;
                node.prev = tmp;
                node.next = next;
                next.prev = node;


                count++;
                return;
            }
            tmp = tmp.next;
        } while (tmp.next!=head);

        if (x<head.x){
            Node node = new Node(x,y);

            Node tail = head.prev;

            tail.next = node;
            node.next = head;
            node.prev = tail;
            head.prev = node;
            head = node;

            count++;
            return;

        }
        if(tmp.next.x<x&&tmp.x<x){
            Node node = new Node(x,y);
            Node tail = head.prev;

            tail.next = node;
            node.next = head;
            node.prev = tail;
            head.prev = node;

            count++;
            return;
        }
    }
    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private Node node = head;

            @Override
            public Point next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                Point p = new Point(node.x, node.y);
                node = node.next;
                if (node == head)
                    node = null;
                return p;
            }

            @Override
            public boolean hasNext() {
                return node != null;
            }
        };
    }

}