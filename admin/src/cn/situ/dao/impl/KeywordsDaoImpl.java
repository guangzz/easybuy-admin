package cn.situ.dao.impl;

import cn.situ.bean.Keywords;
import cn.situ.dao.IKeywordsDao;
import org.springframework.stereotype.Repository;

@Repository("keywordsDao")
public class KeywordsDaoImpl extends BaseDaoImpl<Keywords> implements IKeywordsDao{
}
