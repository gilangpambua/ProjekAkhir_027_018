package reservasihotel;

public class MVC {
    public void menuutama(){
        ViewHotel viewHotel = new ViewHotel();
        ModelHotel modelHotel = new ModelHotel();
        Controller controller = new Controller(viewHotel, modelHotel);
    }
}
