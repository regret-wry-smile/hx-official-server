package com.hx.common.service;

import com.hx.domain.LogDO;
import com.hx.domain.UserDO;
import org.springframework.stereotype.Service;

@Service
public interface LogService {
	void save(LogDO logDO);
	void save(LogDO logDO, UserDO userDO);
//	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
