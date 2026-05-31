package studentRecordsManagmentSystem.student;



import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entity.User;

public class mainapp {

    public static void main(String[] args) {

       
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        
        SessionFactory factory = cfg.buildSessionFactory();

       
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Scanner sc= new Scanner(System.in);
        

        try {
        	System.out.println("enter username");
        	String name=sc.nextLine();
        	
        	System.out.println("enter password");
        	String password = sc.nextLine();
        	
            
            User u = new User();
            u.setUsername(name);
            u.setPassword(password);

           
            session.persist(u);

           
            tx.commit();

            System.out.println("✅ User created successfully!");

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
         
            session.close();
            factory.close();
        }
    }
}