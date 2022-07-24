package main.hibernate.service;

import main.hibernate.entity.LoginDataEntity;
import main.hibernate.entity.UserEntity;
import main.hibernate.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public UserEntity findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(UserEntity.class, Long.valueOf(id));
    }

    @Override
    public List<UserEntity> findByProfession(String value) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> entityCriteriaQuery = builder.createQuery(UserEntity.class);
        Root<UserEntity> userEntityRoot = entityCriteriaQuery.from(UserEntity.class);
        List<UserEntity> users = session.createQuery(entityCriteriaQuery.where(builder.equal(userEntityRoot.get("profession"), value))).getResultList();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public List<UserEntity> findByAge(int age) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> entityCriteriaQuery = builder.createQuery(UserEntity.class);
        Root<UserEntity> userEntityRoot = entityCriteriaQuery.from(UserEntity.class);
        List<UserEntity> users = session.createQuery(entityCriteriaQuery.where(builder.equal(userEntityRoot.get("age"), age))).getResultList();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public List<UserEntity> findByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> entityCriteriaQuery = builder.createQuery(UserEntity.class);
        Root<UserEntity> userEntityRoot = entityCriteriaQuery.from(UserEntity.class);
        List<UserEntity> users = session.createQuery(entityCriteriaQuery.where(builder.equal(userEntityRoot.get("name"), name))).getResultList();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public UserEntity findByEmail(String email) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LoginDataEntity> loginCriteriaQuery = builder.createQuery(LoginDataEntity.class);
        Root<LoginDataEntity> loginDataEntityRoot = loginCriteriaQuery.from(LoginDataEntity.class);
        LoginDataEntity dataEntity = session.createQuery(loginCriteriaQuery.where(builder.equal(loginDataEntityRoot.get("email"), email))).getSingleResult();
        CriteriaQuery<UserEntity> userEntityCriteriaQuery = builder.createQuery(UserEntity.class);
        Root<UserEntity> userEntityRoot = userEntityCriteriaQuery.from(UserEntity.class);
        UserEntity user = session.createQuery(userEntityCriteriaQuery.where(builder.equal(userEntityRoot.get("dataEntity"), dataEntity))).getSingleResult();
        transaction.commit();
        session.close();
        return user;

    }

    @Override
    public void save(UserEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(UserEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(UserEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void bulkSave(UserEntity... userEntities) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        for (int i = 0; i < userEntities.length; i++){
            session.save(userEntities[i]);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> userEntities = (List<UserEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from  UserEntity ").list();
        return userEntities;
    }
}
