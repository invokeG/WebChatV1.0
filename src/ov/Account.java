package ov;

public class Account{

	//���ں�id
	private String id="id";
	//���ں�����
	private String account_name="account_name";
	//��ͨ���ںŵ��û�id
	private String c_id="c_id";
	//���ںŴ�������
	private String date="date";
	//���ںŹ��ܽ���
	private String introduction="introduction";

	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getAccount_name(){
		return account_name;
	}
	public void setAccount_name(String account_name){
		this.account_name=account_name;
	}
	public String getC_id(){
		return c_id;
	}
	public void setC_id(String c_id){
		this.c_id=c_id;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date=date;
	}
	public String getIntroduction(){
		return introduction;
	}
	public void setIntroduction(String introduction){
		this.introduction=introduction;
	}

	@Override
	public String toString(){
		return "account:\n"+
				"id="+this.getId()+"\n"+
				"account_name="+this.getAccount_name()+"\n"+
				"c_id="+this.getC_id()+"\n"+
				"introduction="+this.getIntroduction();
	}

}
