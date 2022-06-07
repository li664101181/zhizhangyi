package com.user.mapper;

import com.user.entity.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李昶
 * @since 2022-06-05
 */
public interface TUserMapper extends BaseMapper<TUser> {

    TUser loadUserByUsername(String s);
    List<TUser> countuserbytime(Date starttime, Date endtime);
}
