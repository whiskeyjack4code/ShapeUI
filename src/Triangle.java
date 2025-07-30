import java.awt.*;

public class Triangle extends MyBoundedShape {

    public Triangle(int x1, int y1, int x2, int y2, Color color, boolean isFilled) {
        super(x1, y1, x2, y2, color, isFilled);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());

        int x1 = getX1();
        int y1 = getY1();
        int x2 = getX2();
        int y2 = getY2();

        int[] xPoints = {
                (x1 + x2) / 2, // top point (midpoint of x1 and x2)
                x1,            // bottom-left
                x2             // bottom-right
        };

        int[] yPoints = {
                y1,  // top point (y1)
                y2,  // bottom-left
                y2   // bottom-right
        };

        if(isFilled()){
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            g.drawPolygon(xPoints, yPoints, 3);
        }
    }
}
