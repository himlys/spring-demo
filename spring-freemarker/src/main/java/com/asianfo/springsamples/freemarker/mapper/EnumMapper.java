package com.asianfo.springsamples.freemarker.mapper;

import com.asianfo.springsamples.freemarker.entity.TmEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EnumMapper {
    @Select("select * from tm_enum where enum_code = #{enumCode}")
    List<TmEnum> getEnum(String enumCode);
}
