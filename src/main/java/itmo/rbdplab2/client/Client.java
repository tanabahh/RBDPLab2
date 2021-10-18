package itmo.rbdplab2.client;


import java.util.Scanner;

public class Client {

    public static void main(String[] args){
        System.out.println("Hi!");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("choose command");
            String command = scanner.nextLine();
            switch (command) {
                case "add": addNewBook(); break;
                case "find": find(); break;
                case "exit": System.exit(0); break;
                default:
                    System.out.println("I don't know this command:(");
            }
        }
    }

    private static void addNewBook() {
        ValidationService validationService = new ValidationService();
        ClientCommand clientCommand = new ClientCommand();
        Scanner scanner = new Scanner(System.in);
        System.out.println("print name");
        String name = scanner.nextLine();
        System.out.println("print author");
        String author = scanner.nextLine();
        System.out.println("print annotation");
        String annotation = scanner.nextLine();
        System.out.println("print date");
        String date = scanner.nextLine();
        System.out.println("print genre");
        String genre = scanner.nextLine();
        System.out.println("print isbn");
        String isbn = scanner.nextLine();

        try{
            validationService.checkNotEmptyString(name, "name");
            validationService.checkNotEmptyString(author, "author");
            validationService.checkNotEmptyString(annotation, "annotation");
            validationService.checkNotEmptyString(genre, "genre");
            validationService.checkNotEmptyString(isbn, "isbn");
            validationService.checkNotEmptyString(name, "name");
            validationService.checkLocalDate(date, "date");
            clientCommand.addBook(name, author, genre, annotation, date, isbn);
        } catch (BadRequestException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println("try again later");
        }

    }

    private static void find() {
        ValidationService validationService = new ValidationService();
        ClientCommand clientCommand = new ClientCommand();
        Scanner scanner = new Scanner(System.in);
        String byName = "false";
        String byAuthor = "false";
        String byKeyWord = "false";
        System.out.println("print filter for searching (name, author, key words)");
        String command = scanner.nextLine();
        switch (command) {
            case "name": byName = "true"; break;
            case "author": byAuthor = "true"; break;
            case "key words":byKeyWord = "true"; break;
            default:
                System.out.println("find all");
        }
        System.out.println("print value of searching");
        String value = scanner.nextLine();
        try {
            clientCommand.findBook(byName, byAuthor, byKeyWord, value);
        }catch (Exception e) {
            System.out.println("try again later");
        }
    }
}
