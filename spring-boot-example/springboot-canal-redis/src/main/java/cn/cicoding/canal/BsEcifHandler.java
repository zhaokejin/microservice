package cn.cicoding.canal;

import cn.cicoding.canal.entity.BsEcif;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * @author z
 * @Description
 * @date 2022/9/25
 */
@CanalTable("bs_ecif")
@Component
@AllArgsConstructor
@Slf4j
public class BsEcifHandler implements EntryHandler<BsEcif> {

    private final RedisTemplate<Object,Object> redisTemplate;

    @Override
    public void insert(BsEcif bsEcif) {
        log.info("[新增]"+bsEcif.toString());
        redisTemplate.opsForValue().set("bs_ecif:"+bsEcif.getSeqNo(),bsEcif);
    }

    @Override
    public void update(BsEcif before, BsEcif after) {
        log.info("[更新]"+after.toString());
        redisTemplate.opsForValue().set("bs_ecif:"+after.getSeqNo(),after);
    }

    @Override
    public void delete(BsEcif bsEcif) {
        log.info("[删除]"+bsEcif.getSeqNo());
        redisTemplate.delete("bs_ecif:"+bsEcif.getSeqNo());
    }
}
