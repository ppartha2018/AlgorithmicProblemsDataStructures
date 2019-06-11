package datastructures.algorithms;

/*
 * Implementation of Heap with Max Heap function.
 */
public class Heap {
	
	int[] arr;
	int size;
	
	Heap(int[] a){
		this.size = a.length;
		this.arr = a;
		buildMaxHeap();
	}
	
	public boolean isLeaf(int index) {
		if(2*index+1 >= size) return true;
		else return false;
	}
	
	public void maxHeapify(int index) {
		int left = 2*index + 1;
		int right = (2*index) + 2;
		if(!isLeaf(index) && ((left < size && arr[left] > arr[index]) || (right < size && arr[right] > arr[index]))) {
			int k = 0;
			if(right >= size)
				k = left;
			else
				k = (arr[left] > arr[right]) ? left : right; 
			int temp = arr[index];
			arr[index] = arr[k];
			arr[k] = temp;
			maxHeapify(k);
		}
	}
	
	public void buildMaxHeap() {
		for(int i = size/2 - 1; i >= 0; i--) {
			maxHeapify(i);
		}
	}
	
	public int extractMax() {
		if(size > 0) {
			int root = arr[0];
			arr[0] = arr[size-1];
			size--;
			maxHeapify(0);
			return root;
		}
		else return -1;
	}
	
	
	public void heapSort() {
		//buildMaxHeap();
		for(int i=size-1; i >= 1; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			size--;
			maxHeapify(0);
		}
		printHeap();
	}
	
	public void printHeap() {
		for(int n: arr) {
			System.out.print(n+" ");
		}
	}
	
	public static void main(String[] args) {
		Heap heap = new Heap(new int[] {9,4,6,10,2,11,16,1,15,8});
		heap.heapSort();
		//System.out.println(heap.extractMax());
		//System.out.println(heap.extractMax());
		//System.out.println(heap.extractMax());
		//System.out.println(heap.extractMax());
	}

}
