package com.kerwin.shiro.test.web.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: TestVo
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-14 13:36
 */
@Setter
@Getter
public class TestVo
{
    @NotBlank
    private String msg;

    @NotNull
    @Max(10)
    @Min(0)
    private Integer id;

//    @NotEmpty
//    private List list;

}
