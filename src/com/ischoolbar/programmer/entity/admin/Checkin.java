package com.ischoolbar.programmer.entity.admin;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * ��סʵ����
 * @author Administrator
 *
 */
@Component
public class Checkin {
	private Long id;//��סid
	private Long roomId;//����id
	private Long roomTypeId;//����id
	private Float checkinPrice;//��ס�۸�
	private String name;//��ס������
	private String idCard;//���֤����
	private String mobile;//�ֻ���
	private int status;//״̬��0����ס�У�1���ѽ������
	private String arriveDate;//��ס����
	private String leaveDate;//�������
	private Date createTime;//����ʱ��
	private Long bookOrderId;//Ԥ������id����Ϊ��
	private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Long getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getArriveDate() {
		return arriveDate;
	}
	public void setArriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}
	public String getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public Float getCheckinPrice() {
		return checkinPrice;
	}
	public void setCheckinPrice(Float checkinPrice) {
		this.checkinPrice = checkinPrice;
	}
	public Long getBookOrderId() {
		return bookOrderId;
	}
	public void setBookOrderId(Long bookOrderId) {
		this.bookOrderId = bookOrderId;
	}
	
	
	
}
