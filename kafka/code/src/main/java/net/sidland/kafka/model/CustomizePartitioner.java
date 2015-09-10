/**
 * Project Name:kafkacode
 * File Name:s.java
 * Package Name:net.sidland.kafka.model
 * Date:2015年9月9日下午2:21:36
 * Copyright (c) 2015, sid Jenkins All Rights Reserved.
 * 
 *
*/

package net.sidland.kafka.model;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * ClassName:CustomizePartitioner
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2015年9月9日 下午2:21:36 
 * @author   sid
 * @see 	 
 */
public class CustomizePartitioner implements Partitioner {  
    public CustomizePartitioner(VerifiableProperties props) {  
   
    }  
    /** 
     * 返回分区索引编号 
     * @param key sendMessage时，输出的partKey 
     * @param numPartitions topic中的分区总数 
     * @return 
     */  
    @Override  
    public int partition(Object key, int numPartitions) {  
        System.out.println("key:" + key + "  numPartitions:" + numPartitions);  
        String partKey = (String)key;  
        if ("part2".equals(partKey))  
            return 2;  
        return 0;  
    }  
}  