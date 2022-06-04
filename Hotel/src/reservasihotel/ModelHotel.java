package reservasihotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ModelHotel {
    Connector connector = new Connector();
    public int getBanyakData(){
        int jmlData=0;
        try{
            String query = "Select * from hotel"; 
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query); //result isinya tabel belum berupa string
            while(resultSet.next()){ //menghitung banyak baris yang ada di database
                jmlData++; 
            }
            connector.statement.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
        return jmlData;
    }
    
    public String[][] readData(){ //dua dimensi baris-kolom
        try{
            int jmlData = 0; //menampung jumlah data sebanyak jumlah data yang ada, defaultnya
            String data[][] = new String[getBanyakData()][6]; // nemampung array. barisnya isinya di getBanyakData,kolomnya itu atribut
            String query = "Select * from `hotel`"; 
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);// result isinya tabel belum berupa string
            while(resultSet.next()){ //konversi tabel ke string
                data[jmlData][0] = resultSet.getString("nama"); // Harus sesuai database
                data[jmlData][1] = resultSet.getString("nik");
                data[jmlData][2] = resultSet.getString("alamat");
                data[jmlData][3] = resultSet.getString("jeniskamar"); // Harus sesuai database
                data[jmlData][4] = resultSet.getString("menginap"); // Harus sesuai database  
                data[jmlData][5] = resultSet.getString("totalbayar");           
                jmlData++; //barisnya berpindah terus sampai habis        
            }
            
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }   
    
    public void tambahData(String nama,String nik, String alamat,int jeniskamar,int menginap, float totalbayar){        
        try {
            String query = "INSERT INTO hotel VALUES ('"+nama+"','"+nik+"','"+alamat+"','"+jeniskamar+"','"+menginap+"','"+totalbayar+"')";  
            connector.statement = connector.koneksi.createStatement();
            connector.statement.executeUpdate(query);

            JOptionPane.showMessageDialog(null,"Insert Berhasil !!");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,"Data Sudah Ada !!");
        }
    }

    public void updateData(String nama,String name,String nik, String alamat,int jeniskamar,int menginap, float totalbayar) {
        try {
            String query = "UPDATE hotel SET nama=?, "
                    + "nik=?, alamat=?, jeniskamar=?, menginap=? , totalbayar=? where nama=?";
            PreparedStatement preparedStmt = connector.koneksi.prepareStatement(query);
            preparedStmt.setString(1, nama);
            preparedStmt.setString(2, nik);
            preparedStmt.setString(3, alamat);
            preparedStmt.setInt(4, jeniskamar);
            preparedStmt.setDouble(5, menginap);
            preparedStmt.setFloat(6, totalbayar);
            preparedStmt.setString(7, name);
            preparedStmt.execute();
            
            if(nama.compareTo(name)==0)
                JOptionPane.showMessageDialog(null,"Update Data Berhasil");
            else
                JOptionPane.showMessageDialog(null,"Data Tidak Ada");
        } catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }
      
    public void deleteData(String nik){
        try{
            String query = "DELETE from hotel WHERE nik = '"+nik+"'";
            connector.statement = connector.koneksi.createStatement();
            connector.statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            
        }catch(SQLException sql){
            System.out.println(sql.getMessage());
        }
    }

}
