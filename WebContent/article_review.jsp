<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ov.User"%>
<%@ page import="ov.Account"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章评论页面</title>
<link rel="stylesheet" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
<style type="text/css">
	@import url("css/apply_account.css");
</style>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
%>
<%
	User user=(User)session.getAttribute("User");
	System.out.println(" "+user.toString());
	String headImage_path = "C:\\Users\\jack\\Desktop\\head_image\\";
	String id = user.getId()+"\\";
	headImage_path+=id;	
	headImage_path += "head.png";
	System.out.println("path=="+headImage_path);
	
%>

	<div id="container">
		<div id="mainContent">
			<div id="list">
				<div id="list_hd">
					<div id="actor">
						<img src=<%=headImage_path%>>
					</div>
					<div id="info_icon">
						<a href="#" id="wechat_add"></a>
					</div>
					<div id=info>
						<p><%=user.getNickName() %></p>
					</div>
				</div>
				<div id="list_search_bar">
					<a id="wechat_search"></a>
					<input id="search_input" type="text" placeholder="搜索">
				</div>
				<div id="list_tab">
					<div class="list_tab_chat" id="list_tab_chat">
						<a href="#" title="聊天">
							<i id="wechat_chat">
							</i>
						</a>	
					</div>
					<div id="list_tab_public" class="list_tab_public">
						<a href="OfficialAccountsServer?id=<%=user.getId() %>" title="阅读">
							<i id="wechat_public">
							</i>
						</a>
					</div>
					<div id="list_tab_friends" class="list_tab_friends" >
						<a href="ContactServlet?id=<%=user.getId() %>" title="通讯录">
							<i id="wechat_friends">
							</i>
						</a>
					</div>
				</div>
				
				<div class="apply_account" onclick="window.location.href='ApplyAccountsServlet?id=<%=user.getId() %>';return false">		<!-- 申请公众号 -->
					<div class="apply_account_avatar">		<!-- 申请公众号入口图标 -->
						<!-- <img src="images/headImage.png"></a> -->
					</div>
					<div class="apply_account_info">		<!-- 申请公众号入口信息 -->
						<h3>
							<span>
								
							</span>
						</h3>
					</div>
				</div>
				
				<div class="dividing_line">
					<hr>		<!-- 分割线 -->
				</div>
				
				<div class="list_min_account">		<!-- 公众号列表 -->
<%
	int count=0;
	List<Account> list=(List<Account>)request.getAttribute("list");
	if(list!=null){
		count=list.size();
		for(int i=0;i<count;i++){
			Account account=list.get(i);
			System.out.println(" "+account.getAccount_name());
%>
					<div class="list_min_account_avatar">		<!-- 公众号图标 -->
						<img src="images/headImage.png"></a>
					</div>
					<div class="list_min_account_date">		<!-- 公众号推送文章日期 -->
						<p>11.11</p>
					</div>
					<div class="list_min_account_info">		<!-- 公众号具体信息 -->
						<h3>
							<span>
								<%=account.getAccount_name() %><!-- 公众号名称 -->
							</span>
						</h3>
						<p>
							<span>
								<%=account.getC_id() %><!-- 公众号标题 -->
							</span>
						</p>
					</div>
					<div class="dividing_line">
						<hr>		<!-- 分割线 -->
					</div>
<%
		}
	}
%>	
				</div>
			</div>
			
			<div id="protocol">
				<div id="protocol_hd">
					<div id="chatArea_title">
					</div>
				</div>
				<div id="protocol_main">
					<div class="page-header">
						<h3 class="text-center">文章评论</h3>
					</div>
					<form action="ArticleReviewServlet?user_id=<%=user.getId() %>" id="testForm" class="form-horizontal">
						<div class="form-group">
							<div class="col-md-12">
								<textarea class="form-control" name="article_review" onkeyup="textAreaChange(this)"
										onkeydown="textAreaChange(this)" rows="6">
								</textarea>
								<div class="text-right">
									<em style="color:red">200</em><span>200</span>
								</div>
							</div>
						</div>
						<!-- 
						<div class="form-group">
							<div class="col-md-offset-10 col-md-6">
								<button type="button" class="btn btn-info" id="testConfirm">
									<a href="ArticleReviewServlet?user_id=<%=user.getId()%>">评论</a>
								</button>
							</div>
						</div>
						 -->
						 <div class="from-group">
						 	<div class="col-md-offset-11 col-md-6">
						 		<input type="submit" value="评论">
						 	</div>
						 </div> 
					</form>
				</div>
				
				<div id="chatArea_tool">
					<a id="wechat_face"></a>
					<a id="wechat_screencut"></a>
					<a id="wechat_file"></a>
				</div>
				<div id="chatArea_input_blank"></div>
				<div id="protocol_submit">
				</div>
			</div>
		</div>
	</div>

<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript">
	//显示限制输入字符method
	function textAreaChange(obj) {
		var $this = $(obj);
        var count_total = $this.next().children('span').text();
        var count_input = $this.next().children('em');
        var area_val = $this.val();
        if(area_val.len()>count_total){
            area_val = autoAddEllipsis(area_val,count_total);//根据字节截图内容
            $this.val(area_val);
            count_input.text(0);//显示可输入数
        }else{
            count_input.text(count_total - area_val.len());//显示可输入数
        }
	}
	
	//得到字符串的字节长度
    String.prototype.len = function(){
        return this.replace(/[^\x00-\xff]/g, "xx").length;
    };
    /*
     * 处理过长的字符串，截取并添加省略号
     * 注：半角长度为1，全角长度为2
     * pStr:字符串
     * pLen:截取长度
     * return: 截取后的字符串
     */
    function autoAddEllipsis(pStr, pLen) {
        var _ret = cutString(pStr, pLen);
        var _cutFlag = _ret.cutflag;
        var _cutStringn = _ret.cutstring;
        return _cutStringn;
    }
    
</script>
</body>
</html>















