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

        if(zooKeeper.exists("/node",true) != null){
            zooKeeper.delete("/node",-1);
        }

        zooKeeper.close();
    }

    @Test
    public void testQueue() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.138.129:2181", 6000, new Watcher() {
            //监听所以被触发的事件
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("触发了事件 " + watchedEvent.getType());
            }
        });

        if (zooKeeper.exists("/queue",false) == null){
            System.out.println("创建队列queue");
            zooKeeper.create("/queue","task-queue".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }else {
            System.out.println("队列queue已存在");
        }


    }

}
