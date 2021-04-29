package study.resolver.handlerresolvertest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MainController {

    @PostMapping("/test")
    public void test(@PersonInfoCheck PersonInfo personInfo){

        log.info("Address : {}",personInfo.getAddress());
        log.info("Id : {}",personInfo.getId());
        log.info("Name : {}",personInfo.getName());
    }
}
