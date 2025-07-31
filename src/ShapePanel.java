import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class ShapePanel extends JPanel {
    private final ArrayList<MyShape> shapes = new ArrayList<>();
    private JLabel statusLabel;
    private Color shapeColor;
    private MyShape currentShape;

    private int shapeType;
    private boolean shapeIsFilled;
    private int lineCount, squareCount, ovalCount, triangleCount;

    public ShapePanel(JLabel statusLabel){
        setBackground(Color.WHITE);
        setIsFilled(true);
        this.statusLabel = statusLabel;
        this.shapeType = 0;
        this.shapeColor = Color.BLACK;
        currentShape = null;
        lineCount = 0;
        squareCount = 0;
        ovalCount = 0;
        triangleCount = 0;

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
                case 0: currentShape = new Line(x1, y1, x2, y2, shapeColor); lineCount++; break;
                case 1: currentShape = new Square(x1, y1, x2, y2, shapeColor, shapeIsFilled); squareCount++; break;
                case 2: currentShape = new Oval(x1, y1, x2, y2, shapeColor, shapeIsFilled); ovalCount++; break;
                case 3: currentShape = new Triangle(x1, y1, x2, y2, shapeColor, shapeIsFilled); triangleCount++; break;
                default: currentShape = null;
            }
            repaint();
            setLabelText(e.getX(), e.getY());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(currentShape != null){
                currentShape.setX2(e.getX());
                currentShape.setY2(e.getY());
                repaint();
            }
            setLabelText(e.getX(), e.getY());
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
            setLabelText(e.getX(), e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            setLabelText(e.getX(), e.getY());
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

    public void clearLastShape() {
        if(!shapes.isEmpty()){
            MyShape shapeRemoved = shapes.get(shapes.size() - 1);
            shapes.remove(shapes.size() - 1);

            if(shapeRemoved instanceof Line){
                lineCount--;
            }else if(shapeRemoved instanceof Square){
                squareCount--;
            }else if(shapeRemoved instanceof Oval){
                ovalCount--;
            }else if(shapeRemoved instanceof Triangle){
                triangleCount--;
            }
            repaint();
        }
    }

    public void clearPanel() {
        if(!shapes.isEmpty()){
            shapes.clear();
            resetShapeCounters();
            repaint();
        }
    }

    private void resetShapeCounters(){
        lineCount = 0;
        squareCount = 0;
        ovalCount = 0;
        triangleCount = 0;
    }

    public String getShapeCounts(){
        return String.format("Lines: %d, Squares: %d, Ovals: %d, Triangles: %d",
                lineCount, squareCount, ovalCount, triangleCount);
    }

    private void setLabelText(int x,int y){
        String textToShow = String.format("Mouse: (%d, %d) | %s",
                x, y, getShapeCounts());
        this.statusLabel.setText(textToShow);
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