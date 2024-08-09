package org.example.streamGiris;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonelRunner {
    public static void main(String[] args) {

        List<Personel> personelList = new ArrayList<>();
        personelList.add(new Personel(1,"Cem","Yılmaz","Sinema",100000.0));
        personelList.add(new Personel(2,"Tarkan","Tevetoğlu","Müzik",200000.0));
        personelList.add(new Personel(3,"Elon","Musk","Space",1_000_000.0));
        personelList.add(new Personel(4,"Tarık","Akan","Sinema",50_000.0));
        personelList.add(new Personel(5,"Ayşe","Kulin","Edebiyat",70_000.0));
        personelList.add(new Personel(6,"Mauro","Icardi","Futbol",100_000.0));
        personelList.add(new Personel(7,"Yusuf","Dikeç","Spor",50_000.0));
        personelList.add(new Personel(8,"Demet","Akalın","Müzik",30_000.0));
        personelList.add(new Personel(9,"Albert","Einstein","Fizik",35_000_000.0));
        personelList.add(new Personel(10,"Arda","Güler","Futbol",4_000_000.0));

        //Aynı kaynaktan(personelList) den 4 farklı stream oluşturduk.
        Stream<Personel> stream1 = personelList.stream();
        Stream<Personel> stream2 = Stream.of(personelList.toArray(new Personel[personelList.size()]));
        Stream<Personel> stream3 = Arrays.stream(personelList.toArray(new Personel[personelList.size()]));
        personelList.stream();

        //1. foreach() : Bir terminal metoddur. Yani sonlandırıcıdır.
        /*personelList.stream().forEach(personel -> System.out.println(personel)); //lambda exp
        System.out.println("-------------------------------------------------");
        personelList.stream().forEach(System.out::println); //metod referans*/

        stream1.forEach(personel -> System.out.println(personel)); //lambda exp
        //stream1.forEach(System.out::println); //metod referans


        System.out.println("-------------------------------");
        personelList.stream().forEach(p -> {
            p.maasArttir(10);
            System.out.println(p);
        });

        System.out.println("-----------------------------------------------------");

        //2. filter() ->
        System.out.println("filter() ile adında e geçen personelleri yazdırma: ");
        personelList.stream()
                .filter(p -> p.getName().contains("e")) //intermediate operation'dır.
                // Kullanıldıktan sonra içindeki koşula uygun yeni bir stream oluşturur. İçinde true, false değer dönecek bir lambda exp kullanılır.

                .forEach(System.out::println); //foreach de yeni bir stream oluşturmaz oluşturulan stream'i sonlandırır.
        //Personel{id=1, name='Cem', surname='Yılmaz', departman='Sinema', maas=110000.0}
        //Personel{id=5, name='Ayşe', surname='Kulin', departman='Edebiyat', maas=77000.0}
        //Personel{id=8, name='Demet', surname='Akalın', departman='Müzik', maas=33000.0}
        //Personel{id=9, name='Albert', surname='Einstein', departman='Fizik', maas=3.85E7}

        System.out.println("------------------------------------------------");
        //adında a harfi geçenler %20 zam yap ve yazdir.
        personelList.stream().filter(p -> p.getName().contains("a"))
                .forEach(p -> {
                    p.maasArttir(20);
                    System.out.println(p);
                });

        System.out.println("-------------------------------------------");
        System.out.println("Maaşı  5_000_000 üzerinde olanlara %1 zam yapalım");
        System.out.println("-------------------------------------------");
        personelList.stream()
                .filter(p -> p.getMaas() > 5_000_000)
                .forEach(p -> {
                    p.maasArttir(1);
                    System.out.println(p);
                });
        //Personel{id=9, name='Albert', surname='Einstein', departman='Fizik', maas=3.8885E7}
        //Personel{id=10, name='Arda', surname='Güler', departman='Futbol', maas=5332800.0}



        //3. Collect: Stream'den bir collection oluşturur.
        System.out.println("--------------------Collect List---------------");
        //departmanı sinema olan personelleri bir listede tutalım. maaşı 80.000 den yüksek olanları getirir.
        List<Personel> sinema = personelList.stream()
                .filter(p -> p.getDepartman().equalsIgnoreCase("Sinema"))
                .filter(p -> p.getMaas() > 80_000)
                .collect(Collectors.toList());
        System.out.println(sinema);
        //[Personel{id=1, name='Cem', surname='Yılmaz', departman='Sinema', maas=110000.0},



        //4. map
        System.out.println("------------map------------------");
        personelList.stream()
                .map(p -> p.getName())
                .forEach(System.out::println);

        System.out.println("----------map ve filter---------------");
        personelList.stream()
                .map(p -> p.getName())
                .filter(personel -> personel.contains("a")) //filter belli bir koşula göre çalışır.
                .forEach(System.out::println);

        System.out.println("-------------map ile maas değerine 9 ekleyelim--------------");
        personelList.stream()
                .map(p -> p.getMaas() + 9)
                .forEach(System.out::println);
        //ancak gerçek listeyi etkilemez. sadece gösterir. Foreach içerisinde yapmış olsaydık gerçek listeyi etkilerdi.


        System.out.println("--------------------------------------------");
        List<Integer> sayilar = List.of(1,2,3,4);
        List<Double> collect = sayilar
                .stream()
                .map(sayi -> Math.pow(sayi,2))
                .collect(Collectors.toList());
        System.out.println(collect);
        //[1.0, 4.0, 9.0, 16.0]

        System.out.println(sayilar);
        //[1, 2, 3, 4]

        //5. distinct: tekrar eden göstermez.
        System.out.println("------------------distinct--------------");
        personelList.stream()
                .map(p -> p.getDepartman())
                .distinct()
                .forEach(System.out::println);

        //6 -> sorted : sıralama işlemleri için kullanılır.
        System.out.println("-------------------sorted----------------");
        personelList.stream()
                .sorted()
                .forEach(System.out::println);
        //sıralama işlemini yapması için comparable dan implemente etmiş olmalı yada comparable içermeli...
        //maaşa göre sıralama işlemi yaptı.

        //7 -> reversed sorted : tersten sıralama yapar.
        System.out.println("-------------------Reverse sorted----------------");
        personelList.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
        //maaşa göre tersten sıralama işlemi yaptı.

        //8 -> limit : stream den kaç eleman alınacağını belirler.
        System.out.println("----------------limit-------------------");
        personelList.stream()
                .limit(5)
                .forEach(System.out::println);

        //en düşük maaşı alan personelin adını yazdıran stream
        System.out.println("------------------------------------");
        personelList.stream()
                .sorted()
                .map(p -> p.getName())
                .limit(1)
                .forEach(System.out::println);

        //9 -> skip: kaç tane eleman atlayacağız.
        System.out.println("--------------skip--------------");
        personelList.stream()
                .skip(3)
                .limit(2)
                .forEach(System.out::println);

        //10 -> count: eleman sayısını getirir.

        //kaç farklı departman var?
        System.out.println("--------------count-------------");
        System.out.println(personelList.stream()
                .map(p -> p.getDepartman())
                .distinct()
                .count());
        //toplam kaç personel var?
        System.out.println(personelList.stream().count());

        //11 -> anyMatch: Herhangi bir eşleşme olursa true döner.
        System.out.println("--------------------anyMatch---------------------");
        System.out.println(personelList.stream()
                .anyMatch(p -> p.getMaas() < 30_000.0));
        //maaşı 30 binden küçük olan bir değer varsa true döner yoksa false döner.

        //12 -> allMatch: hepsi eşleşirse true döner.
        System.out.println("---------------------allMatch--------------------");
        System.out.println(personelList.stream()
                .allMatch(p -> p.getMaas() < 30_000));

        //13 -> noneMatch: hiçbiri koşulu sağlamazsa true döner.
        System.out.println("---------------------noneMacth-------------------");
        System.out.println(personelList.stream()
                .noneMatch(p -> p.getMaas() < 30_000.0));


        //14 -> reduce:
        System.out.println("-------------------reduce--------------------");
        List<Integer> sayilarList = Arrays.asList(5,15,12,2);
        Integer sonuc = sayilarList.stream()
                .reduce(0, (toplam, sayi) -> toplam + sayi);
        System.out.println(sonuc);

        Optional<Integer> maxDeger = sayilarList.stream().reduce((sayi1, sayi2) -> sayi1 > sayi2 ? sayi1 : sayi2);
        if (maxDeger.isPresent()){
            System.out.println(maxDeger.get());
        }
        //optioonal ile sarmalanmış değeri kontrollü bir biçimde kullanmak
        maxDeger.ifPresent(System.out::println);

        //personel list içindeki departmanların adını bir sette toplayınzı
        Set<String> departmanSet = personelList.stream()
                .map(p -> p.getDepartman())
                .collect(Collectors.toSet());
        departmanSet.forEach(System.out::println);

        //personel list içindeki personel adlarını ve uzunluklarını bir map(collection) de topla
        Map<String, Integer> isimlerVeUzunluklar = personelList.stream()
                .map(p -> p.getName())
                .distinct()
                .collect(Collectors.toMap(name -> name, ad -> ad.length()));

        isimlerVeUzunluklar.forEach((k,v)-> System.out.println(k + " " + v));

        //15 -> peek:
        System.out.println("-----------------------peek---------------------------");
        List<Double> zamliMaasListesi = personelList.stream()
                .peek(p -> System.out.println("Zamdan önceki maas: " + p.getMaas()))
                .map(p -> p.getMaas() * 2)
                .peek(zamliMaas -> System.out.println("Zamdan sonraki maas: " + zamliMaas))
                .collect(Collectors.toList()); //yerine toList yazılabilir.

        //16 -> joining: bir nevi birleştirme işlemi yapar.
        System.out.println("----------------------joining--------------------------");
        String personelAdlar = personelList.stream()
                .map(p -> p.getName())
                .collect(Collectors.joining("--"));
        System.out.println(personelAdlar);

    }
}
