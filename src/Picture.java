import java.util.Objects;

public class Picture {
    public String shape;
    public String shapeColor;
    public String bColor;

    public Picture(String shape, String shapeColor, String bColor) {
        this.shape = shape;
        this.shapeColor = shapeColor;
        this.bColor = bColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return shape.equals(picture.shape) &&
                shapeColor.equals(picture.shapeColor) &&
                bColor.equals(picture.bColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, shapeColor, bColor);
    }
}
