package com.example.week4.rabbit.consumer;
import com.example.week4.dao.AdvertisementRepository;
import com.example.week4.dao.UserRepository;
import com.example.week4.entity.Advertisement;
import com.example.week4.entity.Users;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class RabbitConsumer {

    String [] name1 = {"Ahmet", "Mehmet", "Fethi", "Aysu", "Ekrem", "Eren", "Murat", "Efe", "Yigit", "Berkant", "Yasin", "Elif", "Merve", "Zeynep", "İlayda", "Gaye"};
    String [] name2 = {"Yilmaz","Demir","Guclu","Yorulmaz","Keten","Hizli","Kosan","Kerim","Kalay","Bayrak","Toprak","Milas","Atar","Gider","Bardak","Askar","Kumas"};
    String [] title1 = {"satilik","kiralik","temiz","doktordan","ihtiyacdan"};
    String [] title2 = {"ev","araba","villa","arsa","motor","yat","daire"};
    String [] description = {"ihtiyactan satilik","asansorlu","kombili","fiber altyapisi","guven emlak","aktas emlak","kalite bizim isimiz","guven bizim isimiz"};

    UserRepository userRepository;
    AdvertisementRepository advertisementRepository;

    List<Users> usersList;

    @RabbitListener(queues = "queue1-queue")
    public void handleOperation (Users users) throws InterruptedException {

        Thread.sleep(1000);
        System.out.println("(User) Message recieved...");

        users.builder()
                .userName(name1[(int)(Math.random()*name1.length)])
                .userSurname(name2[(int)(Math.random()*name2.length)])
                .userMail(users.getUserName().toLowerCase() + users.getUserSurname().toLowerCase() + "@gmail.com")
                .build();
        usersList.add(users);
        userRepository.save(users);

    }

    @RabbitListener(queues = "queue1-queue")
    public void handleOperation (Advertisement advertisement) throws InterruptedException {

        Thread.sleep(1000);
        System.out.println("(Advertisement) Message recieved...");

        advertisement.builder()
                .users(usersList.get((int)(Math.random()* usersList.size())))
                .title(title1[(int)(Math.random()*title1.length)] + ", " + title2[(int)(Math.random()*title2.length)])
                .detailedMessage(description[(int)(Math.random()*description.length)] + ", "+description[(int)(Math.random()* description.length)])
                .price((long)(Math.random()*3000000))
                .createdAt(new Date())
                .build();
        advertisementRepository.save(advertisement);

    }

}
