package com.mb.main.system.base;

import java.util.List;

public interface BaseService {
	public List<?> select() throws Exception;
	
	public Object selectByPrimaryKey(int id) throws Exception;

	public Object create(Object record, String creatorName) throws Exception;
	
	public Object update(Object record, String creatorName) throws Exception;

	public int deleteByPrimaryKey(String id) throws Exception;
}
