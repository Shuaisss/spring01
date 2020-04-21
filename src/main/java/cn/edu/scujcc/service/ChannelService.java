package cn.edu.scujcc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.dao.ChannelRepository;
import cn.edu.scujcc.model.Channel;


@Service
public class ChannelService {
	@Autowired
	private ChannelRepository repo;
	
	/**
	 * 获取所有频道的数据
	 * 
	 * @return 频道List
	 */
	public List<Channel> getAllChannels(){
		return repo.findAll();
	}
	
	/***
	 * 获取一个频道的数据
	 * 
	 * @param channelId 频道编号
	 * @return 频道对象，未找到返回 null
	 */
	public Channel getChannel(String channelId) {
		Optional<Channel> result = repo.findById(channelId);
		
		if(result.isPresent()) {
			return result.get();
		}else {
			return null;
		}	
	}
	
	/**
	 * 删除一个频道数据
	 * 
	 * @param channelId ��ɾ����Ƶ�����
	 * @return 删除成功返回 true 失败返回false
	 */
	public boolean deleteChannel(String channelId) {
		boolean result = true;
		repo.deleteById(channelId);
		
		return result;
	}
	
     /**
	 * 保存频道
	 * 
	 * @param c 待保存的频道对象 （没有id值）
	 * @return 保存后的频道 （有id值）
	 */
	public Channel creatChannel(Channel c) {
		return repo.save(c);
	}
	
	/**
	 * 更新指定的频道信息
	 * @param c 新的频道信息，用于更新已存在的同一频道
	 * @return 更新后的频道信息
	 */
	public Channel updateChannel(Channel c) {
		Channel saved = getChannel(c.getId());
		if (saved != null) {
			//方法一：自己一个个复制属性
			if(c.getTitle() != null) {
				saved.setTitle(c.getTitle());
			}
			if(c.getQuality() != null) {
				saved.setQuality(c.getQuality());
			}
			if(c.getUrl() != null) {
				saved.setUrl(c.getUrl());
			}
			if(c.getComments() != null) {
				saved.getComments().addAll(c.getComments());
			}else {
				saved.setComments(c.getComments());
			}
		}
		return repo.save(saved);
	}
	
	/**
	 * ��������
	 * @param title
	 * @param quality
	 * @return
	 */

	public List<Channel> searchQuality( String quality) {
		return repo.findByQuality( quality);
	}
	public List<Channel> searchTitle( String title) {
		return repo.findByTitle( title);
	}
	
	/**
	 * 找出今天又评论的频道
	 * @return 频道列表
	 */
	public List<Channel> searchLatestCommentsChannel(){
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime today = LocalDateTime.of(now.getYear(),
				now.getMonthValue(), now.getDayOfMonth(), 0, 0);
		return repo.findByCommentsDtAfter(today);
		
		
	}


}
