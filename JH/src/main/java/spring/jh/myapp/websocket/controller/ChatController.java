package spring.jh.myapp.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.jh.myapp.websocket.model.ChatRepository;
import spring.jh.myapp.websocket.model.ChattingRoom;

@Controller
@RequestMapping("/multichat")
public class ChatController {
	
	@Autowired
	private ChatRepository chatRepository;
	
	
	
	@GetMapping({"","/rooms"})
	public String getRoomList(Model model) {
		model.addAttribute("rooms", chatRepository.loadAllRooms());
		return "multichat/roomlist";
	}
	
	@GetMapping("/room/{roomId}")
	public String getRoom(@PathVariable int roomId, Model model) {
		ChattingRoom room = chatRepository.selectRoom(roomId);
		model.addAttribute("room", room);
		model.addAttribute("size", getRoomSize(roomId));
		return "multichat/room";
	}
	
	@PostMapping("/newroom")
	public String createRoom(String roomName) {
		chatRepository.createChattingRoom(roomName);
		return "redirect:rooms";
	}
	
	@GetMapping("/roomsize")
	@ResponseBody
	public int getRoomSize(int roomId) {
		return chatRepository.selectRoom(roomId).getSize();
	}
	
	@GetMapping("/deleteroom")
	@ResponseBody
	public void deleteRoom(int roomId) {
		if(chatRepository.selectRoom(roomId).getSize() == 0) {
			chatRepository.deleteChattingRoom(roomId);
		}
	}
	
	@RequestMapping("/new")
	public String newRoom() {
		return "multichat/new";
	}
}
