import javax.swing.*;
import java.awt.*;

public class ShapeFrame extends JFrame {

    private Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.PINK, Color.ORANGE, Color.CYAN, Color.BLACK};
    private ShapePanel shapePanel;
    private JButton undoButton, clearButton;

    public ShapeFrame(){
        super("Shape UI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        JLabel statusLabel = new JLabel();
        JPanel bottomPanel = new JPanel();
        shapePanel = new ShapePanel(statusLabel);
        undoButton = new JButton("Undo"); // Add listener later
        clearButton = new JButton("Clear"); // Add listener later

        String[] colorNames = {"Blue", "Red", "Green", "Pink", "Orange", "Cyan", "Black"};
        String[] shapeNames = {"Line", "Rectangle", "Oval", "Triangle"};

        bottomPanel.add(undoButton);
        bottomPanel.add(clearButton);

        JComboBox<String> colorList = new JComboBox<>(colorNames); // Add listener later
        JComboBox<String> shapeList = new JComboBox<>(shapeNames); // Add listener later

        colorList.setSelectedIndex(0);
        shapeList.setSelectedIndex(0);

        bottomPanel.add(colorList);
        bottomPanel.add(shapeList);

        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(shapePanel, BorderLayout.CENTER);
        this.add(statusLabel, BorderLayout.NORTH);
    }
}
