package pe.kr.ddakker.test.spring.boot.mybatis.time.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.kr.ddakker.test.spring.boot.mybatis.time.mapper.UserMapper;
import pe.kr.ddakker.test.spring.boot.mybatis.time.vo.User;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class MainController {

    @Autowired
    UserMapper testMapper;

    @GetMapping("/user")
    public List<User> main() {

        log.debug("new Date(): {}", new Date());
        return testMapper.getUsers();
    }

    @PostMapping("/user")
    @ResponseStatus(code = HttpStatus.CREATED)
    public User store(@RequestBody User user) {
        log.debug("user: {}", user);

        testMapper.store(user);
        return testMapper.findUserByName(user.getName());
    }

    @PutMapping("/user/{seq}/birthday")
    public User updateBirthday(@PathVariable("seq") Integer seq) {

        User user = testMapper.findUserById(seq);
        user.setBirthday(LocalDate.of(2000, 01, 01));
        int updateCnt = testMapper.updateBirthday(user);

        if (updateCnt != 1) {
            throw new RuntimeException("error. updateCnt: " + updateCnt);
        }
        return testMapper.findUserById(seq);
    }

    @DeleteMapping("/user/{seq}")
    public User deleteUser(@PathVariable("seq") Integer seq) {

        User user = testMapper.findUserById(seq);
        user.setDeleteTime(ZonedDateTime.now());
        int deleteCnt = testMapper.delete(user);
        if (deleteCnt != 1) {
            throw new RuntimeException("error. deleteCnt: " + deleteCnt);
        }

        return testMapper.findUserById(seq);
    }
}
