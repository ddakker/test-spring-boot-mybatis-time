package pe.kr.ddakker.test.spring.boot.mybatis.time.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pe.kr.ddakker.test.spring.boot.mybatis.time.vo.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into `user` (name, age) values (#{name}, #{age})")
    void store(User user);

    @Select("select * from `user`")
    List<User> getUsers();

    @Select("select * from `user` where seq = ${seq}")
    User findUserById(int seq);

    @Select("select * from `user` where name = #{name}")
    User findUserByName(String name);

    @Update("update `user` set birthday = #{birthday} where seq = ${seq}")
    int updateBirthday(User user);

    @Update("update `user` set delete_time = #{deleteTime} where seq = ${seq}")
    int delete(User user);
}
