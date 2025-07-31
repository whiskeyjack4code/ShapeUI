import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeFrame extends JFrame {

    private Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.PINK, Color.ORANGE, Color.CYAN, Color.BLACK};
    private ShapePanel shapePanel;
    private JButton undoButton, clearButton;
    private JRadioButton isFilledRadioButton, isNotFilledRadioButton;
    private ButtonGroup filledButtonGroup;
    private JLabel statusLabel;

    public ShapeFrame(){
        super("Shape UI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        statusLabel = new JLabel();
        JPanel bottomPanel = new JPanel();

        shapePanel = new ShapePanel();
        undoButton = new JButton("Undo");
        clearButton = new JButton("Clear");
        filledButtonGroup = new ButtonGroup();
        statusLabel = new JLabel("Mouse: (0, 0) | " + shapePanel.getShapeCounts());

        isFilledRadioButton = new JRadioButton("Filled");
        isNotFilledRadioButton = new JRadioButton("Not Filled");

        filledButtonGroup.add(isFilledRadioButton);
        filledButtonGroup.add(isNotFilledRadioButton);
        isFilledRadioButton.setSelected(true);

        String[] colorNames = {"Blue", "Red", "Green", "Pink", "Orange", "Cyan", "Black"};
        String[] shapeNames = {"Line", "Rectangle", "Oval", "Triangle"};

        bottomPanel.add(undoButton);
        bottomPanel.add(clearButton);
        bottomPanel.add(isFilledRadioButton);
        bottomPanel.add(isNotFilledRadioButton);

        JComboBox<String> colorListComboBox = new JComboBox<>(colorNames);
        JComboBox<String> shapeListComboBox = new JComboBox<>(shapeNames);

        colorListComboBox.setSelectedIndex(0);
        shapeListComboBox.setSelectedIndex(0);

        isFilledRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapePanel.setIsFilled(true);
            }
        });

        isNotFilledRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapePanel.setIsFilled(false);
            }
        });

        shapePanel.setMouseStatusListener(new ShapePanel.MouseStatusListener() {
            @Override
            public void updateStatus(int x, int y, String shapeCounts) {
                statusLabel.setText(String.format("Mouse: (%d, %d) | %s", x, y, shapeCounts));
            }
        });

        colorListComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedColorIndex = colorListComboBox.getSelectedIndex();
                Color selectedColor;

                switch (selectedColorIndex){
                    case 0: selectedColor = Color.BLUE; break;
                    case 1: selectedColor = Color.RED; break;
                    case 2: selectedColor = Color.GREEN; break;
                    case 3: selectedColor = Color.PINK; break;
                    case 4: selectedColor = Color.ORANGE; break;
                    case 5: selectedColor = Color.CYAN; break;
                    case 6: selectedColor = Color.BLACK; break;
                    default: selectedColor = Color.WHITE;
                }
                shapePanel.setShapeColor(selectedColor);
            }
        });
        shapeListComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedShapeIndex = shapeListComboBox.getSelectedIndex();
                shapePanel.setShapeType(selectedShapeIndex);
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapePanel.clearLastShape();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapePanel.clearPanel();
            }
        });

        bottomPanel.add(colorListComboBox);
        bottomPanel.add(shapeListComboBox);

        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(shapePanel, BorderLayout.CENTER);
        this.add(statusLabel, BorderLayout.NORTH);
    }
}
