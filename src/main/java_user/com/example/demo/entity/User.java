package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户实体", description = "user")
@Entity
@Table(name = "user")
public class User implements Serializable {

    /** 主键ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", name = "id")
    private Long id;

    /** 用户名 */
    @ApiModelProperty(value = "用户账号，类似微信ID", name = "username")
    @Length(min=5, max=20, message="用户名长度必须在5-20之间")
    @Pattern(regexp = "^[a-zA-Z_]\\w{4,19}$", message = "用户名必须以字母下划线开头，可由字母数字下划线组成")
    @Column(name = "username", nullable = false, length = 20, unique = true)
    private String username;

    /** 绑定手机号 */
    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "绑定手机", name = "telephone")
    @Column(name = "telephone", nullable = false, length = 11, unique = true)
    private String telephone;

    /** 绑定邮箱 */
    @ApiModelProperty(value = "绑定邮箱", name = "email")
    @Email(message = "邮箱格式错误")//如何验证邮箱是否存在，自定义注解
    @Column(name = "email", length = 50, unique = true)
    private String email;

    /** 用户昵称 */
    @ApiModelProperty(value = "用户昵称", name = "nickname", allowEmptyValue = true)
    @Column(name = "nickname", length = 20, unique = true)
    private String nickname;

    /** 用户性别 */
    @Column(name = "gender", length = 3)
    @ApiModelProperty(value = "用户性别", name = "gender", allowableValues = "[男,女]")
    private char gender;

    /** 用户密码 */
    @ApiModelProperty(value = "密码", name = "password")
    @NotBlank(message = "密码不能为空")
    //@Pattern(regexp = "^.[A-Za-z0-9]+$")
    // 只能匹配英文和数字,密码的正则和长度以加密后的字符串为准，否则校验出错
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    /** 创建时间 */
    @ApiModelProperty(value = "创建日期", name = "createDate")
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    //private List<Role> roles;

    @Column(name = "last_password_reset_date")
    private Date lastPasswordResetDate;
}
