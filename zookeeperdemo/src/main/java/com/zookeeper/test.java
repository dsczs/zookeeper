package com.zookeeper;

import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class test {
    @Test
    public void t1() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.138.129:2181", 6000, new Watcher() {
            //监听所以被触发的事件
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("触发了事件 " + watchedEvent.getType());
            }
        });

        List<String> children = zooKeeper.getChildren("/", true);
        System.out.println(Arrays.asList(children));
        if(zooKeeper.exists("/node",true) == null){
            zooKeeper.create("/node","alan".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }
        System.out.println(Arrays.asList(zooKeeper.getChildren("/", true)));
    }
}
