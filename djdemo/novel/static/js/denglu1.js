var jieqiUserId = 0;
var jieqiUserName = '';
var jieqiUserPassword = '';
var jieqiUserGroup = 0;
var jieqiNewMessage = 0;
var jieqiUserVip = 0;
var jieqiUserHonor = '';
var jieqiUserGroupName = '';
var jieqiUserVipName = '';


if(document.cookie.indexOf('jieqiUserInfo') >= 0){
	var jieqiUserInfo = get_cookie_value('jieqiUserInfo');
	//document.write(jieqiUserInfo);
	start = 0;
	offset = jieqiUserInfo.indexOf(',', start); 
	while(offset > 0){
		tmpval = jieqiUserInfo.substring(start, offset);
		tmpidx = tmpval.indexOf('=');
		if(tmpidx > 0){
           tmpname = tmpval.substring(0, tmpidx);
		   tmpval = tmpval.substring(tmpidx+1, tmpval.length);
		   if(tmpname == 'jieqiUserId') jieqiUserId = tmpval;
		   else if(tmpname == 'jieqiUserName_un') jieqiUserName = tmpval;
		   else if(tmpname == 'jieqiUserPassword') jieqiUserPassword = tmpval;
		   else if(tmpname == 'jieqiUserGroup') jieqiUserGroup = tmpval;
		   else if(tmpname == 'jieqiNewMessage') jieqiNewMessage = tmpval;
		   else if(tmpname == 'jieqiUserVip') jieqiUserVip = tmpval;
		   else if(tmpname == 'jieqiUserHonor_un') jieqiUserHonor = tmpval;
		   else if(tmpname == 'jieqiUserGroupName_un') jieqiUserGroupName = tmpval;
		}
		start = offset+1;
		if(offset < jieqiUserInfo.length){
		  offset = jieqiUserInfo.indexOf(',', start); 
		  if(offset == -1) offset =  jieqiUserInfo.length;
		}else{
          offset = -1;
		}
	}
}

if(jieqiUserId != 0 && jieqiUserName != '' && (document.cookie.indexOf('PHPSESSID') != -1 || jieqiUserPassword != '')){
  if(jieqiUserVip == 1) jieqiUserVipName='<span class="hottext">����VIP-</span>';
document.writeln("        <div>");
document.writeln("            <div id=\"centerInp1\">");
document.writeln("                <div class=\"inp\">");
document.writeln('                    ��ӭ����ȫ��������Ա��{'+jieqiUserName+'}</div>');
document.writeln("            ");
document.writeln("           <a class=\"mored2\" href=\"/modules/article/bookcase.php\"  target=\"_blank\">�����</a><a class=\"mored2\" href=\"/logout.php\">�˳�</a>");
document.writeln("        </div></div>");
}else{
  var jumpurl="";
  if(location.href.indexOf("jumpurl") == -1){
    jumpurl=location.href;	
  }
document.writeln("        <form name=\"form\"  method=\"post\" action=\"/login.php?do=submit\">");
document.writeln("            <div id=\"centerInp\">");
document.writeln("                <div class=\"inp\">");
document.writeln("                    <span>�û�����</span><input name=\"username\" type=\"text\" id=\"htmlUserName\" class=\"text\"></div>");
document.writeln("                <div class=\"inp\">");
document.writeln("                    <span>&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;�룺</span><input name=\"password\" type=\"password\" id=\"htmlPassword\"");
document.writeln("                        class=\"text\"></div>");
document.writeln("				<input type=\"hidden\" name=\"action\" value=\"login\"></td>");
document.writeln("            </div>");
document.writeln("            <div class=\"btn\">");
document.writeln("                <input name=\"submit\" class=\"btn1\" type=\"submit\" value=\"��¼\" />");
document.writeln("				<a class=\"btn2\" href=\"/register.php\" onclick=\"openDialog('/register.php?ajax_gets=jieqi_contents', false);stopEvent();\">ע��</a>");
document.writeln("        </form>");
}
function get_cookie_value(Name) { 
  var search = Name + "=";
  var returnvalue = "";
  if (document.cookie.length > 0) {
    offset = document.cookie.indexOf(search)
    if (offset != -1) {
      offset += search.length
      end = document.cookie.indexOf(";", offset);
      if (end == -1)
      end = document.cookie.length;
      returnvalue=unescape(document.cookie.substring(offset, end));
    }
  }
  return returnvalue;
}