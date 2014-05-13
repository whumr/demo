package test.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		//连接redis   
        Jedis redis = new Jedis("localhost", 6379);  
        redis.flushDB();  
        //key operator  begin  
        /** 
        //列出所有的key 
        Set<String> keys = redis.keys("*"); 
        Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()){   
            String key = it.next();   
            System.out.println(key);   
        }   
        //删除多个key  若key不存在，则忽略该命令。   
        redis.del("key4");   
        //返回给定key的剩余生存时间  [秒] 
        System.out.println(redis.ttl("key3")); 
        //查看key是否存在。      
        System.out.println(redis.exists("key3")); 
        //将当前db的key移动到给定的db当中。 
        //如果当前db和给定db有相同名字的给定key，或者key不存在于当前数据库，那么MOVE没有任何效果。   
        System.out.println(redis.move("foo", 1)); 
        //将key改名为newkey 
        //当key和newkey相同或者key不存在时，返回一个错误。 
        //当newkey已经存在时，RENAME命令将覆盖旧值。   
        redis.rename("key6", "key0");  
        //返回key所储存的值的类型 
        //none(key不存在),string(字符串),list(列表),set(集合),zset(有序集),hash(哈希表) 
        System.out.println(redis.type("foo"));   
        //为给定key设置生存时间。当key过期时，它会被自动删除[秒] 
        redis.expire("foo", 5); 
         
        redis.lpush("sort", "1");   
        redis.lpush("sort", "4");   
        redis.lpush("sort", "6");   
        redis.lpush("sort", "3");   
        redis.lpush("sort", "0");   
        //默认是升序   
        List<String> list = redis.sort("sort"); 
        int leng = list.size(); 
        for(int i=0;i<leng;i++){   
            System.out.println(list.get(i));   
        }   
        */  
        //key operator  end  
  
        //string operator begin  m--->more  
        /** 
        redis.set("name", "solo");   
        //同时设置一个或多个key-value对。   
        redis.mset("haha","111","xixi","222");   
        System.out.println(redis.get("xixi")); 
        redis.append("xixi", "3456"); 
        List<String> list = redis.mget("haha","xixi");   
        for(int i=0;i<list.size();i++){   
             System.out.println(list.get(i));   
        }   
        **/  
        //string operator end  
  
        //list operator begin   
        /** 
        //将值value插入到列表key的表头。   
        redis.lpush("list", "abc");   
        redis.lpush("list", "xzc");   
        redis.lpush("list", "erf");   
        redis.lpush("list", "bnh");   
        //length 
        System.out.println(redis.llen("list")); 
        //遍历 
        //返回列表key中指定区间内的元素，区间以偏移量start和stop指定。 
        //下标(index)参数start和stop都以0为底，也就是说，以0表示列表的第一个元素，以1表示列表的第二个元素， 
        //以此类推。你也可以使用负数下标，以-1表示列表的最后一个元素，-2表示列表的倒数第二个元素.. 
        List<String> list = redis.lrange("list", 0, -1);   
        for(int i=0;i<list.size();i++){   
           System.out.println(list.get(i));   
        }  
        */  
        //list operator end  
        //map operator begin   
        /** 
        redis.hset("website", "google", "www.google.cn"); 
        redis.hset("website", "baidu", "www.baidu.com"); 
        redis.hset("website", "sina", "www.sina.com"); 
        Map<String, String> map = new HashMap<String, String>(); 
        //同时将多个field - value(域-值)对设置到哈希表key中。   
        map.put("cardid", "123456"); 
        map.put("username", "jzkangta"); 
        redis.hmset("hash", map); 
        System.out.println(redis.hlen("website")); 
        System.out.println(redis.hget("hash", "username")); 
        //返回哈希表key中，一个或多个给定域的值。   
        List<String> list = redis.hmget("website","google","baidu","sina");   
        for(int i=0;i<list.size();i++){   
            System.out.println(list.get(i));   
        }   
           
        //返回哈希表key中，所有的域和值。   
        Map<String,String> map2 = redis.hgetAll("hash");   
        for(Map.Entry entry: map2.entrySet()) {   
             System.out.print(entry.getKey() + ":" + entry.getValue() + "\t");   
        }   
        */  
        //map operator end  
        //set operator begin  
        //将member元素加入到集合key当中。    
        redis.sadd("testSet", "s1");  
        redis.sadd("testSet", "s2");  
        redis.sadd("testSet", "s3");  
        redis.sadd("testSet", "s4");  
        redis.sadd("testSet", "s5");  
  
        //移除集合中的某元素。    
        redis.srem("testSet", "s5");  
  
        //SMEMBERS key返回集合key中的所有成员。    
        Set<String> set = redis.smembers("testSet");  
        Iterator<String> it = set.iterator();  
        while (it.hasNext()) {  
            Object obj1 = it.next();  
            System.out.println(obj1);  
        }  
  
        //判断元素是否是集合key的成员。boolean   
        System.out.println(redis.sismember("testSet", "s4"));  
  
        
        StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 1024 * 1024; i++) {
			sb.append("xx");
		}
		System.out.println(sb.toString().length());
        String s = redis.set("test", sb.toString());
        System.out.println(s);
        System.out.println(redis.get("test").length());
        
        
        User u = new User("xxx", "dddd");
        System.out.println(redis.set(u.name.getBytes(), SerializeUtil.serialize(u)));
        
        System.out.println(SerializeUtil.unserialize(redis.get(u.name.getBytes())));
	}

}

class SerializeUtil {
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
		}
		return null;
	}

	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
		}
		return null;
	}
}

