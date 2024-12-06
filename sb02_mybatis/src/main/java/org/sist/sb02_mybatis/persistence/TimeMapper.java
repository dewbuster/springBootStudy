package org.sist.sb02_mybatis.persistence;

import org.apache.ibatis.annotations.Select;

// @Mapper
public interface TimeMapper {

   @Select("SELECT sysdate FROM dual")
   public String getTime();
   
   public String getTimeXML();
   
}