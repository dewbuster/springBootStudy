package org.sist.sb03_jpa_oracle.persistence;

import org.apache.ibatis.annotations.Select;

// @Mapper
public interface TimeMapper {

   @Select("SELECT sysdate FROM dual")
   public String getTime();
   
   public String getTimeXML();
   
}