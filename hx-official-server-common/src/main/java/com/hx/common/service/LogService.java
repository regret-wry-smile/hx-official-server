package com.hx.common.service;

import com.hx.domain.HxUser;
import com.hx.domain.LogDO;
import org.springframework.stereotype.Service;

@Service
public interface LogService {
	void save(LogDO logDO);
	void save(LogDO logDO, HxUser userDO);
//	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
