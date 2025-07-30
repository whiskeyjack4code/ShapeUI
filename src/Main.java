import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame myFrame = new JFrame("Shapes UI");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(550,450);

        ShapePanel shapePanel = new ShapePanel();
        myFrame.add(shapePanel);
        myFrame.setVisible(true);

    }
}