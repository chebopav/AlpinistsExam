package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Группа туристов
 * Поля: Id, гора (зависимость "Многие к одному", так как несколько различных групп
 * может осуществлять поход на гору), сэт альпинистов(зависимость "Многие ко многим",
 * так как несколько альпинистов, может быть записано в несколько групп),
 * возможность приема, дата начала, продолжительность похода
 * Альпинисты добавляются методом
 * @see TouristGroup#addTourist
 */
@Entity
public class TouristGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Mountain mountain;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( name = "group_alpinist",
            joinColumns = @JoinColumn(name = "tourist_group_id"),
            inverseJoinColumns = @JoinColumn(name = "alpinist_id")
    )
    private Set<Alpinist> tourists = new HashSet<>(7);

    private boolean isAvailableReceipt;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private int duration;

    public TouristGroup() {
    }

    public TouristGroup(Mountain mountain, String startDate, int days) {
        setMountain(mountain);
        String[] date = startDate.split("\\.");
        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[0]);
        setStartDate(LocalDate.of(year, month, day));
        setDuration(days);
        isAvailableReceipt = true;
        mountain.getGroups().add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mountain getMountain() {
        return mountain;
    }

    public void setMountain(Mountain mountain) {
        this.mountain = Objects.requireNonNull(mountain);
    }

    public Set<Alpinist> getTourists() {
        return tourists;
    }

    public void setTourists(Set<Alpinist> tourists) {
        this.tourists = Objects.requireNonNull(tourists);
    }

    public boolean isAvailableReceipt() {
        return isAvailableReceipt;
    }

    public void setAvailableReceipt(boolean availableReceipt) {
        isAvailableReceipt = availableReceipt;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Дата начала не может быть раньше сегодняшней");
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int days) {
        if (days < 1)
            throw new IllegalArgumentException("Продолжительность не может быть меньше 1 дня");
        this.duration = days;
    }

    public void closeReceipt() {
        isAvailableReceipt = false;
        System.out.println( "Прием в поход на гору " + mountain.getName() + " закрыт" );
    }

    private boolean checkDates(Alpinist alpinist){
        for (TouristGroup group : alpinist.getTouristGroups()){
            if (this.startDate.isAfter(group.startDate)
                    && this.startDate.isBefore(group.startDate.plus(group.duration, ChronoUnit.DAYS))){
                return false;
            }
        }
        return true;
    }

    /**
     * Метод добавление альпиниста в группу
     * Проводится проверка даты (в сравнении с настоящей)
     * Проводится проверка открытого набора, и проверка дат
     * @see TouristGroup#checkDates
     * для того, чтобы клиент не был в двух группах в одно время
     * @param alpinist Добавляемый альпинист
     */
    public void addTourist(Alpinist alpinist){
        if (startDate.isAfter(LocalDate.now())) {
            if (isAvailableReceipt && checkDates(Objects.requireNonNull(alpinist))) {
                tourists.add(alpinist);
                alpinist.getTouristGroups().add(this);
                if (tourists.size() >= 7) closeReceipt();
            } else System.out.println("Приема в группу нет, добавить альпиниста " + alpinist.getName() + " не возможно");
        } else {
            System.out.println("Группа уже ушла");
            closeReceipt();
        }
    }

    @Override
    public String toString() {
        return "TouristGroup{" +
                "id=" + id +
                ", isAvailableReceipt=" + isAvailableReceipt +
                ", startDate=" + startDate +
                ", duration=" + duration +
                '}';
    }
}
