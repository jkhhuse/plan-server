package com.jkhhuse.plan.controller;

import com.jkhhuse.plan.common.CommonResponse;
import com.jkhhuse.plan.service.kafka.KafkaService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * kafka控制器
 */

@RestController
@Api(tags = "Kafka控制器")
@Slf4j
public class KafkaController {

    @Autowired
    private KafkaService kafkaService;

    /**
     * 新增topic (支持批量，这里就单个作为演示)
     *
     * @param topic topic
     * @return CommonResponse
     */
    @ApiOperation("新增topic")
    @PostMapping("kafka")
    public CommonResponse<?> add(String topic) {
        NewTopic newTopic = new NewTopic(topic, 3, (short) 1);
        kafkaService.createTopic(Lists.newArrayList(newTopic));
        return new CommonResponse("200", "", "success");
    }

    /**
     * 查询topic信息 (支持批量，这里就单个作为演示)
     *
     * @param topic 自增主键
     * @return CommonResponse
     */
    @ApiOperation("查询topic信息")
    @GetMapping("kafka/{topic}")
    public CommonResponse<String> getBytTopic(@PathVariable String topic) {
        return new CommonResponse("200",kafkaService.getTopicInfo(Lists.newArrayList(topic)), "");
    }

    /**
     * 删除topic (支持批量，这里就单个作为演示)
     * (注意：如果topic正在被监听会给人感觉删除不掉（但其实是删除掉后又会被创建）)
     *
     * @param topic topic
     * @return CommonResponse
     */
    @ApiOperation("删除topic")
    @DeleteMapping("kafka/{topic}")
    public CommonResponse<?> delete(@PathVariable String topic) {
        kafkaService.deleteTopic(Lists.newArrayList(topic));
        return new CommonResponse("200", "", "success");
    }

    /**
     * 查询所有topic
     *
     * @return CommonResponse
     */
    @ApiOperation("查询所有topic")
    @GetMapping("kafka/allTopic")
    public CommonResponse<List<String>> getAllTopic() {
        return new CommonResponse("200", kafkaService.getAllTopic(), "success");
    }

    /**
     * 生产者往topic中发送消息demo
     *
     * @param topic
     * @param message
     * @return
     */
    @ApiOperation("往topic发送消息")
    @PostMapping("kafka/message")
    public CommonResponse<?> sendMessage(String topic, String message) {
        kafkaService.sendMessage(topic, message);
        return new CommonResponse("200", "", "success");
    }

    /**
     * 消费者示例demo
     * <p>
     * 基于注解监听多个topic，消费topic中消息
     * （注意：如果监听的topic不存在则会自动创建）
     */
    @KafkaListener(topics = {"topic1", "topic2", "topic3"})
    public void consume(String message) {
        log.info("receive msg: " + message);
    }
}