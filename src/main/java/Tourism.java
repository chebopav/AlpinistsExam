import dao.AlpinistDao;
import dao.MountainDao;
import dao.TouristGroupDao;
import entity.Alpinist;
import entity.Mountain;
import entity.TouristGroup;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Основной класс в котором создаются альпинисты, горы и группы
 * После группы наполняются альпинистами и все данные загружаются в БД с использованием ДАО классов
 */
public class Tourism {
    public static void main(String[] args) {
        Alpinist ivan = new Alpinist("Ivan", 20, "Russia");
        Alpinist pavel = new Alpinist("Pavel", 27, "Russia");
        Alpinist elena = new Alpinist("Elena", 32, "Russia");
        Alpinist john = new Alpinist("John", 25, "England");

        Mountain elbrus = new Mountain("Elbrus", "Russia", 5400);
        Mountain everest = new Mountain("Everest", "Nepal", 8400);
        Mountain acongaqua = new Mountain("Acongaqua", "Nepal", 1000);

        TouristGroup elbrusGroup = new TouristGroup(elbrus, "10.02.2021", 5);
        elbrusGroup.addTourist(ivan);

        TouristGroup elbrusGroup2 = new TouristGroup(elbrus, "12.02.2021", 10);
        elbrusGroup2.addTourist(ivan);

        TouristGroup everestGroup = new TouristGroup(everest, "01.03.2021", 10);
        everestGroup.addTourist(pavel);
        everestGroup.addTourist(elena);

        TouristGroup everestGroup2 = new TouristGroup(everest, "01.04.2021", 15);
        everestGroup2.addTourist(john);


        EntityManager manager = Persistence.createEntityManagerFactory("manager").createEntityManager();

        AlpinistDao alpinistDao = new AlpinistDao(manager);
        MountainDao mountainDao = new MountainDao(manager);
        TouristGroupDao touristGroupDao = new TouristGroupDao(manager);

        manager.getTransaction().begin();
        mountainDao.add(elbrus);
        mountainDao.add(everest);
        mountainDao.add(acongaqua);

        alpinistDao.add(john);
        alpinistDao.add(ivan);
        alpinistDao.add(pavel);
        alpinistDao.add(elena);

        touristGroupDao.add(elbrusGroup);
        touristGroupDao.add(everestGroup);
        touristGroupDao.add(everestGroup2);

        manager.getTransaction().commit();

        System.out.println(alpinistDao.getAlpinistsByAgeFromTo(18, 27));
        System.out.println(touristGroupDao.getGroupsByMountainName("Everest"));
        System.out.println(mountainDao.getByCountryName("Nepal"));
        System.out.println(touristGroupDao.getGroupsAvailable());
    }
}
