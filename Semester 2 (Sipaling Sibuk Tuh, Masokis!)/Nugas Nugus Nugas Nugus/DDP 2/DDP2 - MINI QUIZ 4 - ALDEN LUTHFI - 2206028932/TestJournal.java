public class TestJournal{
	public static void main(String[] args){
		Author a = new Author("Ayu", "ayu@gmail.com");
		Author b = new Author("bayu", "bay@gmail.com");
		Author c = new Author("Au", "ayu@gmail.com");
		Author[] abc = {a, b, c};

		Journal aj = new Journal("ISBN 123-123-123", "Mein Kampf", "Pearson", "123-123-123", abc);
		Journal bj = new Journal("ISBN 123-123-123", "Mein Kåmpf", "Pearson", "123-123-123", abc);

		System.out.println(aj.getDOI());
		System.out.println(aj.getISBN());
		System.out.println(aj.getJournalName());
		System.out.println(aj.getTitle());
		Author[] authors = aj.getAuthors();

		for (Author author: authors) {
			System.out.println(author);
		}

		System.out.println(aj.equals(bj));
	}
}