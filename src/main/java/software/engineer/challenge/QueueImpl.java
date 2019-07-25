package software.engineer.challenge;

public class QueueImpl<T> implements Queue<T> {
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

        private static InnerStack newInstance() {
            return new InnerStack();
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public InnerStack push(T obj) {
            return new InnerStack(obj, this);
        }
    }

    @Override
    public Queue<T> enQueue(T t) {
        return null;
    }

    @Override
    public Queue<T> deQueue() {
        return null;
    }

    @Override
    public T head() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
