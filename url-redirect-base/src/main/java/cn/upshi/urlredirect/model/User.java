package cn.upshi.urlredirect.model;

import java.util.HashMap;
import java.util.Map;

/**
 * CloudPrint cn.upshi.urlredirect.model
 * 描述：
 *
 * 时间：2016-12-7 19:13.
 */

public class User {

    private String userid;
    private String password; //BCrypt加密
    private String name;
    private String dept;    //部门
    private String tel;
    private String email;
    private Integer role;   //0普通用户 1管理员
    private Integer status; //-1 删除  0禁用 1正常使用

    public User() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", userid);
        map.put("name", name);
        map.put("dept", dept);
        map.put("tel", tel);
        map.put("email", email);
        map.put("role", role);
        map.put("status", status);
        return map;
    }
}
