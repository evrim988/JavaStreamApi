package org.example.streamOrnek;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class OgrenciRunner {
    public static void main(String[] args) {

        List<Ogrenci> ogrenciler = new ArrayList<>();
        ogrenciler.add(new Ogrenci(1L,"Barış","Demirci","İstanbul","Bilg. Müh.",3.52));
        ogrenciler.add(new Ogrenci(2L,"Alexander J.","Walker","Eskişehir","Yön. Bil. Sistemleri",2.9));
        ogrenciler.add(new Ogrenci(3L,"Alperen","Bicav","Izmir","Hukuk",3.22));
        ogrenciler.add(new Ogrenci(4L,"Harun","SAKIN","Bursa","Bilg. Müh.",2.90));
        ogrenciler.add(new Ogrenci(5L,"Anıl","Özoğluoz","İstanbul","Bilg. Müh.",2.04));
        ogrenciler.add(new Ogrenci(6L, "Murat", "Sacak", "Kocaeli", "Insaat Muh.",2.60));
        ogrenciler.add(new Ogrenci(7L,"Berkay","Basol","Angara","Fotografcilik ve Kameramanlik Prog.",0.1));
        ogrenciler.add(new Ogrenci(8L,"Ahmet","Eris","İzmir","Matematik Böl.",2.52));
        ogrenciler.add(new Ogrenci(9L,"Mehmet","Tufan","Adana","Otomotiv müh.",3.08));
        ogrenciler.add(new Ogrenci(10L, "Emirhan", "Ergun", "Tekirdag", "Matematik", 2.8));
        ogrenciler.add(new Ogrenci(11L,"Mehmet","Ertop","Ankara","EEM",3.52));
        ogrenciler.add(new Ogrenci(12L,"Emine","Karabolat","Adana","Elektrik Elektronik Müh.",3.37));
        ogrenciler.add(new Ogrenci(13L,"Mehmet Can","Karahan","İstanbul","Elektronik Haberleşme",3.50));
        ogrenciler.add(new Ogrenci(14L,"Selin", "Sancak","Ankara","Insaat Muh.",3.18));
        ogrenciler.add(new Ogrenci(15L,"Özkan","Sargın","Kotor","Elektronik Haberleşme",3.0));
        ogrenciler.add(new Ogrenci(16L,"Alper","Güler","Ankara","Insaat Muh.",2.52));
        ogrenciler.add(new Ogrenci(17L,"Evrim","Çalışkan","Bursa","Bilg. Programcılığı",3.22));
        ogrenciler.add(new Ogrenci(18L,"Alperen","İkinci","Ankara","Insaat Muh.",3.02));

        System.out.println("------------Öğrenciler Listesi--------------");
        ogrenciler.stream()
                .forEach(System.out::println);

        System.out.println("----------Notu 3.00 ve üstünde olan öğrenciler-------------");
        ogrenciler.stream()
                .filter(o -> o.getNot() >= 3.00)
                .forEach(System.out::println);

        System.out.println("---------İnşaat Muhendisliği Okuyan Öğrencilerin Sayısı-------------");
        long count = ogrenciler.stream()
                .filter(o -> o.getBolum().equalsIgnoreCase("Insaat Muh.")).count();
        System.out.println(count);

        System.out.println("---------Adı a İle Başlayan Öğrenciler---------------------");
        ogrenciler.stream()
                .filter(ogr-> ogr.getAd().startsWith("A"))
                .forEach(System.out::println);

        System.out.println("--------Hakan adlı öğrenci var mı----------------------");
        boolean hakanVarMi = ogrenciler.stream()
                .anyMatch(o -> o.getAd().equalsIgnoreCase("hakan"));
        System.out.println(hakanVarMi);

        System.out.println("--------Öğrencilerin notlarını büyükten küçüğe sıralanmış hali-------------------");
        ogrenciler.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("---------Notu 3.0 üstü olan öğrencilerin adı ve bölümleri---------------");
        ogrenciler.stream()
                .filter(o -> o.getNot() > 3.00)
                .forEach(o -> System.out.println("Öğrenci Adı: " + o.getAd() + " /Soyadı: " + o.getBolum()));

        System.out.println("---------En yüksek nota sahip öğrencinin listesi-------------------");
        ogrenciler.stream()
                .sorted(Comparator.comparingDouble(Ogrenci::getNot).reversed()) //öğrenci sınıfının impğlemente etmesine gerek kalmıyor böyle bir durumda
                .limit(1)
                .forEach(System.out::println);
        //alternatif
        System.out.println("--------------alternatif çözüm------------------------");
        Optional<Ogrenci> max = ogrenciler.stream().max(Comparator.comparingDouble(Ogrenci::getNot));
        if(max.isPresent()){
            System.out.println(max.get());
        }

        System.out.println("--------------notu 2.50 ile 3.50 arasında öğrencilerin sayısı-------------");
        long count1 = ogrenciler.stream()
                .filter(o -> o.getNot() >= 2.50 && o.getNot() <= 3.50).count();
        System.out.println(count1);
    }
}
