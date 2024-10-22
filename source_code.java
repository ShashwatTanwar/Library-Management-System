// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

public class LibraryManagementSystem extends JFrame {
   private JTextField titleField;
   private JTextField authorField;
   private JTextField isbnField;
   private JTextField yearField;
   private JTextField searchField;
   private JComboBox<String> genreBox;
   private JCheckBox availabilityCheckBox;
   private JTable bookTable;
   private DefaultTableModel tableModel;

   public LibraryManagementSystem() {
      this.setTitle("Library Management System");
      this.setSize(900, 700);
      this.setLocationRelativeTo((Component)null);
      this.setDefaultCloseOperation(3);
      this.setLayout(new BorderLayout());
      JMenuBar var1 = new JMenuBar();
      JMenu var2 = new JMenu("File");
      JMenu var3 = new JMenu("Edit");
      JMenu var4 = new JMenu("Help");
      var1.add(var2);
      var1.add(var3);
      var1.add(var4);
      this.setJMenuBar(var1);
      JToolBar var5 = new JToolBar();
      JButton var6 = new JButton("Add Book");
      JButton var7 = new JButton("Remove Book");
      JButton var8 = new JButton("Search");
      var5.add(var6);
      var5.add(var7);
      var5.add(var8);
      this.add(var5, "North");
      JTabbedPane var9 = new JTabbedPane();
      JPanel var10 = new JPanel(new GridBagLayout());
      GridBagConstraints var11 = new GridBagConstraints();
      var11.insets = new Insets(5, 5, 5, 5);
      var11.fill = 2;
      var11.gridx = 0;
      var11.gridy = 0;
      this.titleField = new JTextField(15);
      this.authorField = new JTextField(15);
      this.isbnField = new JTextField(15);
      this.yearField = new JTextField(15);
      this.genreBox = new JComboBox(new String[]{"Fiction", "Non-fiction", "Science", "History", "Biography"});
      this.availabilityCheckBox = new JCheckBox("Available");
      var10.add(new JLabel("Title:"), var11);
      ++var11.gridx;
      var10.add(this.titleField, var11);
      var11.gridx = 0;
      ++var11.gridy;
      var10.add(new JLabel("Author:"), var11);
      ++var11.gridx;
      var10.add(this.authorField, var11);
      var11.gridx = 0;
      ++var11.gridy;
      var10.add(new JLabel("ISBN:"), var11);
      ++var11.gridx;
      var10.add(this.isbnField, var11);
      var11.gridx = 0;
      ++var11.gridy;
      var10.add(new JLabel("Year:"), var11);
      ++var11.gridx;
      var10.add(this.yearField, var11);
      var11.gridx = 0;
      ++var11.gridy;
      var10.add(new JLabel("Genre:"), var11);
      ++var11.gridx;
      var10.add(this.genreBox, var11);
      var11.gridx = 0;
      ++var11.gridy;
      var10.add(new JLabel("Availability:"), var11);
      ++var11.gridx;
      var10.add(this.availabilityCheckBox, var11);
      var11.gridx = 0;
      ++var11.gridy;
      JButton var12 = new JButton("Add Book");
      JButton var13 = new JButton("Update Book");
      var11.gridwidth = 1;
      var10.add(var12, var11);
      ++var11.gridx;
      var10.add(var13, var11);
      var9.add("Book Details", var10);
      JPanel var14 = new JPanel(new BorderLayout());
      this.tableModel = new DefaultTableModel(new Object[]{"Title", "Author", "ISBN", "Genre", "Available"}, 0);
      this.bookTable = new JTable(this.tableModel);
      var14.add(new JScrollPane(this.bookTable), "Center");
      this.searchField = new JTextField();
      JButton var15 = new JButton("Search");
      JPanel var16 = new JPanel(new BorderLayout());
      var16.add(this.searchField, "Center");
      var16.add(var15, "East");
      var14.add(var16, "North");
      var9.add("Book List", var14);
      this.add(var9, "Center");
      var12.addActionListener((var1x) -> {
         this.addBookToTable();
      });
      var7.addActionListener((var1x) -> {
         this.deleteBook();
      });
      var15.addActionListener((var1x) -> {
         this.searchBooks();
      });
      this.setVisible(true);
   }

   private void addBookToTable() {
      String var1 = this.titleField.getText();
      String var2 = this.authorField.getText();
      String var3 = this.isbnField.getText();
      String var4 = this.yearField.getText();
      String var5 = (String)this.genreBox.getSelectedItem();
      boolean var6 = this.availabilityCheckBox.isSelected();
      if (!var1.isEmpty() && !var2.isEmpty() && !var3.isEmpty()) {
         this.tableModel.addRow(new Object[]{var1, var2, var3, var5, var6 ? "Yes" : "No"});
         this.clearFields();
      } else {
         JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Error", 0);
      }

   }

   private void deleteBook() {
      int var1 = this.bookTable.getSelectedRow();
      if (var1 == -1) {
         JOptionPane.showMessageDialog(this, "Please select a book to delete.", "Error", 0);
      } else {
         int var2 = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected book?", "Confirm Deletion", 0);
         if (var2 == 0) {
            this.tableModel.removeRow(var1);
         }
      }

   }

   private void clearFields() {
      this.titleField.setText("");
      this.authorField.setText("");
      this.isbnField.setText("");
      this.yearField.setText("");
      this.genreBox.setSelectedIndex(0);
      this.availabilityCheckBox.setSelected(false);
   }

   private void searchBooks() {
      String var1 = this.searchField.getText().toLowerCase();

      for(int var2 = 0; var2 < this.bookTable.getRowCount(); ++var2) {
         String var3 = this.bookTable.getValueAt(var2, 0).toString().toLowerCase();
         if (var3.contains(var1)) {
            this.bookTable.setRowSelectionInterval(var2, var2);
            break;
         }
      }

   }

   public static void main(String[] var0) {
      new LibraryManagementSystem();
   }
}
