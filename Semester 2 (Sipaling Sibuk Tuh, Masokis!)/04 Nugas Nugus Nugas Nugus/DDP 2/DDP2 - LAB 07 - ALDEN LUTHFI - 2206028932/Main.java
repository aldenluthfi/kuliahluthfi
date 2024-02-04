import card.Card;
import card.ElectronicCard;
import card.GiftCard;
import item.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {                                                             /* The code is self explanatory       */
    private static Scanner in = new Scanner(System.in);                         /* Bingung mau commment gimana        */
    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<Card> cards = new ArrayList<>();

    public static void main(String[] args) {
        initItems();
        showMenu();
    }

    private static void initItems() {
        System.out.println("\n========== Buat Item ==========");
        System.out.printf("Masukkan jumlah item: ");
        int jumlahItem = in.nextInt();
        in.nextLine();

        for (int i = 0; i < jumlahItem; i++) {
            System.out.printf("========== Item ke-%d ==========%n", i + 1);
            System.out.print("Nama: ");
            String name = in.nextLine();

            System.out.print("Harga: ");
            int price = Integer.parseInt(in.nextLine());                        /*all number inputs is in this format */

            Item item = new Item(name, price);
            items.add(item);
        }
    }

    private static void showMenu() {                                            /* Main menu                          */
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n========== Menu ==========");
            System.out.println("1. Tambahkan Kartu");
            System.out.println("2. Tampilkan Daftar Kartu");
            System.out.println("3. Beli Item");
            System.out.println("4. Topup");
            System.out.println("5. Keluar");
            System.out.print("Masukan pilihan: ");

            isRunning = execute(Integer.parseInt(in.nextLine()));
        }
    }

    private static boolean execute(int pilihan) {                               /* Execute command                    */
        switch (pilihan) {
            case 1 -> addCard();
            case 2 -> showCard();
            case 3 -> buyItem();
            case 4 -> topup();
            default -> {
                return false;
            }
        }
        return true;
    }

    public static void addCard() {                                              /* Menambah kartu                     */
        System.out.println("\n========== Tambah Kartu ==========");
        System.out.print("Company Name: ");
        String companyName = in.nextLine();

        System.out.print("Tipe: ");
        String cardType = in.nextLine().toUpperCase();

        System.out.print("Balance: ");
        double balance = Double.parseDouble(in.nextLine());

        Card card = createCard(companyName, balance, cardType);
        cards.add(card);

        System.out.println("Berhasil menambahkan kartu");
    }

    private static Card createCard(String companyName, double balance, String type) {
        if (type.matches("(?i)gift")) {
            return new GiftCard(companyName, balance);
        } else if (type.matches("(?i)electronic")) {
            return new ElectronicCard(companyName, balance);
        }
        return null;
    }

    private static void showCard() {                                            /* Print card list                    */
        sortListOfCards();
        printListOfCards();
    }

    public static void buyItem() {                                              /* Beli barang                        */
        System.out.println("\n========== Daftar Item ==========");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("[%d] %s%n", i, items.get(i));
        }
        System.out.print("Pilihan Item: ");
        Item item = items.get(Integer.parseInt(in.nextLine()));

        showCard();
        System.out.print("Pilih id Kartu yang tersedia: ");
        Card card = getCardById(Integer.parseInt(in.nextLine()));
        card.pay(item);
        System.out.printf("Item %s dengan harga %d berhasil dibeli%n", item.getName(), item.getPrice());
    }

    public static void topup() {                                                /* Topup kartu                        */
        showCard();
        System.out.print("Pilih id Kartu yang tersedia: ");
        Card card = getCardById(Integer.parseInt(in.nextLine()));

        System.out.print("Amount: ");
        double amount = Double.parseDouble(in.nextLine());
        if (card instanceof ElectronicCard) {
            ((ElectronicCard) card).topup(amount);
        } else {
            System.out.println("Kartu yang dipilih tidak bisa topup");
        }
    }

    private static void printListOfCards() {
        System.out.printf("========== Daftar Kartu ==========%n");

        for (int i = 0; i < cards.size(); i++) {
            System.out.printf("[%d] %s %n", i, cards.get(i));
        }
    }

    private static Card getCardById(int id) {
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.getId() == id)
                return card;
        }
        return null;
    }

    private static void sortListOfCards() {
        Collections.sort(cards);
    }
}