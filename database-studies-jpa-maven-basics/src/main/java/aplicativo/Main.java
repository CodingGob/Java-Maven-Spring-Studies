package aplicativo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Main {
    public static void main(String[] args) {
        
        Pessoa p1 = new Pessoa("Carlos",  "carlos@email.com");
        Pessoa p2 = new Pessoa("Vivian",  "vivian@email.com");
        Pessoa p3 = new Pessoa("Leonardo",  "leonardo@email.com");

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);

        em.getTransaction().commit();
        em.close();

        System.out.println("Feito.");
    }
}