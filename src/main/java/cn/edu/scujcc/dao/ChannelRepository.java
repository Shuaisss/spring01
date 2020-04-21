package cn.edu.scujcc.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import cn.edu.scujcc.model.Channel;

public interface ChannelRepository extends MongoRepository<Channel,String> {
	public List<Channel> findByTitle(String title);
	public List<Channel> findByQuality(String quality);
	
	//找出评论时间在指定日期之后的所有频道
	public List<Channel> findByCommentsDtAfter(LocalDateTime theDt);
}
