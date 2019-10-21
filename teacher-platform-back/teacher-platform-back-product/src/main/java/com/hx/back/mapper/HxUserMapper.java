package com.hx.back.mapper;

import com.hx.domain.HxUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HxUserMapper {

    int delete(Integer id);

    int insert(HxUser hxUser);

    int insertDynamic(HxUser hxUser);

    int updateDynamic(HxUser hxUser);

    int update(HxUser hxUser);

    HxUser selectByUser(HxUser hxUser);

    List<HxUser> findWithResultBypage(HxUser hxUser);

    Integer findWithCount(HxUser hxUser);
}
