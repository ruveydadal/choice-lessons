package vize;
//Bu programda bölüme yeni gelen bir öğrencinin numarası ,şifresi ,ders kayıt işlemleri gerçekleştiriliyor
//dersler dosyadan okunuyor
//seçilen derslerin eklenmesiyle oluşan ders programı öğrenci bilgilerinin de kaydedildiği dosyaya yazdırılıyor
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Vize {
    static public String[] istenmeyenler={"!","#","+","$","%","&","/","=","?","-","q","w","0","1","2","3","4","5","6","7","8","9"};
    static public String[] harfler={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    static public String[] rakamlar = {"0","1","2","3","4","5","6","7","8","9"};
    static public String[] semboller = { "!","#","+","$","%","&","/","=","?","-"};
    static public String[] dersismi=new String[6];
    static public String[] derskodu={"201","211"};
    static public String[] onecekiderskodu={"200","210"};
    static public int [] derskredisi=new int[6];
    static public int[] dersAkts=new int[6];
    static public String[] dershocasi= new String[6];
    static boolean HarfVar = false;
    static boolean sembolVar = false;
    static boolean rakamVar = false;
    static File Dosya=new File("C:\\Users\\muham\\OneDrive\\Masaüstü\\rnd\\lines.txt");
    static File DERSLER =new File("C:\\Users\\muham\\OneDrive\\Masaüstü\\rnd\\DERSLER.txt");
    static int sayi=0;
    static String sifre,isim,ad1;
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        System.out.println("Bilgisayar Mühendisliği ders kayıt programina hoşgeldiniz.");
        Ad_al();
        No_al();
        sifre_al();
        menu();
    }
    public static void Ad_al() throws IOException
    {
        //Öğrenci adını kullanıcıdan alıyoruz
        Scanner scan = new Scanner(System.in);
        boolean kontrolet=true;
        String ad;
        do{
        System.out.println("Hoşgeldiniz ders işlemleri için giriş yapmalısınız.\nKullanıcı adınızı giriniz: ");
        ad=scan.nextLine();
        //kontrol_ad fonksiyonu ile isimde harf ve sembol olmadığını kontrol ediyoruz.
        if(kontrol_ad(ad))
        {
           kontrolet=false; 
        }
       
        }while(kontrolet);
        isim=ad_degistir(ad);//isimdeki türkçe harfleri ingilizce harflere çeviriyoruz
        FileWriter yaz =new FileWriter(DERSLER,true);//Alınan ismi dosyaya yazdırıyoruz
        BufferedWriter veriyaz=new BufferedWriter(yaz);
        veriyaz.write("Öğrencinin adı:");
        veriyaz.write(isim);
        veriyaz.newLine();
        veriyaz.close();
        yaz.close();
 
    }
    public static void No_al()throws IOException
    {
        //girilen bilgilere göre öğrenci numarası oluşturuyoruz
 
        Scanner scan=new Scanner(System.in);
        String sira,yil,no;
        int  kod=10; //bilgisayar mühendisliği bölüm kodu
   
        System.out.println("Okula kayıt  yılınızı giriniz:");
        yil=scan.nextLine();
        System.out.println("Öğrenci sıralamanızı giriniz:");
        sira=scan.nextLine();
        no=yil.substring(2,4)+kod+sira;//girilen yılın son iki hanesi+bölüm kodu+öğrenci başarı sırası
        System.out.println("Öğrenci numaranız:"+no);
        //dosyaya yazdırıyoruz
        FileWriter yaz =new FileWriter(DERSLER,true);
        BufferedWriter veriyaz=new BufferedWriter(yaz);
        veriyaz.write("Öğrencinin numarası:");
        veriyaz.write(no);
        veriyaz.newLine();
        veriyaz.close();
        yaz.close();
       
        
}
    public static void sifre_al()throws IOException
    {
        //sifreyi kullanıcıdan alıyoruz
        boolean kontrol1=true;
        Scanner scan=new Scanner(System.in);
        do{
        System.out.println("Şifrenizi giriniz:");
        sifre=scan.nextLine();
        //içerisinde sadece rakam olmasını istiyoruz
        if(sembol(sifre) && harf(sifre))
        {
            kontrol1=false;
        }
        
           }while(kontrol1);

    }
    public static void menu()throws IOException 
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("Lütfen yapmak istediğiniz işlemi seçiniz:\n1-Ders Seçimi\n2-Ders Silme\n3-Ders tercihi değiştirme\n4-Ders programını öğrenme\n5-Ayrıntılı ders programı\n6-Çıkış");
        int secenek=scan.nextInt();
        
        if(secenek==1)
        {
           derssecimi();
        }
        if(secenek==2)
        {
            derssil();
        }
        if(secenek==3)
        {
            dersdegistir();
        }
        if(secenek==4)
        {
            dersprogramı();
        }
        if(secenek==5)
        {
            dersprogramı2();
        }
        if(secenek==6)
        {
            System.out.println("Çıkış yapılıyor...");
        }
   
    }
    public static void derssecimi()throws IOException
    {
        //dosyadaki ders listesini okutuyoruz
        FileReader a =new FileReader(Dosya);
        BufferedReader b = new BufferedReader(a);
        String c;
        while((c=b.readLine())!=null)
        {
            System.out.println(c);
        }
        
        System.out.println("Almak istediğiniz seçmeli dersin ismini giriniz:(3 adet seçmeli ders almalısınız.)");
        dersler(0,3);//ders secme fonksiyonunu cagırıyoruz
        System.out.println("\nMenuye donuluyor...");
        menu();
    
    }
    public static void derssil() throws FileNotFoundException,IOException 
   {
       Scanner sc=new Scanner(System.in);
  
       //silinen ders yerine boşluk atıyoruz
            System.out.println("Silmek istediğiniz ders sayisini giriniz: ");
            sayi=sc.nextInt();
            int j;
            for(int i=0;i<sayi;i++)
            {
            System.out.println("Silmek istediğiniz ders veya derslerin kodunu giriniz: ");
            int derskodu1=sc.nextInt();
            for(j=0;j<3;j++)
            {
            if(derskodu1==351)
            {
               
                    if(dersismi[j].equals("351-Görüntü İşlemenin Temelleri"))
                       {
                            dersismi[j]=" ";
                       }
            }
            else if(derskodu1==353)
            {
              if(dersismi[j].equals("353-İnsan Bilgisayar Etkileşimi"))
                       {
                            dersismi[j]=" ";
                       }
            }
            else if(derskodu1==355)
            {
               if(dersismi[j].equals("355-Robot Programlama"))
                            {
                                dersismi[j]=" ";
                            }
            }
            else if(derskodu1==357)
            {
                if(dersismi[j].equals("357-Veri İletişimi"))
                {
                    dersismi[j]=" ";
                }
            }
            else if(derskodu1==359)
            {
                if(dersismi[j].equals("359-Kriptolojiye Giriş"))
                {
                    dersismi[j]=" ";
                }
            }
            else if(derskodu1==361)
            {
                if(dersismi[j].equals("361-Bilgisayar Mühendisleri için Sinyaller ve Sistemler"))
                {
                    dersismi[j]=" ";
                }
            }
            else if(derskodu1==363)
            {
                if(dersismi[j].equals("363-Mobil Yazılım Geliştirme"))
                {
                    dersismi[j]=" ";
                }
            }
            else if(derskodu1==463)
            {
                if(dersismi[j].equals("463-Elektrik Enerjisi Üretimi"))
                {
                    dersismi[j]=" ";
                }
            }
            else if(derskodu1==475)
            {
                if(dersismi[j].equals("475-Teknoloji Ekonomisi"))
                {
                    dersismi[j]=" ";
                }
            }
            else if(derskodu1==453)
            {
                if(dersismi[j].equals("453-Coğrafi Bilgi Sistemleri"))
                {
                    dersismi[j]=" ";
                }
            }
            else if(derskodu1==461)
            {
                if(dersismi[j].equals("461-Güneş Enerjisi"))
                {
                    dersismi[j]=" ";
                }
            }
            else if(derskodu1==464)
            {
                if(dersismi[j].equals("464-Robotik Tutucu Sistemleri"))
                {
                    dersismi[j]=" ";
                }
            }
            else if(derskodu1==358)
            {
                if(dersismi[j].equals("358-Nano Malzemeler"))
                {
                    dersismi[j]=" ";
                }
            }
            else
            {
                System.out.println("Hatalı kod girdiniz!");
            }
                
         } 
     }
            System.out.println("Ders silindi.");
            for(int i=0;i<3;i++)
            {
                if(!" ".equals(dersismi[i]))//konsolda okunurken boşluk gözükmemesi için if kullandım
              {
               System.out.println("Seçtiğiniz ders:"+dersismi[i]);
              }
            }
            System.out.println("\nMenuye donuluyor...");
              menu();
     
        
           
  }
    public static void dersdegistir() throws IOException
    {
        System.out.println("Eklemek istediğiniz dersin kodunu giriniz:");
        //silinen ders yerine ekleme yapılıyor
        //silinen ders sayisi kadar ekleme yapıyoruz
        //bu sayiyi sayi değişkeninde tutuyoruz

        dersler(3,sayi);
   
        for(int j=0;j<(3+sayi);j++)
        {
            if(dersismi[j]!=" ")
            {
                  System.out.println(dersismi[j]);
            }
        }
        System.out.println("\nMenuye donuluyor...");
        menu();
}
    public static void dersprogramı() throws IOException
    {
        //sadece dersleri görmek için bu fonksiyon kullanılıyor
        //ve sonrasında dersler dosyasında öğrenci bilgileri aitında ders programı yazdirılıyor
        FileWriter yaz=new FileWriter(DERSLER,true);
         BufferedWriter veriyaz =new BufferedWriter(yaz);
         System.out.println(" - Ders programi - ");
         veriyaz.write("                Ders Adı              \n");
         veriyaz.write("301-Veritabanı Yönetim Sistemleri  \n");
         veriyaz.write("305-Algoritma Analizi  \n");
         veriyaz.write("307-Mikroişlemciler  \n");
         veriyaz.write("309-Mühendislikte Matematik Yöntemleri  \n");
         for(int k=0;k<(3+sayi);k++)
              { if(dersismi[k]!=" ")
              {
                  System.out.println(dersismi[k]);
                  veriyaz.write(dersismi[k]);
                  veriyaz.newLine();
              }
              }
         veriyaz.newLine();
         veriyaz.close();
         yaz.close();
        System.out.println("\nMenuye dönülüyor...");
        menu();
    }
    public static void dersprogramı2()throws IOException
    {
        //ders programını ayrintılı bir bibçimde görmek için bu fonksiyon kullanılıyor
        //dersin kredisi ,akts i ve koordinatoru gözüküyor
        //sonrasında bu programı dersler dosyasına yazdırıyoruz
         FileWriter yaz=new FileWriter(DERSLER,true);
         BufferedWriter veriyaz =new BufferedWriter(yaz);
         veriyaz.write("                Ders Adı               |  Kredi  |  AKTS  |   Dersin Koordinatörü  \n");
         veriyaz.write("301-Veritabanı Yönetim Sistemleri          5         5          Demir Beyaz\n");
         veriyaz.write("305-Algoritma Analizi                      5         4          Ahmet Kitap\n");
         veriyaz.write("307-Mikroişlemciler                        4         4          Ayşe An\n");
         veriyaz.write("309-Mühendislikte Matematik Yöntemleri     3         5          Ferhat Orman\n");
         
         System.out.println("                Ders Adı               |  Kredi  |  AKTS  |   Dersin Koordinatörü");
         System.out.println("301-Veritabanı Yönetim Sistemleri          5         5          Demir Beyaz");
         System.out.println("305-Algoritma Analizi                      5         4          Ahmet Kitap");
         System.out.println("307-Mikroişlemciler                        4         4          Ayşe An");
         System.out.println("309-Mühendislikte Matematik Yöntemleri     3         5          Ferhat Orman");
         for(int k=0;k<(3+sayi);k++)
              { if(dersismi[k]!=" ")
              {
                  System.out.println(dersismi[k]+"\t\t\t"+derskredisi[k]+"\t"+dersAkts[k]+"\t"+dershocasi[k]);
                  veriyaz.write(dersismi[k]+"\t\t\t"+derskredisi[k]+"\t"+dersAkts[k]+"\t"+dershocasi[k]);
                  veriyaz.newLine();
              }
                  
              }
         veriyaz.newLine();
         veriyaz.close();
         yaz.close();
         
         System.out.println("\nMenuye donuluyor...");
         menu();
    }
    static public boolean kontrol_ad(String ad)
    {
        boolean bulundu=false;
        
        for(int i=0;i<ad.length();i++)
        {
            for(int j=0;j<istenmeyenler.length;j++)
            {
                if(istenmeyenler[j].charAt(0)==ad.charAt(i))
                {
                    bulundu=true;
                }
            }
        }
        if(bulundu)
        {
            System.out.println("Kullanıcı adınızda istenmeyen harf buıundu!");
            return false;
        }
        else return true;
    }
    static public String ad_degistir(String isim)
    {
//ad daki türkçe karakterleri çeviriyoruz
      String turkceKarakterler[]={"ı","ö","ğ","ü","ç","ş"};
      String ingilizceKarakterler[]={"i","o","g","u","c","s"};

      for(int i = 0 ; i< turkceKarakterler.length;i++)
      {
             isim=isim.replace(turkceKarakterler[i], ingilizceKarakterler[i]);
      }
         return isim;
    }
    static public boolean harf(String no)
    {
        HarfVar=false;
        for(int i=0;i<sifre.length();i++)
        {
            for(int j=0;j<harfler.length;j++)
            {
                if(harfler[j].charAt(0)==sifre.charAt(i))
                {
                    HarfVar=true;
                }
            }
        }
        if(HarfVar)
        {
            System.out.println("Harf girdiniz!");
            return false;
        }
        else return true;
    }
    static public boolean sembol(String no)
    {
        sembolVar=false;
        for(int i=0;i<sifre.length();i++)
        {
            for(int j=0;j<semboller.length;j++)
            {
                if(semboller[j].charAt(0)==sifre.charAt(i))
                {
                    sembolVar=true;
                }
            }
        }
        if(sembolVar)
        {
            System.out.println("Sembol girdiniz!");
            return false;
        }
        else return true;
    }
    static public int  dersler(int m,int sayi)
    {  
         
        //kulllanıcıdan verilen derslerden istediği dersin kodunu girmesini istiyoruz
        //ders ismini ,kredisini,akts sini ve koordinator ismini dizilere atıyoruz
        // m değişkeni ders ekleme işleminde istenilen sayıdan devam etmesi için
        //sayi değişkeni sildiğimiz sayi kadar ekleme yapabilmek için
        //öğrenci sadece 3 tane seçmeli ders seçebileceği için sadece seçmeli ders kodlarını yazabilir
        Scanner scan=new Scanner (System.in);
        int derskodu2=0;
        for(int l=m;l<(m+sayi);l++)
        {
           
               derskodu2=scan.nextInt();
               
              
               if(derskodu2==351)
                {
                    
                    dersismi[l]="351-Görüntü İşlemenin Temelleri";
                    derskredisi[l]=6;
                    dersAkts[l]=4;
                    dershocasi[l]="Ahmet Masa";
                }
               else  if(derskodu2==353)
                {
                    dersismi[l]="353-İnsan Bilgisayar Etkileşimi";
                    derskredisi[l]=3;
                    dersAkts[l]=4;
                    dershocasi[l]="Mehmet Kalem";
                }
               else if(derskodu2==355)
                {
                    dersismi[l]="355-Robot Programlama";
                    derskredisi[l]=5;
                    dersAkts[l]=4;
                    dershocasi[l]="Ayşe Defter";
                }
               else if(derskodu2==357)
                {
                    dersismi[l]="357-Veri İletişimi";
                    derskredisi[l]=5;
                    dersAkts[l]=4;
                    dershocasi[l]="Ali Silgi";
                }
               else if(derskodu2==359)
                {
                    dersismi[l]="359-Kriptolojiye Giriş";
                    derskredisi[l]=3;
                    dersAkts[l]=4;
                    dershocasi[l]="Sena Hava";
                }
               else if(derskodu2==361)
                {
                    dersismi[l]="361-Bilgisayar Mühendisleri için Sinyaller ve Sistemler";
                    derskredisi[l]=3;
                    dersAkts[l]=4;
                    dershocasi[l]="Fatma Kara";
                }
               else if(derskodu2==363)
                {
                    dersismi[l]="363-Mobil Yazılım Geliştirme";
                    derskredisi[l]=5;
                    dersAkts[l]=5;
                    dershocasi[l]=" Ayşe Sarı";
                }
               else if(derskodu2==463)
                {
                    dersismi[l]="463-Elektrik Enerjisi Üretimi";
                    derskredisi[l]=3;
                    dersAkts[l]=5;
                    dershocasi[l]="Zeynep Yeşil";
                }
               else if(derskodu2==475)
                {
                    dersismi[l]="475-Teknoloji Ekonomisi";
                    derskredisi[l]=4;
                    dersAkts[l]=6;
                    dershocasi[l]="Mustafa Kırmızı";
                }
               else if(derskodu2==453)
                {
                    dersismi[l]="453-Coğrafi Bilgi Sistemleri";
                    derskredisi[l]=3;
                    dersAkts[l]=4;
                    dershocasi[l]="Hamza Mavi";
                }
               else if(derskodu2==461)
                {
                    dersismi[l]="461-Güneş Enerjisi";
                    derskredisi[l]=3;
                    dersAkts[l]=5;
                    dershocasi[l]="Ömer  Siyah";
                }
               else if(derskodu2==464)
                {
                    dersismi[l]="464-Robotik Tutucu Sistemleri";
                    derskredisi[l]=3;
                    dersAkts[l]=4;
                    dershocasi[l]="Ahmet Yılmaz";
                }
               else if(derskodu2==358)
                {
                    dersismi[l]="358-Nano Malzemeler";
                    derskredisi[l]=4;
                    dersAkts[l]=5;
                    dershocasi[l]="Mehmet Genç";
                }
                else
                {
                    System.out.println("Geçersiz kod girdiniz!Menuye giderek 3.secenekten tekrar ders eklemelisiniz.");
                }
                
    }
     return 0;

}
    
}
