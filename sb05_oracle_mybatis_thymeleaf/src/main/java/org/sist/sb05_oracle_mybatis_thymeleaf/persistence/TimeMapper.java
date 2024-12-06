package org.sist.sb05_oracle_mybatis_thymeleaf.persistence;

import org.apache.ibatis.annotations.Select;

// @Mapper
public interface TimeMapper {

   @Select("SELECT sysdate FROM dual")
   public String getTime();
   
   public String getTimeXML();
   
}