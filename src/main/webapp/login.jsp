<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>系统登录</title>
<jsp:include page="common/common.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {

		var loginFun = function() {
			var loginTabs = $('#loginTabs').tabs('getSelected');//当前选中的tab
			var $form = loginTabs.find('form');//选中的tab里面的form
			if ($form.length == 1 && $form.form('validate')) {
				$('#loginBtn').linkbutton('disable');
				$.post('${cxt}/user/userAction!login.action', $form.serialize(), function(result) {
					if (result.success) {
						location.replace('${cxt}/main/main.jsp');
					} else {
						$.messager.alert('提示', result.msg, 'error', function() {
							$('#loginBtn').linkbutton('enable');
						});
					}
				}, 'json');
			}
		};

		$('#loginDialog').show().dialog({
			modal : false,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [ {
				text : '注册',
				handler : function() {
					location.replace('${cxt}/reg.jsp');
				}
			}, {
				id : 'loginBtn',
				text : '登录',
				handler : function() {
					loginFun();
				}
			} ],
			onOpen : function() {
				$('form :input:first').focus();
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						loginFun();
					}
				});
			}
		});

		/* $('#userLoginCombobox').combobox({
			url : '${cxt}/base/syuser!doNotNeedSessionAndSecurity_loginNameComboBox.sy',
			valueField : 'loginname',
			textField : 'loginname',
			required : true,
			panelHeight : 'auto',
			mode : 'remote',
			delay : 500
		});

		$('#userLoginCombogrid').combogrid({
			url : '${cxt}/base/syuser!doNotNeedSessionAndSecurity_loginNameComboGrid.sy',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'loginname',
			textField : 'loginname',
			pagination : true,
			fitColumns : true,
			required : true,
			rownumbers : true,
			mode : 'remote',
			delay : 500,
			sortName : 'loginname',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'loginname',
				title : '登录名',
				width : 100,
				sortable : true
			}, {
				field : 'name',
				title : '姓名',
				width : 100,
				sortable : true
			}, {
				field : 'createdatetime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'modifydatetime',
				title : '最后修改时间',
				width : 150,
				sortable : true
			} ] ]
		});
 */
	});
</script>
</head>
<body>
	<br />
	<a href="${cxt }/init.jsp">初始化数据库(${cxt }/init.jsp)
	</a>

	<div id="loginDialog" title="系统登录" style="display: none; width: 320px; height: 180px; overflow: hidden;">
		<div id="loginTabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div title="用户输入模式" style="overflow: hidden; padding: 10px;">
				<form method="post" class="form">
					<table class="table" style="width: 100%; height: 100%;">
						<tr>
							<th width="50">登录名</th>
							<td><input name="loginname" class="easyui-validatebox" data-options="required:true" value="浩鹏" style="width: 210px;" /></td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" value="123456" style="width: 210px;" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div title="自动补全模式" style="overflow: hidden; padding: 10px;">
				<form method="post" class="form">
					<table class="table" style="width: 100%; height: 100%;">
						<tr>
							<th width="50">登录名</th>
							<td><input id="userLoginCombobox" name="loginname" type="text" value="浩鹏" style="width: 214px;"></td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" value="123456" style="width: 210px;" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div title="数据表格模式" style="overflow: hidden; padding: 10px;">
				<form method="post" class="form">
					<table class="table" style="width: 100%; height: 100%;">
						<tr>
							<th width="50">登录名</th>
							<td><input id="userLoginCombogrid" name="loginname" type="text" value="浩鹏" style="width: 214px;"></td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" value="123456" style="width: 210px;" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>