import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class ShapePanel extends JPanel {
    private final ArrayList<MyShape> shapes = new ArrayList<>();
    private Color shapeColor;
    private MyShape currentShape;
    private MouseStatusListener statusListener;

    private int shapeType;
    private boolean shapeIsFilled;
    private int lineCount, squareCount, ovalCount, triangleCount;

    public ShapePanel(){
        setBackground(Color.WHITE);
        setIsFilled(true);
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

            switch (shapeType){
                case 0: currentShape = new Line(x1, y1, x1, y1, shapeColor); lineCount++; break;
                case 1: currentShape = new Square(x1, y1, x1, y1, shapeColor, shapeIsFilled); squareCount++; break;
                case 2: currentShape = new Oval(x1, y1, x1, y1, shapeColor, shapeIsFilled); ovalCount++; break;
                case 3: currentShape = new Triangle(x1, y1, x1, y1, shapeColor, shapeIsFilled); triangleCount++; break;
                default: currentShape = null;
            }
            repaint();
            if(statusListener != null){
                statusListener.updateStatus(e.getX(), e.getY(), getShapeCounts());
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(currentShape != null){
                currentShape.setX2(e.getX());
                currentShape.setY2(e.getY());
                repaint();
            }
            if(statusListener != null){
                statusListener.updateStatus(e.getX(), e.getY(), getShapeCounts());
            }
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
            if(statusListener != null){
                statusListener.updateStatus(e.getX(), e.getY(), getShapeCounts());
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(statusListener != null){
                statusListener.updateStatus(e.getX(), e.getY(), getShapeCounts());
            }
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

            if(statusListener != null) {
                statusListener.updateStatus(-1, -1, getShapeCounts());
            }
        }
    }

    public void clearPanel() {
        if(!shapes.isEmpty()){
            shapes.clear();
            resetShapeCounters();
            repaint();

            if(statusListener != null) {
                statusListener.updateStatus(-1, -1, getShapeCounts());
            }
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

    public void setShapeType(int selectedShapeIndex) {
        this.shapeType = selectedShapeIndex;
    }

    public void setShapeColor(Color selectedColor) {
        this.shapeColor = selectedColor;
    }

    public void setIsFilled(boolean shapeIsFilled){
        this.shapeIsFilled = shapeIsFilled;
    }

    public void setMouseStatusListener(MouseStatusListener listener){
        this.statusListener = listener;
    }

    public interface MouseStatusListener {
        void updateStatus(int x, int y, String shapeCounts);
    }

}