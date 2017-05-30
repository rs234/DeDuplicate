package com.eci.logic.test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class DeDup {
	private static final int[] intArray = { 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86,
			25, 43, 2, 1, 10000, 11, 16, 19, 1, 18, 4, 9, 3, 20, 17, 8, 15, 6,
			2, 5, 10, 14, 12, 13, 7, 8, 9, 1, 2, 15, 12, 18, 10, 14, 20, 17,
			16, 3, 6, 19, 13, 5, 11, 4, 7, 19, 16, 5, 9, 12, 3, 20, 7, 15, 17,
			10, 6, 1, 8, 18, 4, 14, 13, 2, 11 };

	public static void main(String[] args) {

		DeDup deDup = new DeDup();

		int[] uniqueIntArray = deDup.removeDuplicatesJava8(intArray);
		display(uniqueIntArray);

		Integer[] uniqueIntegerArray = deDup
				.removeDuplicatesWithArrays(intArray);
		display(uniqueIntegerArray);

		uniqueIntegerArray = deDup.removeDuplicatesWithCollections(intArray);
		display(uniqueIntegerArray);

		// Modifying insertion order
		uniqueIntArray = deDup.removeDuplicatesByModifingInsertionOrder(Arrays
				.copyOf(intArray, intArray.length));
		display(uniqueIntArray);

		uniqueIntArray = deDup.removeDuplicatesWithSorting(Arrays.copyOf(
				intArray, intArray.length));
		display(uniqueIntArray);

	}

	private static void display(int[] uniqueIntArray) {
		for (int i = 0; i < uniqueIntArray.length; i++) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(uniqueIntArray[i]);
		}
		System.out.println();
	}

	private static void display(Integer[] uniqueIntArray) {
		for (int i = 0; i < uniqueIntArray.length; i++) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(uniqueIntArray[i]);
		}
		System.out.println();
	}

	// Using the collections
	//
	public Integer[] removeDuplicatesWithCollections(int[] intArray) {
		Set<Integer> uniqueSet = new LinkedHashSet<Integer>();
		for (Integer intNum : intArray) {
			uniqueSet.add(intNum);
		}
		Integer[] uniqueIntArray = new Integer[uniqueSet.size()];
		return uniqueSet.toArray(uniqueIntArray);
	}

	// Storing unique values in temporary array for checking already value
	// exists or not
	// performance - O(n2)
	public Integer[] removeDuplicatesWithArrays(int[] intArray) {
		Integer[] uniqueArray = new Integer[intArray.length];
		boolean found = false;
		int foundSize = 0;
		int uaIndex = 0;
		for (int i = 0; i < intArray.length; i++) {

			found = false;
			if (i > 0) {
				for (int j = 0; j < uniqueArray.length; j++) {
					if (uniqueArray[j] == null)
						break;

					if (intArray[i] == uniqueArray[j]) {
						found = true;
						foundSize++;
						break;
					}
				}
			}
			if (!found) {
				uniqueArray[uaIndex++] = intArray[i];
			}
		}
		uniqueArray = Arrays.copyOf(uniqueArray, intArray.length - foundSize);
		return uniqueArray;
	}

	// Java 8 provides the way to remove duplicate values from the array.
	// Only one line code and efficient way
	// performance - O(log n)
	public int[] removeDuplicatesJava8(int[] arr) {
		return Arrays.stream(arr).distinct().toArray();
	}

	// Modifying insertion order
	// performance- O(log n), O(n)
	public int[] removeDuplicatesWithSorting(int[] intArr) {
		if (intArr.length <= 1) {
			return intArr;
		}

		Arrays.sort(intArr);

		int lastFound = intArr[0];
		int currPos = 1;

		for (int i = 1; i < intArr.length; ++i) {
			int num = intArr[i];
			if (lastFound != num) {
				lastFound = num;
				intArr[currPos++] = num;
			}
		}

		return Arrays.copyOf(intArr, currPos);
	}

	// Modifying insertion order
	// looping the array twice
	// performance- O(n2)
	public int[] removeDuplicatesByModifingInsertionOrder(int[] intArr) {
		int noOfUniqueElements = intArr.length;

		for (int i = 0; i < noOfUniqueElements; i++) {
			for (int j = i + 1; j < noOfUniqueElements; j++) {
				if (intArr[i] == intArr[j]) {
					intArr[j] = intArr[noOfUniqueElements - 1];
					noOfUniqueElements--;
					j--;
				}
			}
		}

		return Arrays.copyOf(intArr, noOfUniqueElements);
	}

}
