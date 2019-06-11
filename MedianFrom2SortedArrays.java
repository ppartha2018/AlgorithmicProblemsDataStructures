package com.prasanna.practice;

public class MedianFrom2SortedArrays {
	
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        int leftLength = nums1.length;
        int rightLength = nums2.length;
        int totalLength = nums1.length + nums2.length;
        double floorMiddle = Math.ceil((double)totalLength/2);
        double ceilMiddle = Math.ceil((double) (totalLength+1)/2);
        int[] sortedArray = new int[(int)ceilMiddle];
        for(int i=0, left = 0, right = 0; i < ceilMiddle; i++)
        {
            if(!(left < leftLength))
               sortedArray[i] = nums2[right++];
            else if(!(right < rightLength))
               sortedArray[i] = nums1[left++];
            else if(nums1[left] < nums2[right])
                sortedArray[i] = nums1[left++];
            else
                sortedArray[i] = nums2[right++];
        }
               
        double median = ((double)sortedArray[(int)floorMiddle-1] + (double)sortedArray[(int)ceilMiddle-1]) / 2;
        
        return median;
    }

	public static void main(String[] args) {
		int[] nums1 = new int[]{1,3};
		int[] nums2 = new int[] {2,4};
		
		MedianFrom2SortedArrays m = new MedianFrom2SortedArrays();
		System.out.println(m.findMedianSortedArrays(nums1, nums2));
	}

}
