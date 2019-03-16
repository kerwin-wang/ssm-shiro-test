package com.kerwin.shiro.test.web.dto;

import com.google.common.collect.Lists;
import com.kerwin.shiro.test.web.model.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @ClassName: DeptLevelDto
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-16 18:52
 */
@Getter
@Setter
@ToString
public class DeptLevelDto extends SysDept
{
    private List<DeptLevelDto> deptList = Lists.newArrayList();

    /**
     * @Description: 拷贝为本地dto
     * @param dept
     * @Date: 2019-03-16 06:55
     */
    public static DeptLevelDto adapt(SysDept dept)
    {
        DeptLevelDto dto = new DeptLevelDto();
        BeanUtils.copyProperties(dept, dto);
        return dto;
    }
}
