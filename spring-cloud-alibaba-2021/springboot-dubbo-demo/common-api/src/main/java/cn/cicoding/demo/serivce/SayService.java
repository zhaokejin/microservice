package cn.cicoding.demo.serivce;

/**
 * @author Neo.Zzj
 * @date 2020/12/18
 */
public interface SayService {

    /**
     * 根据名字say hello
     * @param name 名字
     * @return name + ,hello!
     */
    String sayHelloByName(String name);
}
