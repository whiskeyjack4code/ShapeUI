import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ShapeFrame extends JFrame {

    private Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.PINK, Color.ORANGE, Color.CYAN, Color.BLACK};
    private ShapePanel shapePanel;
    private JButton undoButton, clearButton, saveButton;
    private JRadioButton isFilledRadioButton, isNotFilledRadioButton;
    private ButtonGroup filledButtonGroup;
    private JLabel coordLabel, countLabel;

    public ShapeFrame(){
        super("Shape UI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,800);
        this.setLayout(new BorderLayout());

        shapePanel = new ShapePanel();
        coordLabel = new JLabel("Mouse: (0, 0)");
        countLabel = new JLabel(shapePanel.getShapeCounts());

        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.add(coordLabel, BorderLayout.WEST);
        statusPanel.add(countLabel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();

        undoButton = new JButton("Undo");
        clearButton = new JButton("Clear");
        saveButton = new JButton("Save");

        isFilledRadioButton = new JRadioButton("Filled");
        isNotFilledRadioButton = new JRadioButton("Not Filled");

        filledButtonGroup = new ButtonGroup();
        filledButtonGroup.add(isFilledRadioButton);
        filledButtonGroup.add(isNotFilledRadioButton);
        isFilledRadioButton.setSelected(true);

        String[] colorNames = {"Blue", "Red", "Green", "Pink", "Orange", "Cyan", "Black"};
        String[] shapeNames = {"Line", "Rectangle", "Oval", "Triangle"};

        JComboBox<String> colorListComboBox = new JComboBox<>(colorNames);
        JComboBox<String> shapeListComboBox = new JComboBox<>(shapeNames);
        colorListComboBox.setSelectedIndex(0);
        shapeListComboBox.setSelectedIndex(0);

        isFilledRadioButton.addActionListener(e -> shapePanel.setIsFilled(true));
        isNotFilledRadioButton.addActionListener(e -> shapePanel.setIsFilled(false));

        saveButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Drawing As Image");
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (!selectedFile.getName().toLowerCase().endsWith(".png")) {
                    selectedFile = new File(selectedFile.getAbsolutePath() + ".png");
                }
                shapePanel.saveImage(selectedFile);
            }
        });

        colorListComboBox.addActionListener(e -> {
            int selectedColorIndex = colorListComboBox.getSelectedIndex();
            Color selectedColor = switch (selectedColorIndex) {
                case 0 -> Color.BLUE;
                case 1 -> Color.RED;
                case 2 -> Color.GREEN;
                case 3 -> Color.PINK;
                case 4 -> Color.ORANGE;
                case 5 -> Color.CYAN;
                case 6 -> Color.BLACK;
                default -> Color.WHITE;
            };
            shapePanel.setShapeColor(selectedColor);
        });

        shapeListComboBox.addActionListener(e ->
                shapePanel.setShapeType(shapeListComboBox.getSelectedIndex())
        );

        undoButton.addActionListener(e -> shapePanel.clearLastShape());
        clearButton.addActionListener(e -> shapePanel.clearPanel());

        shapePanel.setMouseStatusListener(new ShapePanel.MouseStatusListener() {
            @Override
            public void updateStatus(int x, int y, String shapeCounts) {
                coordLabel.setText(String.format("Mouse: (%d, %d)", x, y));
                countLabel.setText(shapeCounts);
            }
        });

        bottomPanel.add(saveButton);
        bottomPanel.add(undoButton);
        bottomPanel.add(clearButton);
        bottomPanel.add(isFilledRadioButton);
        bottomPanel.add(isNotFilledRadioButton);
        bottomPanel.add(colorListComboBox);
        bottomPanel.add(shapeListComboBox);

        this.add(statusPanel, BorderLayout.NORTH);
        this.add(shapePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
