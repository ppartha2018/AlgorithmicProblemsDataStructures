package com.prasanna.practice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.PriorityQueue;

public class MedianOfRunningNumbers {

	/*
	 * https://stackoverflow.com/questions/10657503/find-running-median-from-a-
	 * stream-of-integers For the first two elements add smaller one to the maxHeap
	 * on the left, and bigger one to the minHeap on the right. Then process stream
	 * data one by one,
	 * 
	 * Step 1: Add next item to one of the heaps
	 * 
	 * if next item is smaller than maxHeap root add it to maxHeap, else add it to
	 * minHeap
	 * 
	 * Step 2: Balance the heaps (after this step heaps will be either balanced or
	 * one of them will contain 1 more item)
	 * 
	 * if number of elements in one of the heaps is greater than the other by more
	 * than 1, remove the root element from the one containing more elements and add
	 * to the other one.
	 * 
	 * Then at any given time you can calculate median like this: If the heaps
	 * contain equal amount of elements; median = (root of maxHeap + root of
	 * minHeap)/2 Else median = root of the heap with more elements
	 *
	 */

	public static void findMedianRunningNumbers(int[] numbers) {

		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

		double median;

		median = numbers[0];
		System.out.println("Median: " + median);

		if (numbers[0] < numbers[1]) {
			maxHeap.add(numbers[0]);
			minHeap.add(numbers[1]);
		} else {
			maxHeap.add(numbers[1]);
			minHeap.add(numbers[0]);
		}

		median = (double) (maxHeap.peek() + minHeap.peek()) / 2;
		median = BigDecimal.valueOf(median).setScale(1, RoundingMode.HALF_UP).doubleValue();
		System.out.println("Median: " + median);

		for (int i = 2; i < numbers.length; i++) {
			if (numbers[i] < maxHeap.peek())
				maxHeap.add(numbers[i]);
			else
				minHeap.add(numbers[i]);

			if (Math.abs(maxHeap.size() - minHeap.size()) > 1) {
				if (maxHeap.size() > minHeap.size()) {
					minHeap.add(maxHeap.remove());
				} else {
					maxHeap.add(minHeap.remove());
				}
			}
			if (maxHeap.size() > minHeap.size()) {
				median = maxHeap.peek();
			} else if (minHeap.size() > maxHeap.size()) {
				median = minHeap.peek();
			} else {
				median = (double) (maxHeap.peek() + minHeap.peek()) / 2;
				median = BigDecimal.valueOf(median).setScale(1, RoundingMode.HALF_UP).doubleValue();
			}
			System.out.println("Median: " + median);
		}

	}

	public static void main(String[] args) {
		MedianOfRunningNumbers.findMedianRunningNumbers(new int[] { 12, 4, 5, 3, 8, 7 });
	}

}
