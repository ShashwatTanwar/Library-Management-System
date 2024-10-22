import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LibraryManagementSystem extends JFrame {
    private JTextField titleField, authorField, isbnField, yearField, searchField;
    private JComboBox<String> genreBox;
    private JCheckBox availabilityCheckBox;
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public LibraryManagementSystem() {
        // Set up the main frame
        setTitle("Library Management System");
        setSize(900, 700);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu Bar setup
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // Tool Bar setup
        JToolBar toolBar = new JToolBar();
        JButton addBookButton = new JButton("Add Book");
        JButton removeBookButton = new JButton("Remove Book");
        JButton searchButton = new JButton("Search");
        toolBar.add(addBookButton);
        toolBar.add(removeBookButton);
        toolBar.add(searchButton);
        add(toolBar, BorderLayout.NORTH);

        // Tabbed Pane setup
        JTabbedPane tabbedPane = new JTabbedPane();

        // Book Details Panel using GridBagLayout for more control over positioning
        JPanel bookDetailsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        titleField = new JTextField(15);
        authorField = new JTextField(15);
        isbnField = new JTextField(15);
        yearField = new JTextField(15);
        genreBox = new JComboBox<>(new String[]{"Fiction", "Non-fiction", "Science", "History", "Biography"});
        availabilityCheckBox = new JCheckBox("Available");

        // Add labels and fields systematically using GridBagConstraints
        bookDetailsPanel.add(new JLabel("Title:"), gbc);
        gbc.gridx++;
        bookDetailsPanel.add(titleField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        bookDetailsPanel.add(new JLabel("Author:"), gbc);
        gbc.gridx++;
        bookDetailsPanel.add(authorField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        bookDetailsPanel.add(new JLabel("ISBN:"), gbc);
        gbc.gridx++;
        bookDetailsPanel.add(isbnField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        bookDetailsPanel.add(new JLabel("Year:"), gbc);
        gbc.gridx++;
        bookDetailsPanel.add(yearField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        bookDetailsPanel.add(new JLabel("Genre:"), gbc);
        gbc.gridx++;
        bookDetailsPanel.add(genreBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        bookDetailsPanel.add(new JLabel("Availability:"), gbc);
        gbc.gridx++;
        bookDetailsPanel.add(availabilityCheckBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        // Add action buttons below the fields
        JButton addBook = new JButton("Add Book");
        JButton updateBook = new JButton("Update Book");
        gbc.gridwidth = 1; // Reset to default column span
        bookDetailsPanel.add(addBook, gbc);
        gbc.gridx++;
        bookDetailsPanel.add(updateBook, gbc);

        tabbedPane.add("Book Details", bookDetailsPanel);

        // Book List Panel using BorderLayout
        JPanel bookListPanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new Object[]{"Title", "Author", "ISBN", "Genre", "Available"}, 0);
        bookTable = new JTable(tableModel);
        bookListPanel.add(new JScrollPane(bookTable), BorderLayout.CENTER);

        // Search Panel setup for book list panel
        searchField = new JTextField();
        JButton searchTableButton = new JButton("Search");
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchTableButton, BorderLayout.EAST);

        bookListPanel.add(searchPanel, BorderLayout.NORTH);

        tabbedPane.add("Book List", bookListPanel);
        add(tabbedPane, BorderLayout.CENTER);

        // Event Listeners
        addBook.addActionListener(e -> addBookToTable());
        removeBookButton.addActionListener(e -> deleteBook());
        searchTableButton.addActionListener(e -> searchBooks());

        setVisible(true);
    }

    private void addBookToTable() {
        String title = titleField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();
        String year = yearField.getText();
        String genre = (String) genreBox.getSelectedItem();
        boolean available = availabilityCheckBox.isSelected();

        if (!title.isEmpty() && !author.isEmpty() && !isbn.isEmpty()) {
            tableModel.addRow(new Object[]{title, author, isbn, genre, available ? "Yes" : "No"});
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a book to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected book?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
            }
        }
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
        yearField.setText("");
        genreBox.setSelectedIndex(0);
        availabilityCheckBox.setSelected(false);
    }

    private void searchBooks() {
        String searchQuery = searchField.getText().toLowerCase();
        for (int i = 0; i < bookTable.getRowCount(); i++) {
            String title = bookTable.getValueAt(i, 0).toString().toLowerCase();
            if (title.contains(searchQuery)) {
                bookTable.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        new LibraryManagementSystem();
    }
}
