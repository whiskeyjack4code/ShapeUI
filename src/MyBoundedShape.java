import java.awt.*;

public abstract class MyBoundedShape extends MyShape {

    private boolean isFilled;

    public MyBoundedShape(int x1, int y1, int x2, int y2, Color color, boolean isFilled) {
        super(x1, y1, x2, y2, color);
        setFilled(isFilled);
    }

    public int getUpperLeftX(){
        return Math.min(getX1(), getX2());
    }

    public int getUpperLeftY(){
        return Math.min(getY1(), getY2());
    }

    public int getWidth() {
        return Math.abs(getX2() - getX1());
    }

    public int getHeight() {
        return Math.abs(getY2() - getY1());
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    @Override
    public abstract void draw(Graphics g);
}
