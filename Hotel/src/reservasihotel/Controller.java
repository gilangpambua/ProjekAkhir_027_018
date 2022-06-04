package reservasihotel;

import reservasihotel.Main.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Controller {
    ViewHotel viewHotel;
    ModelHotel modelHotel;
    Main main;
    Menu menu;
            
    Controller(ViewHotel viewHotel, ModelHotel modelHotel) {
        this.viewHotel = viewHotel;
        this.modelHotel = modelHotel;
        this.main = main;
        this.menu = menu;
        //mengecek data yang ada dari database dan memasukkan ke dalam tabel
        if(modelHotel.getBanyakData() != 0){
            String data[][] = modelHotel.readData();
            viewHotel.tabel.setModel((new JTable(data, viewHotel.namaKolom)).getModel());
        } else{
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }                       
        
        viewHotel.btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
            String nama = viewHotel.getFnama().getText();
            String nik = viewHotel.getFnik().getText();
            String alamat = viewHotel.getFalamat().getText();
            int lamamenginap = Integer.parseInt(viewHotel.getFlamamenginap().getText());
            int jeniskamar = Integer.parseInt(viewHotel.getFjeniskamar().getText());
            float totalbayar=0;
            if (jeniskamar == 1){
                totalbayar = 200000 * lamamenginap;
            }
            else if (jeniskamar == 2){
                totalbayar = 275000 * lamamenginap;
            }
            else if (jeniskamar == 3){
                totalbayar = 300000 * lamamenginap;
            }
            else if (jeniskamar == 4){
                totalbayar = 350000 * lamamenginap;
            } 
            
            if (nama.equals("") || nik.equals("")||alamat.equals("")||jeniskamar==0||lamamenginap==0) {
                JOptionPane.showMessageDialog(viewHotel, "Isi Kolom Kosong Terlebih Dahulu !");
            }else {
                modelHotel.tambahData(nama,nik,alamat,jeniskamar,lamamenginap,totalbayar);
                viewHotel.dispose();//new tab
                MVC mvc = new MVC();
                mvc.menuutama();
            }
            }
        });
        
        viewHotel.btnUpdate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { 
                String name = viewHotel.name;
                String nama = viewHotel.getFnama().getText();
                String nik = viewHotel.getFnik().getText();
                String alamat = viewHotel.getFalamat().getText();
                int lamamenginap = Integer.parseInt(viewHotel.getFlamamenginap().getText());
                int jeniskamar = Integer.parseInt(viewHotel.getFjeniskamar().getText());
                float totalbayar=0;
                if (jeniskamar == 1){
                    totalbayar = 200000 * lamamenginap;
                }
                else if (jeniskamar == 2){
                    totalbayar = 275000 * lamamenginap;
                }
                else if (jeniskamar == 3){
                    totalbayar = 300000 * lamamenginap;
                }
                else if (jeniskamar == 4){
                    totalbayar = 350000 * lamamenginap;
                } 

                if (nama.equals("") || nik.equals("")||alamat.equals("")||jeniskamar==0||lamamenginap==0) {
                    JOptionPane.showMessageDialog(viewHotel, "Isi Kolom Kosong Terlebih Dahulu !");
                }else {
                    modelHotel.updateData(name,nama,nik,alamat,jeniskamar,lamamenginap,totalbayar);
                    viewHotel.dispose();
                    MVC mvc = new MVC();
                    mvc.menuutama();
                }
            }
        });
        
        //aksi ketika menekan tombol delete
        viewHotel.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
            String nik = viewHotel.getFnik().getText();

            if (nik.equals("")) {
                JOptionPane.showMessageDialog(viewHotel, "Isi NIK Yang Akan Dihapus");
            }else {
                modelHotel.deleteData(nik);
                viewHotel.dispose();
                MVC mvc = new MVC();
                mvc.menuutama();
            }
            }
        });

        //aksi ketika menekan tombol clear
         viewHotel.btnClear.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    viewHotel.fnama.setText("");
                    viewHotel.fnik.setText("");
                    viewHotel.falamat.setText("");
                    viewHotel.fjeniskamar.setText("");
                    viewHotel.flamamenginap.setText("");
                }
         });
        
        //aksi ketika menekan tombol Menu Utama
        viewHotel.btnBack.addActionListener(new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                   viewHotel.dispose();
                   Menu menu = new Menu();
               }
         }); 
        }
        
    
}
