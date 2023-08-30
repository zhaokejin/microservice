package cn.cicoding.editor.service;

import cn.cicoding.editor.domain.Editor;

/**
 * Created on 2018/3/4 0004.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface EditorService {

    void save(Editor editor);

    Editor findOne(int id);
}
