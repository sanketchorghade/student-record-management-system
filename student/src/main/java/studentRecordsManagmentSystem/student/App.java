package studentRecordsManagmentSystem.student;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entity.StudentInfo;
import com.entity.User;

public class App {
    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        	
        Scanner sc = new Scanner(System.in);
        // ================= LOGIN =================
        int attempts = 3;
        User user = null;

        while (attempts > 0) {

            System.out.println("\n===== LOGIN =====");

            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            Session loginSession = factory.openSession();

            user = loginSession.createQuery(
                    "from User where username = :u and password = :p", User.class)
                    .setParameter("u", username)
                    .setParameter("p", password)
                    .uniqueResult();

            loginSession.close();

            if (user != null) {
                System.out.println("✅ Login Successful!\n");
                break;
            } else {
                attempts--;
                System.out.println("❌ Invalid! Attempts left: " + attempts);
            }
        }

        if (user == null) {
            System.out.println(" Too many attempts. Exiting...");
            System.out.println("❌ Login failed! Go take a chai break ☕ and try later 😆");
            return;
        }
        String option;

        do {
            System.out.println("\n===== STUDENT RECORD MANAGEMENT SYSTEM =====");
            System.out.println("1. Insert student data");
            System.out.println("2. Update student data");
            System.out.println("3. Delete student record");
            System.out.println("4. Check student record");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            // ================= INSERT Records =================
            case 1:
                Transaction tx = session.beginTransaction();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Email: ");
                String email = sc.nextLine();

                System.out.print("Enter Phone: ");
                String phone = sc.nextLine();

                System.out.print("Enter Address: ");
                String address = sc.nextLine();

                System.out.print("Enter Course Name: ");
                String courseName = sc.nextLine();

                System.out.print("Enter Duration: ");
                String duration = sc.nextLine();

                System.out.print("Enter Fees: ");
                int fees = sc.nextInt();
                sc.nextLine();

                StudentInfo s = new StudentInfo();
                s.setName(name);
                s.setEmail(email);
                s.setPhone(phone);
                s.setAddress(address);
                s.setCourseName(courseName);
                s.setDuration(duration);
                s.setFees(fees);

                session.persist(s);
                tx.commit();

                System.out.println("✅ Record inserted successfully!");
                break;

            // ================= in here we can update data =================
            case 2:
                Transaction tx1 = session.beginTransaction();

                System.out.print("Enter Student ID to update: ");
                int id = sc.nextInt();
                sc.nextLine();

                StudentInfo s2 = session.get(StudentInfo.class, id);

                if (s2 != null) {

                    System.out.println("\nWhat do you want to update?");
                    System.out.println("1. Name");
                    System.out.println("2. Email");
                    System.out.println("3. Phone");
                    System.out.println("4. Address");
                    System.out.println("5. Course Name");
                    System.out.println("6. Duration");
                    System.out.println("7. Fees");
                    System.out.println("8. All");

                    int updateChoice = sc.nextInt();
                    sc.nextLine();

                    switch (updateChoice) {
                    case 1:
                        System.out.print("Enter New Name: ");
                        s2.setName(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Enter New Email: ");
                        s2.setEmail(sc.nextLine());
                        break;
                    case 3:
                        System.out.print("Enter New Phone: ");
                        s2.setPhone(sc.nextLine());
                        break;
                    case 4:
                        System.out.print("Enter New Address: ");
                        s2.setAddress(sc.nextLine());
                        break;
                    case 5:
                        System.out.print("Enter New Course Name: ");
                        s2.setCourseName(sc.nextLine());
                        break;
                    case 6:
                        System.out.print("Enter New Duration: ");
                        s2.setDuration(sc.nextLine());
                        break;
                    case 7:
                        System.out.print("Enter New Fees: ");
                        s2.setFees(sc.nextInt());
                        sc.nextLine();
                        break;
                    case 8:
                        System.out.print("Enter New Name: ");
                        s2.setName(sc.nextLine());

                        System.out.print("Enter New Email: ");
                        s2.setEmail(sc.nextLine());

                        System.out.print("Enter New Phone: ");
                        s2.setPhone(sc.nextLine());

                        System.out.print("Enter New Address: ");
                        s2.setAddress(sc.nextLine());

                        System.out.print("Enter New Course Name: ");
                        s2.setCourseName(sc.nextLine());

                        System.out.print("Enter New Duration: ");
                        s2.setDuration(sc.nextLine());

                        System.out.print("Enter New Fees: ");
                        s2.setFees(sc.nextInt());
                        sc.nextLine();
                        break;
                    default:
                        System.out.println(" Invalid choice");
                    }

                    session.merge(s2);
                    tx1.commit();
                    System.out.println(" Student updated successfully!");

                } else {
                    System.out.println(" Record not found!");
                }
                break;

            // =================in this case we can delete data =================
            case 3:
                Transaction tx2 = session.beginTransaction();

                System.out.print("Enter Student ID to delete: ");
                id = sc.nextInt();
                sc.nextLine();

                StudentInfo s3 = session.get(StudentInfo.class, id);

                if (s3 != null) {
                    session.remove(s3);
                    tx2.commit();
                    System.out.println(" Student deleted successfully!");
                } else {
                    System.out.println(" Invalid record!");
                }
                break;

            // =================  in this case we can display  data =================
            case 4:
                Transaction tx4 = session.beginTransaction();

                System.out.println("\nWhat do you want to check?");
                System.out.println("1. Name");
                System.out.println("2. Email");
                System.out.println("3. Phone");
                System.out.println("4. Address");
                System.out.println("5. Course Name");
                System.out.println("6. Duration");
                System.out.println("7. Fees");
                System.out.println("8. All");

                int checkChoice = sc.nextInt();

                System.out.print("Enter Student ID: ");
                id = sc.nextInt();
                sc.nextLine();

                StudentInfo s4 = session.get(StudentInfo.class, id);

                if (s4 != null) {

                    switch (checkChoice) {
                    case 1:
                        System.out.println("Name: " + s4.getName());
                        break;
                    case 2:
                        System.out.println("Email: " + s4.getEmail());
                        break;
                    case 3:
                        System.out.println("Phone: " + s4.getPhone());
                        break;
                    case 4:
                        System.out.println("Address: " + s4.getAddress());
                        break;
                    case 5:
                        System.out.println("Course: " + s4.getCourseName());
                        break;
                    case 6:
                        System.out.println("Duration: " + s4.getDuration());
                        break;
                    case 7:
                        System.out.println("Fees: " + s4.getFees());
                        break;
                    case 8:
                        System.out.println("\n--- Student Details ---");
                        System.out.println("Name: " + s4.getName());
                        System.out.println("Email: " + s4.getEmail());
                        System.out.println("Phone: " + s4.getPhone());
                        System.out.println("Address: " + s4.getAddress());
                        System.out.println("Course: " + s4.getCourseName());
                        System.out.println("Duration: " + s4.getDuration());
                        System.out.println("Fees: " + s4.getFees());
                        break;
                    default:
                        System.out.println("❌ Invalid choice");
                    }

                    tx4.commit();

                } else {
                    System.out.println("❌ Student not found!");
                }
                break;

            default:
                System.out.println("❌ Invalid choice");
            }

            
            System.out.print("\nDo you want to continue? (yes/no): ");
            option = sc.nextLine();

        } while (option.equalsIgnoreCase("yes"));

        session.close();
        factory.close();
        sc.close();

        System.out.println("Application closed.");
    }
}