import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        LoginSystem newLogin = new LoginSystem();

        while (true) {
            System.out.println("1. Ders Ekle");
            System.out.println("2. Ders Kaydı Yap");
            System.out.println("3. Kayıtlı Dersleri Listele");
            System.out.println("4. ş");
            System.out.print("Seçiminizi yapın: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Boş satırı oku

            switch (choice) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                   
                    break;
                case 4:
                    
                    System.exit(0);
                default:
                    System.out.println("ERORR");
            }
        }
    }
}
