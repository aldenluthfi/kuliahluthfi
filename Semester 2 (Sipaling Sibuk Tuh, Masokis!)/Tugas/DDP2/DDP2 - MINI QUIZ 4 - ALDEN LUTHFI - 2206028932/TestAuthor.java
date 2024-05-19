public class TestAuthor{
	public static void main(String[] args){
		Author a = new Author("Ayu", "ayu@gmail.com");
		Author b = new Author("bayu", "bay@gmail.com");
		Author c = new Author("Au", "ayu@gmail.com");

		System.out.println(a.getEmail());
		System.out.println(a.getDOI());
		System.out.println(a.getInstitution());
		System.out.println(a.getClass());

		b.setEmail("bayu@gmail.com");
		b.setName("Bayu");

		System.out.println(b.getName());
		System.out.println(b.getEmail());

		System.out.println(a.equals(c));
	}
}

