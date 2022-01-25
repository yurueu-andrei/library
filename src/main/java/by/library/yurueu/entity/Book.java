package by.library.yurueu.entity;

public class Book {
    private long id;
    private String title;
    private int pagesNumber;
    private String imagePath;

    public Book(long id, String title, int pagesNumber, String imagePath) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

        if(getTitle() == null) {
            if(aThat.getTitle() != null) {return false;}
        } else if(!getTitle().equals(aThat.getTitle())) { return false;}

        if(getPagesNumber() != aThat.getPagesNumber()) {return false;}

        if(getImagePath() == null) {
            if(aThat.getImagePath() != null) {return false;}
        } else if(!getImagePath().equals(aThat.getImagePath())) { return false;}

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (title != null ? title.hashCode() : 0);
        result = prime * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = prime * result + pagesNumber;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{title=").append(title)
                .append(", pagesNumber=").append(pagesNumber)
                .append(", imagePath=").append(imagePath)
                .append("}");
        return  sb.toString();
    }
}
