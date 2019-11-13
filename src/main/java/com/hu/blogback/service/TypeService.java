package com.hu.blogback.service;

import com.hu.blogback.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    /**
     * 添加分类
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 根据id获取分类
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 分页，获取 分类列表
     * @param pageable
     * @return
     */
    Page<Type> listType(Pageable pageable);

    /**
     * 查找所有数据
     * @return
     */
    List<Type> listType();

    /**
     * 查找博客类型最多的size个博客类型
     * @param size
     * @return
     */
    List<Type> listTypeTop(Integer size);

    /**
     * 更新分类信息
     * @param id
     * @param type
     * @return
     */
    Type updateType(Long id, Type type);

    /**
     * 删除分类
     * @param id
     */
    void deleteType(Long id);

    /**
     * 根据name获取分类
     * @param name
     * @return
     */
    Type getTypeByName(String name);
}
