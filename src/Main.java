import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Main {
    private PasswordManager passwordManager;

    private int mainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Welcome to your Password Manager. Press 1 to search for an existing entry, press 2 to add a new entry.");
        while(!scanner.hasNextInt()){
            System.out.println("Please enter 1 or 2 as your input.");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    private void addEntry(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the website");
        String newWebsite = scanner.nextLine();
        System.out.println("Please enter the username");
        String newUsername = scanner.nextLine();
        System.out.println("Please enter the password");
        String newPassword = scanner.nextLine();
        System.out.println("Please enter the url");
        String newUrl = scanner.nextLine();
        passwordManager.addEntry(newWebsite, newUsername, newPassword, newUrl, PasswordManager.getTime()) ;
        System.out.println("Succesfullly added!");
        System.out.println("Enter 1 to return to home, or 2 to add another new entry");
        while(!scanner.hasNextInt()){
            System.out.println("Please enter 1 or 2 as your input.");
            scanner.nextLine();
        }
        int result = scanner.nextInt();
        if(result==1){
            return;
        }
        if(result==2){
            addEntry();
        }
    }

    private void getWebsite() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the website");
        Entry e = passwordManager.getEntry(scanner.next());

        if (e != null) {
            System.out.println("Website:" + e.getWebsite());
            System.out.println("Username:" + e.getUsername());
            System.out.println("Password:" + e.getPassword());
            System.out.println("Url:" + e.getUrl());
            System.out.println("Last edited: " +  e.getTime());
            websiteMenu(e); //choose the next step
        } else {
            System.out.println("Sorry, the entry doesn't seem to exist."); //press x to continue etc.
            websiteMenu(e);
        }
    }
    private void search(){
        //search  by website, password, url
        //password:: there are X entries with the same password
    }
    public void updateEntry(Entry e){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to edit? Enter A for website, B for username, C for password, D for url ");
        String answer = scanner.nextLine();
            if(answer.equals("A"))     {
                System.out.println("Please enter the new website name:");
                passwordManager.editWebsite(e,scanner.next());
            }
            if(answer.equals("B"))     {
                System.out.println("Please enter the new username:");
                passwordManager.editUsername(e,scanner.next());
            }
            if(answer.equals("C"))     {
                System.out.println("Please enter the new password:");
                passwordManager.editPassword(e,scanner.next());
            }
            if(answer.equals("D"))     {
                System.out.println("Please enter the new url:");
                passwordManager.editUrl(e,scanner.next());
            }

        System.out.println("Succesfullly edited!");
        System.out.println("Enter 1 to return to the homepage or 2 to search for another entry");
        while(!scanner.hasNextInt()){
            System.out.println("Please enter 1 or 2 as your input.");
            scanner.nextLine();
        }
            int result = scanner.nextInt();
            if(result==1){
                return;
            }
            else if(result==2){
                getWebsite();

        }
    }

    private void websiteMenu(Entry e) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 to return to home, 2 to add a new entry, 3 to delete the current entry, or 4 to edit the current entry");
        while(!scanner.hasNextInt()){
            System.out.println("Please enter 1, 2, 3, or 4 as your input.");
            scanner.nextLine();
        }
        int result = scanner.nextInt();
        if (result==1) {
            return;
        } else if (result==2) {
            addEntry();
        } else if (result==3) {
            passwordManager.deleteEntry(e.getWebsite());
            System.out.println("Entry successfully deleted!"); //
        } else if(result==4){
            updateEntry(e);
        }
    }

    public void run(){
        try {
            passwordManager = new PasswordManager("Data.txt");
        } catch (IOException e) {
            System.out.println("Data file not found.");
            return;
        }

        while (true) {
            int result = mainMenu();

            if (result==1) {
                getWebsite();
            } else if (result==2) {
                addEntry();
            }

        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        Main m = new Main();
        m.run();
    }


}

