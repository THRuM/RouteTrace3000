package model.event;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by user on 27.07.17.
 * Klasa reprezentująca adres dla geodekodowania
 */
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_Id")
    private Integer id;

    @NotNull
    @NotEmpty(message = "{error.address}")
    @Column(name = "ad_Country")
    private String country;

    @NotNull
    @Column(name = "ad_City")
    private String city;

    @NotNull
    @Column(name = "ad_Street")
    private String street;

    @NotNull
    @Column(name = "ad_Number")
    private String number;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (!city.equals("undefined"))
            sb.append(city).append(" ");

        if (!street.equals("undefined"))
            sb.append(street).append(" ");

        if (!number.equals("undefined"))
            sb.append(number).append(" ");

        if (!country.equals("undefined"))
            sb.append(country).append(" ");

        return sb.toString();
    }
}
