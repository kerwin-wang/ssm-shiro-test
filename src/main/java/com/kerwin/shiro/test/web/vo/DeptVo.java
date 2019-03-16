package com.kerwin.shiro.test.web.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: DeptVo
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-16 17:12
 */
@Getter
@Setter
@ToString
public class DeptVo
{
    private Integer id;

    @NotBlank(message = "部门名称不能为空")
    @Length(max = 20,min = 2,message = "部门名称长度需要在2-20个字节之间")
    private String name;

    private Integer parentId;

    @NotNull(message = "展示顺序不能为空")
    private Integer seq;

    @Length(max = 250,message = "备注的长度需要在250个字节之内")
    private String remark;
}
