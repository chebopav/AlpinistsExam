package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Гора
 * Поля: Id, название (уникальное), страна, высота,
 * список групп на эту гору (зависимость "Одна ко многим",
 * так как на одну гору может быть записано несколько групп)
 */
@Entity
public class Mountain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 50, nullable = false)
    private String country;

    @Column(nullable = false)
    private int height;

    @OneToMany(mappedBy = "mountain", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TouristGroup> groups = new ArrayList<>();

    public Mountain() {
    }

    public Mountain (String name, String country, int height){
        setName(name);
        setCountry(country);
        setHeight(height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 4)
            throw new IllegalArgumentException( "Некорректное имя (меньше 4 символов или = null)" );
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null || country.trim().length() < 4)
            throw new IllegalArgumentException( "Некорректное название страны (меньше 4 символов или = null)" );
        this.country = country;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < 100)
            throw new IllegalArgumentException( "Некорректная высота (меньше 100 метров)" );
        this.height = height;
    }

    public List<TouristGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<TouristGroup> groups) {
        this.groups = Objects.requireNonNull(groups);
    }

    @Override
    public String toString() {
        return "Mountain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", height=" + height +
                '}';
    }
}
