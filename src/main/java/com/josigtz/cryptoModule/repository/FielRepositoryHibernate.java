package com.josigtz.cryptoModule.repository;

import com.josigtz.cryptoModule.model.Fiel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class FielRepositoryHibernate implements FielRepository{
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    @Override
    public Fiel findBySerie(String serie) {
        Session session = factory.openSession();
        Query query = session.createQuery("FROM Fiel where serie = :serie");
        query.setParameter("serie", serie);
        List<Fiel> fiels = query.list();
        session.close();

        if (fiels == null || fiels.isEmpty()) {
            return null;
        }
        return fiels.get(0);
    }

    @Override
    public List<Fiel> findAll() {
        Session session = factory.openSession();
        List<Fiel> fiels = session.createQuery("FROM Fiel").list();
        session.close();
        return fiels;
    }

    @Override
    public Fiel save(Fiel fiel) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(fiel);
        session.getTransaction().commit();
        session.close();
        return fiel;
    }

    @Override
    public void deleteBySerie(String serie) {
        Session session = factory.openSession();
        session.beginTransaction();
        Fiel fiel = new Fiel();
        fiel.setSerie(serie);
        session.remove(fiel);
        session.getTransaction().commit();
        session.close();
    }
}
