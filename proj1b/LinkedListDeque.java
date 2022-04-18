public class LinkedListDeque<T> implements Deque<T> {
    private class StuffNode {
        private T item;
        private StuffNode next;
        private StuffNode prev;

		StuffNode(T i, StuffNode n1, StuffNode n2) {
            item = i;
            next = n1;
            prev = n2;
        }   
    } 
    private StuffNode sentinel;
    private int size;

    public LinkedListDeque() {
    	sentinel = new StuffNode(null, null, null);
    	sentinel.next = sentinel;
    	sentinel.prev = sentinel;
    	size = 0;
    }

    public T getRecursive(int index) {
    	return getRecursive(index, sentinel);
    }

	private T getRecursive(int index, StuffNode p) {
		if (p.next == sentinel) {
			return null;
		}
		if (index == 0) {
			return p.next.item;
		} else {
			return getRecursive(index - 1, p.next);
		}
	}

	@Override
	public void addFirst(T item) {
		size++;
		sentinel.next = new StuffNode(item, sentinel.next, sentinel);
		sentinel.next.next.prev = sentinel.next;
	}

	@Override
	public void addLast(T item) {
		size++;
		sentinel.prev = new StuffNode(item, sentinel, sentinel.prev);
		sentinel.prev.prev.next = sentinel.prev;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void printDeque() {
		StuffNode p = sentinel;
		while (p.next != sentinel) {
			System.out.print(p.next.item);
			System.out.print(' ');
			p = p.next;
		}
		System.out.print('\n');
	}

	@Override
	public T removeFirst() {
		if (!isEmpty()) {
			size--;
			StuffNode p = sentinel.next;
			sentinel.next = sentinel.next.next;
			sentinel.next.prev = sentinel;
			return p.item;
		}
		return null;
	}

	@Override
	public T removeLast() {
		if (!isEmpty()) {
			size--;
			StuffNode p = sentinel.prev;
			sentinel.prev = sentinel.prev.prev;
			sentinel.prev.next = sentinel;
			return p.item;
		}
		return null;
	}

	@Override
	public T get(int index) {
		StuffNode p = sentinel;
		for (int i = 0; i < index + 1; i++) {
			if (p.next == sentinel) {
				return null;
			}
			p = p.next;
		}
		return p.item;
	}
}
