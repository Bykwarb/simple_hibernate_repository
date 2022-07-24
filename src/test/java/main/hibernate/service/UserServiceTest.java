package main.hibernate.service;

import main.hibernate.entity.LoginDataEntity;
import main.hibernate.entity.UserEntity;
import main.hibernate.utils.HibernateSessionFactoryUtil;
import main.hibernate.utils.UserEntityBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService = new UserService(new UserDaoImpl());

    private static SessionFactory sessionFactory = null;
    private Session session = null;

    private List<UserEntity> userEntities = List.of(
            new UserEntityBuilder()
            .setName("Bogdan")
            .setId(1)
            .setAge(20)
            .setProfession("Student")
            .setEmail("bogdan@gmail.com")
            .setPassword("Xxagrorog123")
            .build(),
            new UserEntityBuilder()
            .setName("Matvey")
            .setId(2)
            .setAge(19)
            .setProfession("Manager")
            .setEmail("matvey@gmail.com")
            .setPassword("Matvey123")
            .build(),
            new UserEntityBuilder()
            .setId(3)
            .setName("Peroz")
            .setAge(20)
            .setProfession("Soldier")
            .setEmail("peroz@gmail.com")
            .setPassword("Peroz123")
            .build(),
            new UserEntityBuilder()
            .setId(4)
            .setName("Valik")
            .setAge(19)
            .setProfession("Builder")
            .setEmail("valik@gmail.com")
            .setPassword("valik123")
            .build(),
            new UserEntityBuilder()
            .setId(5)
            .setName("Vika")
            .setAge(18)
            .setProfession("Student")
            .setEmail("vika@gmail.com")
            .setPassword("Vika123")
            .build());

    @BeforeEach
    void setupThis(){
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate-test.cfg.xml")
                    .build();
            Metadata metadata = new MetadataSources(registry)
                    .addAnnotatedClass(UserEntity.class)
                    .addAnnotatedClass(LoginDataEntity.class)
                    .getMetadataBuilder()
                    .build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        catch (Throwable throwable){
            throw new ExceptionInInitializerError(throwable);
        }
        session = sessionFactory.openSession();
        session.beginTransaction();

    }
    @AfterEach
    void tearThis(){
        session.getTransaction().commit();
        sessionFactory.close();

    }


    @Test
    void findUser() {
        for (int i = 0; i < userEntities.size(); i++){
            session.save(userEntities.get(i));
        }
        assertEquals("Bogdan", session.get(UserEntity.class, Long.valueOf(1)).getName());
    }

    @Test
    void findUserByProfession() {
        for (int i = 0; i < userEntities.size(); i++){
            session.save(userEntities.get(i));
        }
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> entityCriteriaQuery = builder.createQuery(UserEntity.class);
        Root<UserEntity> userEntityRoot = entityCriteriaQuery.from(UserEntity.class);
        List<UserEntity> users = session.createQuery(entityCriteriaQuery.where(builder.equal(userEntityRoot.get("profession"), "Student"))).getResultList();
        assertEquals(List.of(userEntities.get(0), userEntities.get(4)), users);
    }

    @Test
    void findUserByAge() {
        for (int i = 0; i < userEntities.size(); i++){
            session.save(userEntities.get(i));
        }
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> entityCriteriaQuery = builder.createQuery(UserEntity.class);
        Root<UserEntity> userEntityRoot = entityCriteriaQuery.from(UserEntity.class);
        List<UserEntity> users = session.createQuery(entityCriteriaQuery.where(builder.equal(userEntityRoot.get("age"), 18))).getResultList();
        assertEquals(List.of(userEntities.get(4)), users);

    }

    @Test
    void findByEmail() {
        for (int i = 0; i < userEntities.size(); i++){
            session.save(userEntities.get(i));
        }
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LoginDataEntity> loginCriteriaQuery = builder.createQuery(LoginDataEntity.class);
        Root<LoginDataEntity> loginDataEntityRoot = loginCriteriaQuery.from(LoginDataEntity.class);
        LoginDataEntity dataEntity = session.createQuery(loginCriteriaQuery.where(builder.equal(loginDataEntityRoot.get("email"), "valik@gmail.com"))).getSingleResult();
        CriteriaQuery<UserEntity> userEntityCriteriaQuery = builder.createQuery(UserEntity.class);
        Root<UserEntity> userEntityRoot = userEntityCriteriaQuery.from(UserEntity.class);
        UserEntity user = session.createQuery(userEntityCriteriaQuery.where(builder.equal(userEntityRoot.get("dataEntity"), dataEntity))).getSingleResult();
        assertEquals(userEntities.get(3), user);
    }

    @Test
    void saveUser() {
        UserEntity user = new UserEntityBuilder()
                .setName("Arthas")
                .setId(1)
                .setAge(28)
                .setProfession("Paladion")
                .setEmail("arthas@gmail.com")
                .setPassword("Arthas123456")
                .build();
        session.save(user);
        assertEquals(user, session.get(UserEntity.class, Long.valueOf(1)));
    }

    @Test
    void deleteUser() {
        UserEntity user = new UserEntityBuilder()
                .setName("Arthas")
                .setId(1)
                .setAge(28)
                .setProfession("Paladion")
                .setEmail("arthas@gmail.com")
                .setPassword("Arthas123456")
                .build();
        session.save(user);
        session.delete(user);
        assertEquals(null, session.get(UserEntity.class, Long.valueOf(1)));
    }

    @Test
    void updateUser() {
        UserEntity user = new UserEntityBuilder()
                .setName("Arthas")
                .setId(1)
                .setAge(28)
                .setProfession("Paladion")
                .setEmail("arthas@gmail.com")
                .setPassword("Arthas123456")
                .build();
        session.save(user);
        user.setName("ReArthas");
        session.update(user);
        assertEquals(user, session.get(UserEntity.class, Long.valueOf(1)));
    }

    @Test
    void findUsersByName() {
        for (int i = 0; i < userEntities.size(); i++){
            session.save(userEntities.get(i));
        }
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> entityCriteriaQuery = builder.createQuery(UserEntity.class);
        Root<UserEntity> userEntityRoot = entityCriteriaQuery.from(UserEntity.class);
        List<UserEntity> users = session.createQuery(entityCriteriaQuery.where(builder.equal(userEntityRoot.get("name"), "Bogdan"))).getResultList();
        assertEquals("Bogdan", users.get(0).getName());
    }

    @Test
    void findAllUsers() {
        for (int i = 0; i < userEntities.size(); i++){
            session.save(userEntities.get(i));
        }
        assertEquals(userEntities, HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from  UserEntity ").list());
    }
}