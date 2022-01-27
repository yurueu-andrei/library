package by.library.yurueu.entity;

public class Book {
    private Long id;
    private String title;
    private int pagesNumber;
    private String imagePath;

    public Book() {}

    public Book(Long id, String title, int pagesNumber, String imagePath) {
        this.id = id;
        this.title = title;
        this.pagesNumber = pagesNumber;
        this.imagePath = imagePath;
    }

    public Book(String title, int pagesNumber, String imagePath) {
        this.title = title;
        this.pagesNumber = pagesNumber;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}

        Book aThat = (Book) obj;

        if(getId() == null) {
            if(aThat.getId() != null) {return false;}
        } else if(!getId().equals(aThat.getId())) { return false;}

        if(getTitle() == null) {
            if(aThat.getTitle() != null) {return false;}
        } else if(!getTitle().equals(aThat.getTitle())) { return false;}

        if(getPagesNumber() != aThat.getPagesNumber()) {return false;}

        if(getImagePath() == null) {
            return aThat.getImagePath() == null;
        } else return getImagePath().equals(aThat.getImagePath());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = prime * result + (getImagePath() != null ? getImagePath().hashCode() : 0);
        result = prime * result + getPagesNumber();
        return result;
    }

    @Override

    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{id=").append(getId())
                .append(", title=").append(getTitle())
                .append(", pagesNumber=").append(getPagesNumber())
                .append(", imagePath=").append(getImagePath())
                .append("}");
        return sb.toString();
    }
}
