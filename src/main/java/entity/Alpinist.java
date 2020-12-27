package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Альпинист
 * Имеет Id, имя (не менее 3-х символов), адрес(не менее 5 символов), возраст (от 18 до 80 лет)
 * и список групп, в которые включен (зависимость с группами "Многие ко многим", так как различные альпинисты
 * могут быть в различных группах)
 */
@Entity
public class Alpinist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(nullable = false)
    private int age;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tourists")
    @Column(name = "alpinist_groups")
    private List<TouristGroup> touristGroups = new ArrayList<>();

    public Alpinist() {
    }

    public Alpinist (String name, int age, String address){
        setName(name);
        setAge(age);
        setAddress(address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 3)
            throw new IllegalArgumentException( "Некорректное имя (меньше 3 символов или = null)" );
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().length() < 5)
            throw new IllegalArgumentException( "Некорректный адрес (меньше 5 символов или = null)" );
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18 || age > 80)
            throw new IllegalArgumentException("Некорректный возраст для альпиниста");
        this.age = age;
    }

    public List<TouristGroup> getTouristGroups() {
        return touristGroups;
    }

    public void setTouristGroups(List<TouristGroup> touristGroups) {
        this.touristGroups = Objects.requireNonNull(touristGroups);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Alpinist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
