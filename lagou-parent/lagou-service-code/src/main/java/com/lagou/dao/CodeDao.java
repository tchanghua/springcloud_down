package com.lagou.dao;

import com.lagou.pojo.Code;
import com.lagou.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodeDao extends JpaRepository<Code,Long>, JpaSpecificationExecutor<Code> {

    //自定义sql
    @Query(value = "select * from lagou_auth_code where email =?1 order by createtime",nativeQuery = true)
    public List<Code> findCodeByEmail(String email);

}
