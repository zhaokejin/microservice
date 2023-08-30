package cn.cicoding.editor.repository;

import cn.cicoding.editor.domain.Editor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 2018/3/4 0004.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface EditorRepository extends JpaRepository<Editor, Integer> {
}
