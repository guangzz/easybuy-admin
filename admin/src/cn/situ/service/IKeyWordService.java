package cn.situ.service;

import cn.situ.bean.Keywords;
import cn.situ.bean.PageBean;

public interface IKeyWordService {
    PageBean<Keywords> findKeyWord(Integer currPage);

    boolean update(Keywords keywords);

    boolean delete(Keywords keywords);

    boolean insert(Keywords keywords);
}
