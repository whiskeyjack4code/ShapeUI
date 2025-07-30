import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ShapePanel extends JPanel {
    private final ArrayList<MyShape> shapes = new ArrayList<>();
    private JLabel statusLabel;

    private Random rand = new Random();

    public ShapePanel(JLabel statusLabel){
        setBackground(Color.WHITE);

        this.statusLabel = statusLabel;

        for(int i = 0; i < 10; i++){
            int x1 = rand.nextInt(500);
            int y1 = rand.nextInt(400);
            int x2 = rand.nextInt(500);
            int y2 = rand.nextInt(400);

            boolean isFilled = rand.nextInt(2) == 0;
            Color color = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));

            int shapeType = rand.nextInt(4);
            switch (shapeType){
                case 0: shapes.add(new Line(x1, y1, x2, y2, color));
                case 1: shapes.add(new Oval(x1, y1, x2, y2, color, isFilled));
                case 2: shapes.add(new Square(x1, y1, x2, y2, color, isFilled));
                case 3: shapes.add(new Triangle(x1, y1, x2, y2, color, isFilled));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(MyShape shape : shapes){
            shape.draw(g);
        }
    }
}
