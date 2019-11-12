package com.hu.blogback.service;

import com.hu.blogback.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
