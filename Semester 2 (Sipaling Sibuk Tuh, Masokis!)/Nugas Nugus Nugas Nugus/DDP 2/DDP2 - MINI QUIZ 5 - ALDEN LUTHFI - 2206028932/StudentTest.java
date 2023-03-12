
/**
 * Nama: Alden Luthfi
 * NPM: 2206028932
 */

import java.util.Arrays;

public class StudentTest {

	public static void main(String[] args) {
		// Contoh array of Student
		Student[] students = new Student[10];
		students[0] = new Student("190111", "Spongebob");
		students[1] = new Student("190123", "Doraemon");
		students[2] = new Student("190135", "Nobita");
		students[3] = new Student("190175", "Eren");
		students[4] = new Student("190135", "Nobita");
		students[5] = new Student("190123", "Doraemon");
		students[6] = new Student("190199", "Nobita");
		students[7] = new Student("190178", "Levi");
		students[8] = new Student("190123", "Doraemon");
		students[9] = new Student("190187", "Mikasa");

		System.out.println(Arrays.toString(students));

		// Meremove Student dengan NPM 190123 dan Nama Doraemon
		removeFromArrays(students, new Student("190123", "Nobita"));

		System.out.println(Arrays.toString(students));

	}

	public static void removeFromArrays(Student[] array, Student s) {
        for (int i = 0; i < array.length; i++) {
			if (array[i] != null && array[i].equals(s)) array[i] = null;        /* Adding all other elements          */

			System.out.println(Arrays.toString(array));

			int j = i;
			if (array[j] == null) {												/* Mendorong null                     */
				while (j + 1 < array.length && array[j + 1] != null) {			/* menukar element                    */
					Student temp = array[j + 1];
					array[j + 1] = array[j];
					array[j++] = temp;
				}
			}
		}
	}
}