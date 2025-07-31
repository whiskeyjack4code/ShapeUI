import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class ShapePanel extends JPanel {
    private final ArrayList<MyShape> shapes = new ArrayList<>();
    private JLabel statusLabel;
    private int shapeType;
    private Color shapeColor;
    private MyShape currentShape;
    private boolean shapeIsFilled;
    private int width;
    private int height;

    public ShapePanel(JLabel statusLabel){
        setBackground(Color.WHITE);
        this.statusLabel = statusLabel;
        this.shapeType = 0;
        this.shapeColor = Color.BLACK;
        currentShape = null;
        setIsFilled(true);
        width = 0;
        height = 0;

        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseHandler());
    }

    private class MouseHandler extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            int x1 = e.getX();
            int y1 = e.getY();
            int x2 = x1;
            int y2 = y1;

            switch (shapeType){
                case 0: currentShape = new Line(x1, y1, x2, y2, shapeColor); break;
                case 1: currentShape = new Square(x1, y1, x2, y2, shapeColor, shapeIsFilled); break;
                case 2: currentShape = new Oval(x1, y1, x2, y2, shapeColor, shapeIsFilled); break;
                case 3: currentShape = new Triangle(x1, y1, x2, y2, shapeColor, shapeIsFilled); break;
                default: currentShape = null;
            }

            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(currentShape != null){
                currentShape.setX2(e.getX());
                currentShape.setY2(e.getY());
                repaint();
            }
            width = Math.abs(currentShape.getX2() - currentShape.getX1());
            height = Math.abs(currentShape.getY2() - currentShape.getY1());

            setLabelText("X: " + e.getX() + " Y: " + e.getY() + "Width: " + width + " Height: " + height);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
           if(currentShape != null){
               currentShape.setX2(e.getX());
               currentShape.setY2(e.getY());
               shapes.add(currentShape);
               currentShape = null;
               repaint();
           }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            setLabelText("X: " + e.getX() + " Y: " + e.getY());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(MyShape shape : shapes){
            shape.draw(g);
        }

        if(currentShape != null){
            currentShape.draw(g);
        }
    }

    public void setLabelText(String value){
        this.statusLabel.setText(value);
    }

    public void setShapeType(int selectedShapeIndex) {
        this.shapeType = selectedShapeIndex;
    }

    public void setShapeColor(Color selectedColor) {
        this.shapeColor = selectedColor;
    }

    public void setIsFilled(boolean shapeIsFilled){
        this.shapeIsFilled = shapeIsFilled;
    }

}
