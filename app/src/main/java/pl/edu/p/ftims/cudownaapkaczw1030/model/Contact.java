package pl.edu.p.ftims.cudownaapkaczw1030.model;

public class Contact {

    String firstName;
    double kwota;
    String phone;

    public Contact() {
    }

    public Contact(String firstName, double kwota, String phone) {
        this.firstName = firstName;
        this.kwota = kwota;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = kwota;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
