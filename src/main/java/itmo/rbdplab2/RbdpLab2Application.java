package itmo.rbdplab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RbdpLab2Application {

    public static void main(String[] args) {
        SpringApplication.run(RbdpLab2Application.class, args);
    }

}

//package itmo.rbdplab2;
//
//    import itmo.rbdplab2.service.HelloMessageService;
//    import java.util.Scanner;
//    import org.springframework.beans.factory.annotation.Autowired;
//    import org.springframework.boot.Banner;
//    import org.springframework.boot.CommandLineRunner;
//    import org.springframework.boot.SpringApplication;
//    import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class RbdpLab2Application implements CommandLineRunner {
//
//    @Autowired
//    private HelloMessageService helloService;
//
//    public static void main(String[] args) throws Exception {
//
//        //disabled banner, don't want to see the spring logo
//        SpringApplication app = new SpringApplication(RbdpLab2Application.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.run(args);
//
//    }
//
//    // Put your logic here.
//    @Override
//    public void run(String... args) throws Exception {
//        Scanner scan = new Scanner(System.in);
//        String test = scan.next();
//        System.out.println(test);
//        if (args.length > 0) {
//            System.out.println(helloService.getMessage(args[0].toString()));
//        } else {
//            System.out.println(helloService.getMessage());
//        }
//
//    }
//
//}