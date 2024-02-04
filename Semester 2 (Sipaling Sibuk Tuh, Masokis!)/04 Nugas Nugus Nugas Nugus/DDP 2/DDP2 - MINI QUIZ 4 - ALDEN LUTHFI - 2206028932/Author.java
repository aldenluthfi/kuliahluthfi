public class Author {
    private String name, email, institution, doi;

    public Author(String name, String email) {                                  /* Contructor pertama                 */
        this.name = name;
        this.email = email;
    }

    public Author(String name, String email, String institution, String doi) {  /* Contructor kedua                   */
        this.name = name;
        this.email = email;
        this.institution = institution;
        this.doi = doi;
    }

    public String getName() {                                                   /* getter nama                        */
        return this.name;
    }

    public void setName(String newName) {                                       /* setter nama                        */
        this.name = newName;
    }

    public String getEmail() {                                                  /* getter email                       */
        return this.email;
    }

    public void setEmail(String newName) {                                      /* setter email                       */
        this.email = newName;
    }

    public String getDOI() {                                                    /* getter doi                         */
        return this.doi;
    }

    public String getInstitution() {                                            /* Getter institution                 */
        return this.institution;
    }

    public String toString() {                                                  /* Representasi author dalam string   */
        return "%s - %s".formatted(this.name, this.email);
    }

    public boolean equals(Author other) {                                       /* Compare sesama author              */
        return this.email.equals(other.getEmail());
    }
}
