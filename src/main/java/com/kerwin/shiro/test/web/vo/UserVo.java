package com.kerwin.shiro.test.web.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: UserVo
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-04-02 16:13
 */
@Getter
@Setter
public class UserVo
{
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 1,max = 20,message = "用户名长度需要在20个字以内")
    private String userName;

    @NotBlank(message = "电话不能为空")
    @Length(min = 1,max = 13,message = "电话长度需要在13个字以内")
    private String telephone;

    @NotBlank(message = "邮箱不能为空")
    @Length(min = 5,max = 50,message = "电话长度需要在50个字以内")
    private String mail;

    @NotNull(message = "必须指定用户所在的部门")
    private Integer deptId;

    @NotNull(message = "必须指定用户状态")
    @Min(value = 0,message = "用户状态不合法")
    @Max(value = 2,message = "用户状态不合法")
    private Integer status;

    @Length(max = 200,message = "备注长度需要在200个字以内")
    private String remark;
}
