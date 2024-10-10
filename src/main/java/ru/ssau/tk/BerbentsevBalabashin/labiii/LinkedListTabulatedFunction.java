package ru.ssau.tk.BerbentsevBalabashin.labiii;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction{
    private int count;
    private Node head;


    private static class Node {
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
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {//конструктор
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
        Node curr = head;
        for (int i = 0; i < count; i++) {
            curr = curr.next;
            if (curr.x > x) return i;
        }
        return 0;
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return head.y;
        }
        Node left = getNode(floorIndex);
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return head.y;
        }
        Node left = head;
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return head.y;
        }
        Node left = head.prev.prev;
        Node right = head.prev;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }
}