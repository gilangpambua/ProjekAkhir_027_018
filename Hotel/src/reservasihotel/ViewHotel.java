package reservasihotel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class ViewHotel extends JFrame{
    String name;
    JTable tabel;
    DefaultTableModel tableModel; //otomatis dibuat kalo buat JTable
    JScrollPane scrollPane;
    Object namaKolom[] = {"NAMA","NIK","ALAMAT","JENIS KAMAR","LAMA MENGINAP","TOTAL BAYAR"}; //membuat kolom dgn array tipe object;
    final JTextField fnama = new JTextField(50);
    final JTextField fnik = new JTextField(20);
    final JTextField falamat = new JTextField(20);
    final JTextField fjeniskamar = new JTextField(10);
    final JTextField flamamenginap = new JTextField(10);
    JLabel lnama= new JLabel("Nama");
    JLabel lnik = new JLabel("NIK");
    JLabel lalamat = new JLabel("Alamat");
    JLabel ljeniskamar = new JLabel("Jenis Kamar");
    JLabel llamamenginap = new JLabel("Lama Menginap");
    JLabel lket1 = new JLabel ("Jenis Kamar Hotel : ");
    JLabel lket2 = new JLabel ("1. Standard Room = Rp.200.000/hari");
    JLabel lket3 = new JLabel ("2. Deluxe Room = Rp.275.000/hari");
    JLabel lket4 = new JLabel ("3. Suite Room = Rp.300.000/hari");
    JLabel lket5 = new JLabel ("4. Precident Room = Rp. 350.000/hari");

    JButton btnTambah = new JButton("Tambah");
    JButton btnDelete = new JButton("Delete");
    JButton btnUpdate = new JButton("Update");
    JButton btnClear = new JButton("Clear");
    JButton btnBack = new JButton("Menu");
    
    public ViewHotel(){
        tableModel = new DefaultTableModel(namaKolom,0);
        tabel = new JTable(tableModel);
        scrollPane = new JScrollPane(tabel);
        setLayout(null);
        setSize(1050,650);
        setVisible(true);
        setResizable(false);
        setTitle("MENU UTAMA");
        setDefaultCloseOperation(3);
  
        add(scrollPane);
        add(lnama);
        add(fnama);
        add(lnik);
        add(fnik);
        add(lalamat);
        add(falamat);
        add(ljeniskamar);
        add(fjeniskamar);
        add(llamamenginap);
        add(flamamenginap);
        add(lket1);add(lket2);add(lket3);add(lket4);add(lket5);
        
        add(btnTambah);
        add(btnUpdate);
        add(btnDelete);
        add(btnClear);
        add(btnBack);

        scrollPane.setBounds(20, 55, 700, 345);       
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        lnama.setBounds(750, 40, 120, 20);
        fnama.setBounds(750, 60, 170, 20);
        lnik.setBounds(750, 90, 120, 20);
        fnik.setBounds(750, 115, 170, 20);
        lalamat.setBounds(750, 145, 120, 20);
        falamat.setBounds(750, 170, 170, 20);
        ljeniskamar.setBounds(750, 200, 120, 20);
        fjeniskamar.setBounds(750, 225, 170, 20);
        llamamenginap.setBounds(750, 255, 120, 20);
        flamamenginap.setBounds(750, 280, 170, 20);
        lket1.setBounds(20, 320, 1000, 200);
        lket2.setBounds(20, 340, 1000, 200);
        lket3.setBounds(20, 360, 1000, 200);
        lket4.setBounds(20, 380, 1000, 200);
        lket5.setBounds(20, 400, 1000, 200);
        
        btnTambah.setBounds(750, 315, 80, 20);        
        btnDelete.setBounds(850, 315, 80, 20);
        btnUpdate.setBounds(750, 345, 80, 20);
        btnClear.setBounds(850, 345, 80, 20);
        btnBack.setBounds(20, 20, 80, 20);
        
        tabel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                JTable target = (JTable)me.getSource();
                int baris = target.getSelectedRow(); // select a row
                name =tabel.getValueAt(baris, 0).toString();
                fnama.setText(tabel.getValueAt(baris, 0).toString());
                fnik.setText(tabel.getValueAt(baris, 1).toString());
                falamat.setText( tabel.getValueAt(baris, 2).toString());
                fjeniskamar.setText(tabel.getValueAt(baris, 3).toString());
                flamamenginap.setText(tabel.getValueAt(baris, 4).toString());         
            }
        });
    }

    public JTextField getFnama() {
        return fnama;
    }

    public JTextField getFnik() {
        return fnik;
    }

    public JTextField getFalamat() {
        return falamat;
    }

    public JTextField getFjeniskamar() {
        return fjeniskamar;
    }

    public JTextField getFlamamenginap() {
        return flamamenginap;
    }
    
}
