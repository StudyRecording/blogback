package com.hu.blogback.service;

import com.hu.blogback.dao.TagRepository;
import com.hu.blogback.exception.NotFoundException;
import com.hu.blogback.pojo.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag saveTag(Tag tag) {

        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {

        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTay(String ids) {
        return tagRepository.findAllById(converToList(ids));
    }

    @Override
    public List<Tag> listTag(Integer size) {

        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return tagRepository.findTop(pageable);
    }

    public List<Long> converToList(String ids) {
        List<Long> listId = new ArrayList<>();
        if(!"".equals(ids.trim()) && ids != null) {
            String[] id = ids.split(",");
            for (String i : id) {
                listId.add(Long.parseLong(i));
            }
        }
        return listId;
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {

        Tag tag1 = tagRepository.getOne(id);
        if (tag1 == null) {
            throw new NotFoundException("未发现此标签");
        }
        BeanUtils.copyProperties(tag, tag1);
        return tagRepository.save(tag1);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {

        tagRepository.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
