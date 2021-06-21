package com.cc.simba.k.mysqlJDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Component
public class OrgUserService {

    @Autowired
    JdbcTemplate jdbcTemplate;


    //基于Connection的操作
    public OrgUser getById(long id) {
        // 注意传入的是ConnectionCallback:
        return jdbcTemplate.execute((Connection conn) -> {
            // 可以直接使用conn实例，不要释放它，回调结束后JdbcTemplate自动释放:
            // 在内部手动创建的PreparedStatement、ResultSet必须用try(...)释放:
            try (var ps = conn.prepareStatement("select * from OrgUser where id = ?")) {
                ps.setObject(1, id);
                try (var rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new OrgUser(
                                rs.getLong("id"),
                                rs.getString("userName"),
                                rs.getString("password"),
                                rs.getString("mobile"));
                    }
                    throw new RuntimeException("OrgUser ont found by Id :" + id);
                }
            }
        });
    }

    // T execute(String sql, PreparedStatementCallback<T> action)的用法：
    public OrgUser getByMobiel(String mobile) {
        return jdbcTemplate.execute("select * from orguser where mobile = ?", (PreparedStatement ps) -> {
            ps.setObject(1, mobile);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new OrgUser(
                            rs.getLong("id"),
                            rs.getString("userName"),
                            rs.getString("password"),
                            rs.getString("mobile"));
                }
                throw new RuntimeException("OrgUser ont found by mobile :" + mobile);
            }
            ;
        };
    }

    public OrgUser getByUserName(String userName) {
        return jdbcTemplate.queryForObject("select * from orguser where username = ?", new Object[]{userName},
                (ResultSet rs, int rowNum) -> {
                    return new OrgUser(
                            rs.getLong("id"),
                            rs.getString("userName"),
                            rs.getString("password"),
                            rs.getString("mobile"));
                });
    }

    public List<OrgUser> getUsers(int pageIndex) {
        int limit = 100;
        int offset = limit * (pageIndex - 1);
        return jdbcTemplate.query("SELECT * FROM orguser LIMIT ? OFFSET ?", new Object[]{limit, offset},
                new BeanPropertyRowMapper<>(OrgUser.class));
    }

    public void updateUser(OrgUser user) {
        // 传入SQL，SQL参数，返回更新的行数:
        if (1 != jdbcTemplate.update("UPDATE OrgUser SET username = ? WHERE id=?", user.getUserName(), user.getId())) {
            throw new RuntimeException("User not found by id");
        }
    }

    public OrgUser register(String mobile, String password, String name) {
        // 创建一个KeyHolder:
        KeyHolder holder = new GeneratedKeyHolder();
        if (1 != jdbcTemplate.update(
                // 参数1:PreparedStatementCreator
                (conn) -> {
                    // 创建PreparedStatement时，必须指定RETURN_GENERATED_KEYS:
                    var ps = conn.prepareStatement("INSERT INTO OrgUser(mobile,password,name) VALUES(?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setObject(1, mobile);
                    ps.setObject(2, password);
                    ps.setObject(3, name);
                    return ps;
                },
                // 参数2:KeyHolder
                holder)
        ) {
            throw new RuntimeException("Insert failed.");
        }
        // 从KeyHolder中获取返回的自增值:
        return new OrgUser(holder.getKey().longValue(), mobile, password, name);
    }

}
