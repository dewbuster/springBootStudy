package org.sist.sb04_oracle_mybatis_jsp.persistence;

import org.apache.ibatis.annotations.Select;

// @Mapper
public interface TimeMapper {

   @Select("SELECT sysdate FROM dual")
   public String getTime();
   
   public String getTimeXML();
   
}