public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
	    items = (T[]) new Object[8];
		size = 0;
		nextFirst = 0;
		nextLast = 1;
	}

    private void resize(int ca) {
		T[] a = (T[]) new Object[ca];
		if (nextLast == 0) {
			System.arraycopy(items, 0, a, 0, size);
			nextFirst = ca - 1;
			nextLast = size;
			items = a;
		} else {
		System.arraycopy(items, 0, a, 0, nextLast);
	    System.arraycopy(items, nextFirst + 1, a,
	        ca - items.length + nextFirst + 1, items.length - nextFirst - 1);
		nextFirst = ca - items.length + nextFirst;
		items = a;
		}
	}


	public void addFirst(T item) {
		if (items[nextFirst] != null) {
			resize(2 * items.length);
		}
		items[nextFirst] = item;
		nextFirst--;
		if (nextFirst < 0) {
			nextFirst = items.length - 1;
		}
		size++;
	}

	public void addLast(T item) {
		if (items[nextFirst] != null) {
			resize(2 * items.length);
		}
		items[nextLast] = item;
		nextLast++;
		if (nextLast > items.length - 1) {
			nextLast = 0;
		}
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void printDeque() {
		int temp = nextFirst + 1 > items.length - 1 ? 0 : nextFirst + 1;
		for (int i = 0; i < size; i++) {
			temp = temp > items.length - 1 ? 0 : temp;
			System.out.print(items[temp]);
			System.out.print(' ');
			temp += 1;
		}
		System.out.print('\n');
		System.out.println(items.length);
		System.out.println(size);
		System.out.println(nextFirst);
		System.out.println(nextLast);
	}

	public T removeFirst() {
		if (size > 8 && items.length / size > 3) {
			resize(items.length / 2);
		}
		if (size == 0) {
			return null;
		}
		int temp = nextFirst + 1 > items.length - 1 ? 0 : nextFirst + 1;
		T res = items[temp];
		items[temp] = null;
		nextFirst = temp;
		size--;
		return res;
	}

	public T removeLast() {
		if (size > 8 && items.length / size > 3) {
			resize(items.length / 2);
		}
		if (size == 0) {
			return null;
		}
		int temp = nextLast - 1 < 0 ? items.length - 1 : nextLast - 1;
		T res = items[temp];
		items[temp] = null;
		nextLast = temp;
		size--;
		return res;
	}

	public T get(int index) {
		int res = nextFirst;
		for (int i = 0; i < index + 1; i++) {
			res = res + 1 > items.length - 1 ? 0 : res + 1;
		}
		return items[res];
	}
}
