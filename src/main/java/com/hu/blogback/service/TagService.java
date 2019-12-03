package com.hu.blogback.service;

import com.hu.blogback.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface TagService {

    /**
     * 保存标签
     * @param tag
     * @return
     */
    Tag saveTag(Tag tag);

    /**
     * 获取标签
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 标签的分页查询
     * @param pageable
     * @return
     */
    Page<Tag> listTag(Pageable pageable);

    /**
     * 获得标签列表
     * @return
     */
    List<Tag> listTag();

    /**
     * 根据id字符串获取Tag列表
     * @param ids
     * @return
     */
    List<Tag> listTay(String ids);

    /**
     * 获取博客标签文章数前size的标签列表
     * @param size
     * @return
     */
    List<Tag> listTag(Integer size);

    /**
     * 获取博客标签文章数前size的标签列表(并包含发布博客数量信息)
     * @param size
     * @return
     */
    List<Tag> listTagTopByPublishedCount(Integer size);

    /**
     * 更新标签
     * @param id
     * @param tag
     * @return
     */
    Tag updateTag(Long id, Tag tag);

    /**
     * 删除标签
     * @param id
     */
    void deleteTag(Long id);

    /**
     * 通过名字获取标签
     * @param name
     * @return
     */
    Tag getTagByName(String name);


}
