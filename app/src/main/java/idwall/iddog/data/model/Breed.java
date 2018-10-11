package idwall.iddog.data.model;

public class Breed {
    private String name;
    private int imageRes;

    public Breed(String name, int imageRes) {
        this.name = name;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public int getImageRes() {
        return imageRes;
    }
}
