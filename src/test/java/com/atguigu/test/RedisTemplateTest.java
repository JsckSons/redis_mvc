package com.atguigu.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-redis.xml")
public class RedisTemplateTest {
	@Autowired
	RedisTemplate<String, String> redisTemplate;
	
	@Test
	public void testValue() {
		redisTemplate.boundValueOps("name").set("JackSon");
	}	
	@Test
	public void getValue() {
		String string = redisTemplate.boundValueOps("name").get();
		System.out.println(string);
	}	
	@Test
	public void testSet() {
		redisTemplate.delete("nameSet");
		redisTemplate.boundSetOps("nameSet").add("≤‹≤Ÿ","¡ı±∏","ÀÔ»®","÷Ó∏¡¡","’‘‘∆");
		Set<String> members = redisTemplate.boundSetOps("nameSet").members();
		System.out.println(members);

	}	
	@Test
	public void getSet() {
		Set<String> members = redisTemplate.boundSetOps("nameSet").members();
		System.out.println(members);
		redisTemplate.boundSetOps("nameSet").remove("’‘‘∆");
		members = redisTemplate.boundSetOps("nameSet").members();
		System.out.println(members);
	}	
	
	@Test
	public void putList() {
		redisTemplate.delete("nameList");
		Long leftPushAll = redisTemplate.boundListOps("nameList").leftPushAll("≤‹≤Ÿ","¡ı±∏","ÀÔ»®","÷Ó∏¡¡","’‘‘∆");
		System.out.println(leftPushAll);
		List<String> list = redisTemplate.boundListOps("nameList").range(0, 10);
		System.out.println(list);
	}	
	@Test
	public void putHash() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("A", "≤‹≤Ÿ");//("≤‹≤Ÿ","¡ı±∏","ÀÔ»®","÷Ó∏¡¡","’‘‘∆")
		map.put("B", "¡ı±∏");
		map.put("C", "ÀÔ»®");
		map.put("D", "÷Ó∏¡¡");
		redisTemplate.boundHashOps("nameHash").putAll(map);
		Set<Object> keys = redisTemplate.boundHashOps("nameHash").keys();
		for (Object key : keys) {
			System.out.println(redisTemplate.boundHashOps("nameHash").get(key));
		}
	}	
}
