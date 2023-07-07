package oykullanmauygulamasi;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class OyKullanmaUygulamasi {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Lütfen bir konu veya soru yazın: ");
            String konu = scanner.nextLine();
            System.out.print("Lütfen seçeneklerinizi girin (virgülle ayırarak): ");
            String[] secenekler = scanner.nextLine().split(",");
            Map<String, Integer> secenekOylari = new HashMap<>();
            for (String secenek : secenekler) {
                secenekOylari.put(secenek.trim(), 0);
            }
            int toplamOy = 0;
            boolean oyKullanmaDevamEdiyor = true;
            while (oyKullanmaDevamEdiyor) {
                System.out.println("Lütfen aşağıdaki seçeneklerden birini seçin veya çıkış yapmak için 'cikis' yazın:");
                for (int i = 0; i < secenekler.length; i++) {
                    System.out.println((i + 1) + ". " + secenekler[i]);
                }
                String secim = scanner.nextLine();
                if (secim.equalsIgnoreCase("cikis")) {
                    oyKullanmaDevamEdiyor = false;
                } else {
                    try {
                        int secimIndex = Integer.parseInt(secim) - 1;
                        if (secimIndex >= 0 && secimIndex < secenekler.length) {
                            String secilenSecenek = secenekler[secimIndex].trim();
                            secenekOylari.put(secilenSecenek, secenekOylari.get(secilenSecenek) + 1);
                            toplamOy++;
                        } else {
                            System.out.println("Geçersiz bir seçim yaptınız.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Geçersiz bir seçim yaptınız.");
                    }
                }
            }
            System.out.println(konu);
            DecimalFormat yuzdeFormat = new DecimalFormat("#.##%");
            for (Map.Entry<String, Integer> entry : secenekOylari.entrySet()) {
                String secenekAdi = entry.getKey();
                int oySayisi = entry.getValue();
                double oyOrani = oySayisi / (double) toplamOy;
                String oyOraniYuzde = yuzdeFormat.format(oyOrani);
                System.out.println(secenekAdi + ": " + oyOraniYuzde + " oy");
            }
        }
    }
}