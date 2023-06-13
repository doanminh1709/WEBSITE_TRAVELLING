package com.example.travel.services.imp;

import com.example.travel.daos.User;
import com.example.travel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.xml.bind.SchemaOutputResolver;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@EnableScheduling
@EnableAsync
@ConditionalOnExpression("true ")
public class BirthdayServiceImp {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MailServiceImpl mailService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void Birthday(){
        List<User> users = userRepository.findAll();
        for(User user : users) {
            LocalDate localDate = LocalDate.parse(user.getDateOfBirth());
            LocalDate now = LocalDate.now();
            if(localDate.getDayOfMonth() == now.getDayOfMonth() && localDate.getMonth() == now.getMonth()) {
                ExecutorService service = Executors.newFixedThreadPool(2);
                service.submit(() -> {
                    try {
                        mailService.send("Thông báo", "HELLO TRAVEL Chúc mừng sinh nhật", user.getEmail(), true);

                    } catch (MessagingException ignored) {
                    }
                });
            }
        }

//        LocalDate localDate = LocalDate.parse("2022-07-23");
//        LocalDate now = LocalDate.now();
//        if (localDate.getDayOfMonth() == now.getDayOfMonth()&&
//            localDate.getMonth()== now.getMonth()){
//            System.out.println("hello");
//        }
    }

}
