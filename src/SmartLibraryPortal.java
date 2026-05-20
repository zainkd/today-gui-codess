import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class EmptyFieldException extends Exception {
    public EmptyFieldException(String message) {
        super(message);
    }
}

class InvalidRollNumberException extends Exception {
    public InvalidRollNumberException(String message) {
        super(message);
    }
}

class InvalidDateException extends Exception {
    public InvalidDateException(String message) {
        super(message);
    }
}

class NullSelectionException extends Exception {
    public NullSelectionException(String message) {
        super(message);
    }
}

public class SmartLibraryPortal extends JFrame implements ActionListener {

    private JLabel heading, lblName, lblRoll, lblBookTitle,
            lblCategory, lblIssueDate, lblReturnDate,
            lblRemarks, lblBookType;

    private JTextField txtName, txtRoll, txtBookTitle,
            txtIssueDate, txtReturnDate;

    private JTextArea areaRemarks;

    private JComboBox<String> categoryBox;

    private JRadioButton rbNew, rbOld;

    private ButtonGroup bookTypeGroup;

    private JButton btnIssue, btnReset, btnExit;

    private final Color bgColor = new Color(245, 248, 255);

    public SmartLibraryPortal() {
        setTitle("University of Central Punjab - Library Issue System");
        setSize(760, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(bgColor);

        heading = new JLabel("Library Book Issue System");
        heading.setBounds(160, 20, 450, 45);
        heading.setFont(new Font("Verdana", Font.BOLD, 28));
        heading.setForeground(new Color(0, 102, 153));
        add(heading);

        Font labelFont = new Font("Tahoma", Font.BOLD, 16);

        lblName = new JLabel("Student Name");
        lblName.setBounds(70, 100, 180, 30);
        lblName.setFont(labelFont);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(280, 100, 360, 35);
        add(txtName);

        lblRoll = new JLabel("Roll Number");
        lblRoll.setBounds(70, 155, 180, 30);
        lblRoll.setFont(labelFont);
        add(lblRoll);

        txtRoll = new JTextField();
        txtRoll.setBounds(280, 155, 360, 35);
        add(txtRoll);

        lblBookTitle = new JLabel("Book Title");
        lblBookTitle.setBounds(70, 210, 180, 30);
        lblBookTitle.setFont(labelFont);
        add(lblBookTitle);

        txtBookTitle = new JTextField();
        txtBookTitle.setBounds(280, 210, 360, 35);
        add(txtBookTitle);

        lblCategory = new JLabel("Book Category");
        lblCategory.setBounds(70, 265, 180, 30);
        lblCategory.setFont(labelFont);
        add(lblCategory);

        String[] categories = {
                "-- Select Category --",
                "Programming",
                "Artificial Intelligence",
                "Databases",
                "Networking"
        };

        categoryBox = new JComboBox<>(categories);
        categoryBox.setBounds(280, 265, 360, 35);
        add(categoryBox);

        lblBookType = new JLabel("Book Type");
        lblBookType.setBounds(70, 320, 180, 30);
        lblBookType.setFont(labelFont);
        add(lblBookType);

        rbNew = new JRadioButton("New Edition");
        rbNew.setBounds(280, 320, 160, 30);
        rbNew.setBackground(bgColor);

        rbOld = new JRadioButton("Old Edition");
        rbOld.setBounds(460, 320, 180, 30);
        rbOld.setBackground(bgColor);

        bookTypeGroup = new ButtonGroup();
        bookTypeGroup.add(rbNew);
        bookTypeGroup.add(rbOld);

        add(rbNew);
        add(rbOld);

        lblIssueDate = new JLabel("Issue Date");
        lblIssueDate.setBounds(70, 375, 180, 30);
        lblIssueDate.setFont(labelFont);
        add(lblIssueDate);

        txtIssueDate = new JTextField("dd/MM/yyyy");
        txtIssueDate.setBounds(280, 375, 360, 35);
        add(txtIssueDate);

        lblReturnDate = new JLabel("Return Date");
        lblReturnDate.setBounds(70, 430, 180, 30);
        lblReturnDate.setFont(labelFont);
        add(lblReturnDate);

        txtReturnDate = new JTextField("dd/MM/yyyy");
        txtReturnDate.setBounds(280, 430, 360, 35);
        add(txtReturnDate);

        lblRemarks = new JLabel("Remarks (optional)");
        lblRemarks.setBounds(70, 485, 180, 30);
        lblRemarks.setFont(labelFont);
        add(lblRemarks);

        areaRemarks = new JTextArea();
        JScrollPane remarksPane = new JScrollPane(areaRemarks);
        remarksPane.setBounds(280, 485, 360, 90);
        add(remarksPane);

        btnIssue = new JButton("Issue Book");
        btnIssue.setBounds(90, 610, 170, 45);
        btnIssue.setBackground(new Color(0, 153, 76));
        btnIssue.setForeground(Color.WHITE);
        btnIssue.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIssue.addActionListener(this);
        add(btnIssue);

        btnReset = new JButton("Reset");
        btnReset.setBounds(290, 610, 170, 45);
        btnReset.setBackground(new Color(0, 102, 204));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnReset.addActionListener(this);
        add(btnReset);

        btnExit = new JButton("Exit");
        btnExit.setBounds(490, 610, 170, 45);
        btnExit.setBackground(new Color(204, 0, 0));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnExit.addActionListener(this);
        add(btnExit);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnIssue) {
            try {
                String name = txtName.getText().trim();
                String roll = txtRoll.getText().trim();
                String bookTitle = txtBookTitle.getText().trim();
                String issue = txtIssueDate.getText().trim();
                String returnDate = txtReturnDate.getText().trim();

                if (name.isEmpty() || roll.isEmpty() || bookTitle.isEmpty() || issue.isEmpty() || returnDate.isEmpty()) {
                    throw new EmptyFieldException("All required fields must be filled.");
                }

                if (!roll.matches("[0-9]+")) {
                    throw new InvalidRollNumberException("Roll Number must contain digits only.");
                }

                int rollNumber = Integer.parseInt(roll);
                if (rollNumber <= 0) {
                    throw new InvalidRollNumberException("Roll Number is not valid.");
                }

                if (categoryBox.getSelectedIndex() == 0) {
                    throw new NullSelectionException("Please select a book category.");
                }

                if (!rbNew.isSelected() && !rbOld.isSelected()) {
                    throw new NullSelectionException("Please select the book type.");
                }

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                format.setLenient(false);

                Date issueDate = format.parse(issue);
                Date returnDateObj = format.parse(returnDate);

                if (returnDateObj.before(issueDate)) {
                    throw new InvalidDateException("Return Date cannot be earlier than Issue Date.");
                }

                String bookType = rbNew.isSelected() ? "New Edition" : "Old Edition";

                JOptionPane.showMessageDialog(this,
                        "Book issued successfully!\n" +
                                "Student: " + name + "\n" +
                                "Roll Number: " + roll + "\n" +
                                "Title: " + bookTitle + "\n" +
                                "Category: " + categoryBox.getSelectedItem() + "\n" +
                                "Type: " + bookType,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (EmptyFieldException | InvalidRollNumberException | NullSelectionException | InvalidDateException ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Roll Number must be numeric.",
                        "Number Format Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this,
                        "Dates must be entered in dd/MM/yyyy format.",
                        "Date Format Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Unexpected error: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                JOptionPane.showMessageDialog(this,
                        "Operation Completed",
                        "Status",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (event.getSource() == btnReset) {
            txtName.setText("");
            txtRoll.setText("");
            txtBookTitle.setText("");
            txtIssueDate.setText("dd/MM/yyyy");
            txtReturnDate.setText("dd/MM/yyyy");
            areaRemarks.setText("");
            categoryBox.setSelectedIndex(0);
            bookTypeGroup.clearSelection();
        }

        if (event.getSource() == btnExit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SmartLibraryPortal::new);
    }
}
