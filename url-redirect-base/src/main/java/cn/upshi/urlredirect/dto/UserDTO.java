package cn.upshi.urlredirect.dto;

/**
 * CloudPrint cn.edu.zju.cst.cloudprint.dto
 * 描述：
 * 时间：2017-1-29 11:54.
 */

public class UserDTO {

    private String userid;
    private String name;
    private String dept;    //部门
    private String tel;
    private String email;
    private Integer role;   //0普通用户 1管理员
    private Integer status; //-1 删除  0禁用 1正常使用

    public UserDTO() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
        return "UserDTO{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
