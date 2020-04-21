package cn.edu.scujcc.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scujcc.model.Channel;
import cn.edu.scujcc.service.ChannelService;

@RestController
@RequestMapping("/channel")
public class ChannelController {
	public static final Logger Logger = LoggerFactory.getLogger(ChannelController.class);
	
	@Autowired
	private ChannelService service;
	
	@GetMapping
	public List<Channel> getAllChannels() {
		Logger.info("正在读取所有频道信息。。。");
		List<Channel> results = service.getAllChannels();
		return results;
	}

	@GetMapping("/{id}")
	public Channel getChannel(@PathVariable String id) {
		Channel c = service.getChannel(id);
		if (c != null) {
			return c;
		}else {
			Logger.error("找不到指定频道..!");
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteChannel(@PathVariable String id) {
		System.out.println("即将删除的频道,id="+id);
		boolean result = service.deleteChannel(id);
		if(result) {
		   return ResponseEntity.ok().body("删除成功");
		}else {
		   return ResponseEntity.ok().body("删除失败");
		}
	}	
	 
	@PostMapping
	public Channel createChannel(@RequestBody Channel c) {
		System.out.println("即将创建频道，频道数据："+c);
		Channel saved = service.creatChannel(c);
		return saved;
	}
	
	@PutMapping
	public Channel updateChannel(@RequestBody Channel c) {
		System.out.println("即将更新频道，频道数据："+c);
		Channel updated = service.updateChannel(c);
		return updated;
	}
	
	@GetMapping("/q/{quality}")
	public List<Channel> searchQuality(
			@PathVariable String quality) {
		return service.searchQuality(quality);
	}
	@GetMapping("/t/{title}")
	public List<Channel> searchTitle(
			@PathVariable String title) {
		return service.searchTitle(title);
	}
	
	@GetMapping("/hot")
	public List<Channel>getHotChannels(){
		return service.searchLatestCommentsChannel();
	}
}
