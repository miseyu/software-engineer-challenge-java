package software.engineer.challenge;

import java.util.NoSuchElementException;

public class ImmutableQueue<T> implements Queue<T> {
    private static class InnerStack<T> {
        private T head;
        private InnerStack<T> tail;
        private int size;

        private InnerStack() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        private InnerStack(T head, InnerStack<T> tail) {
            this.head = head;
            this.tail = tail;
            this.size = tail.size + 1;
        }

        private boolean isEmpty() {
            return this.size == 0;
        }

        private InnerStack<T> push(T obj) {
            return new InnerStack<>(obj, this);
        }

        private InnerStack<T> toReverseStack() {
            InnerStack<T> stack = new InnerStack<>();
            InnerStack<T> tail = this;
            while (!tail.isEmpty()) {
                stack = stack.push(tail.head);
                tail = tail.tail;
            }
            return stack;
        }
    }

    private InnerStack<T> order;
    private InnerStack<T> reverse;

    public ImmutableQueue() {
        this.order = new InnerStack<>();
        this.reverse = new InnerStack<>();
    }

    public ImmutableQueue(InnerStack<T> order, InnerStack<T> reverse) {
        this.order = order;
        this.reverse = reverse;
    }

    @Override
    public Queue<T> enQueue(T t) {
        if (t == null) {
            throw new IllegalArgumentException();
        }
        return new ImmutableQueue<>(this.order.push(t), this.reverse);
    }

    @Override
    public Queue<T> deQueue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("queue empty");
        }
        if (!this.reverse.isEmpty()) {
            return new ImmutableQueue<>(this.order, this.reverse.tail);
        }
        return new ImmutableQueue<>(new InnerStack<>(), this.order.toReverseStack().tail);
    }

    @Override
    public T head() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (this.reverse.isEmpty()) {
            this.reverse = this.order.toReverseStack();
            this.order = new InnerStack<>();
        }
        return this.reverse.head;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    protected int size() {
        return this.order.size + this.reverse.size;
    }
}
