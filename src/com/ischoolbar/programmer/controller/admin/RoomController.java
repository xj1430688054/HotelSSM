package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Room;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.RoomTypeService;
import com.ischoolbar.programmer.service.admin.FloorService;
import com.ischoolbar.programmer.service.admin.RoomService;

/**
 * ��������̨������
 * @author Administrator
 *
 */
@RequestMapping("/admin/room")
@Controller
public class RoomController {
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private FloorService floorService;
	
	/**
	 * ��������б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.addObject("roomTypeList", roomTypeService.findAll());
		model.addObject("floorList", floorService.findAll());
		model.setViewName("room/list");
		return model;
	}
	
	/**
	 * ������Ϣ��Ӳ���
	 * @param roomType
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Room room){
		Map<String, String> ret = new HashMap<String, String>();
		if(room == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�ķ�����Ϣ!");
			return ret;
		}
		if(StringUtils.isEmpty(room.getSn())){
			ret.put("type", "error");
			ret.put("msg", "�����Ų���Ϊ��!");
			return ret;
		}
		if(room.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ�񷿼�����!");
			return ret;
		}
		if(room.getFloorId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ�񷿼�����¥��!");
			return ret;
		}
		if(isExist(room.getSn(), 0l)){
			ret.put("type", "error");
			ret.put("msg", "�÷������Ѿ�����!");
			return ret;
		}
		if(roomService.add(room) <= 0){
			ret.put("type", "error");
			ret.put("msg", "���ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ�!");
		return ret;
	}
	
	/**
	 * ������Ϣ�༭����
	 * @param roomType
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Room room){
		Map<String, String> ret = new HashMap<String, String>();
		if(room == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�ķ�����Ϣ!");
			return ret;
		}
		if(StringUtils.isEmpty(room.getSn())){
			ret.put("type", "error");
			ret.put("msg", "�����Ų���Ϊ��!");
			return ret;
		}
		if(room.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ�񷿼�����!");
			return ret;
		}
		if(room.getFloorId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ�񷿼�����¥��!");
			return ret;
		}
		if(isExist(room.getSn(), room.getId())){
			ret.put("type", "error");
			ret.put("msg", "�÷������Ѿ�����!");
			return ret;
		}
		if(roomService.edit(room) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�޸�ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ�!");
		return ret;
	}
	
	/**
	 * ��ҳ��ѯ������Ϣ
	 * @param sn
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> list(
			@RequestParam(name="sn",defaultValue="") String sn,
			@RequestParam(name="status",required=false) Integer status,
			@RequestParam(name="roomTypeId",required=false) Long roomTypeId,
			@RequestParam(name="floorId",required=false) Long floorId,
			Page page
			){
		Map<String,Object> ret = new HashMap<String, Object>();
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("sn", sn);
		queryMap.put("status", status);
		queryMap.put("roomTypeId", roomTypeId);
		queryMap.put("floorId", floorId);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", roomService.findList(queryMap));
		ret.put("total", roomService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ������Ϣɾ������
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ������Ϣ!");
			return ret;
		}
		try {
			if(roomService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "ɾ��ʧ�ܣ�����ϵ����Ա!");
				return ret;
			}
		} catch (Exception e) {
			// TODO: handle exception
			ret.put("type", "error");
			ret.put("msg", "�÷����´��ڶ�����Ϣ������ɾ���÷����µ����ж�����Ϣ!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "ɾ���ɹ�!");
		return ret;
	}
	
	/**
	 * �жϷ������Ƿ����
	 * @param sn
	 * @param id
	 * @return
	 */
	private boolean isExist(String sn,Long id){
		Room findBySn = roomService.findBySn(sn);
		if(findBySn == null)return false;
		if(findBySn.getId().longValue() == id.longValue())return false;
		return true;
	}
}
