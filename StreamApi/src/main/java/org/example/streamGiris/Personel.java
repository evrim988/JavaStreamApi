package org.example.streamGiris;

public class Personel implements Comparable<Personel>{
    private Integer id;
    private String name;
    private String surname;
    private String departman;
    private Double maas;

    public Personel(Integer id, String name, String surname, String departman, Double maas) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.departman = departman;
        this.maas = maas;
    }

    public void maasArttir(double oran) {
        maas += maas * oran / 100;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartman() {
        return departman;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }

    public Double getMaas() {
        return maas;
    }

    public void setMaas(Double maas) {
        this.maas = maas;
    }

    @Override
    public String toString() {
        return "Personel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", departman='" + departman + '\'' +
                ", maas=" + maas +
                '}';
    }

    @Override
    public int compareTo(Personel o) {
        return (int) (this.maas-o.maas); //double dan double ı çıkartırsam sonuç double olacaktı ama ben int istiyorum o yüzden int e cast ettik.
    }
}
