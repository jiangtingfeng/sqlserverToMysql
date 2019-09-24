package com.xxl.job.executor.repository.mysql;

import com.xxl.job.executor.entity.mysql.SysCorp;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jiangtingfeng
 * @description
 * @data 2019/9/24
 */
public interface SysCropRepository extends JpaRepository<SysCorp,Integer> {
}
