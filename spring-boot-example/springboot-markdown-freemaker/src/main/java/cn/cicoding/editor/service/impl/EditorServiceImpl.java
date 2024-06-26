package cn.cicoding.editor.service.impl;

import cn.cicoding.editor.domain.Editor;
import cn.cicoding.editor.repository.EditorRepository;
import cn.cicoding.editor.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/3/4 0004.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@Service
public class EditorServiceImpl implements EditorService {

    @Autowired
    private EditorRepository repository;


    @Override
    public void save(Editor editor) {
        repository.save(editor);
    }

    @Override
    public Editor findOne(int id) {
        return repository.findOne(id);
    }
}
