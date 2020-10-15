package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class RoleDaoImp implements RoleDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void addRole(Role role) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(role);
        entityManager.getTransaction().commit();
    }

    @Override
    public Role findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Role role = entityManager.find(Role.class, id);
        entityManager.getTransaction().commit();
        return role;
    }
}
