package by.library.yurueu.entity;

public class Genre {
    private long id;
    private String genreName;

    public Genre(long id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}

        Genre aThat = (Genre) obj;

        if(getGenreName() == null) {
            if(aThat.getGenreName() != null) {return false;}
        } else if(!getGenreName().equals(aThat.getGenreName())) { return false;}

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (genreName != null ? genreName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{genreName=").append(genreName)
                .append("}");
        return  sb.toString();
    }
}
