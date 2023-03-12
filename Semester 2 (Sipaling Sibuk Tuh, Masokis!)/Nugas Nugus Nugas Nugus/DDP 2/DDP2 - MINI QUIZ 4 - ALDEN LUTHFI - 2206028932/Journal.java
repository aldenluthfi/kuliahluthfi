
public class Journal {

    private String isbn, title, doi, journalName;
    private Author[] authors;

    public Journal(String isbn, String title) {                                 /* Contructor pertama                 */
        this.isbn = isbn;
        this.title = title;
    }

    public Journal(String isbn, String title, String journalName) {             /* Contructor kedua                   */
        this.isbn = isbn;
        this.title = title;
        this.journalName = journalName;
    }

    public Journal(String isbn, String title, String journalName, String doi, Author[] authors) { /* Contructor ketiga*/
        this.isbn = isbn;
        this.title = title;
        this.journalName = journalName;
        this.doi = doi;
        this.authors = authors;
    }

    public String getISBN() {                                                   /* getter ISBN                        */
        return this.isbn;
    }

    public String getTitle() {                                                  /* getter title                       */
        return this.title;
    }

    public String getJournalName() {                                            /* getter journal name                */
        return this.journalName;
    }

    public Author[] getAuthors() {                                              /* getter authors                     */
        return this.authors;
    }

    public String getDOI() {                                                    /* getter DOI                         */
        return this.doi;
    }

    public void setTitle(String newTitle) {                                     /* setter title                       */
        this.title = newTitle;
    }

    public void setAuthors(Author[] newAuthors) {                               /* setter authors                     */
        this.authors = newAuthors;
    }

    public void setJOurnalName(String newJournalName) {                         /* setter journal name                */
        this.journalName = newJournalName;
    }

    public String toString() {                                                  /* Format class dalam string          */
        String result = "Journal %s - %s - %s - %s\nAuthors:"
            .formatted(
                this.isbn,
                this.title,
                this.journalName,
                this.doi
            );

        for (Author author: authors) {
            result += "\nAuthor %s".formatted(author.toString());
        }

        return result;
    }

    public boolean equals(Journal other) {                                      /* Compare sesama journal             */
        return this.doi.equals(other.doi);
    }

    public boolean equals(Author[] other) {                                     /* Compare sesama berdasarkan author  */
        for (Author author: authors) {
            if (!this.doi.equals(author.getDOI())) return false;
        }
        return true;
    }
}
