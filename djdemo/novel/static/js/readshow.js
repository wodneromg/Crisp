var hiscookiename = 'jieqiHistoryBooks'; //cookie����
var hisbookmax = 10;  //��ౣ�������Ķ���¼
var hiscookievalue = Cookie.get(hiscookiename); //ȡcookie

//��cookie�������Ķ���¼���飬��Ҫ���� /scripts/json2.js
var bookary = [];
try{
	bookary = JSON.parse(hiscookievalue);
	if(!bookary) bookary = [];
}catch(e){
}

//��ʾ����Ķ�
if(bookary.length > 0){
	document.write('<ul>');
	for(var i = bookary.length - 1; i >= 0; i--){
		document.write('<li><a class="f14" href="/book/'+Math.floor(bookary[i].articleid / 1000)+'/'+bookary[i].articleid+'/">��'+bookary[i].articlename+'��</a><br>�������:');
		if(bookary[i].chapterid > 0) document.write('&nbsp;<a href="/book/'+Math.floor(bookary[i].articleid / 1000)+'/'+bookary[i].articleid+'/'+bookary[i].chapterid+'.html">'+bookary[i].chaptername.substr(0, 20)+'</a>');
		document.write('</span></li>');
	}

	document.write('</ul>');
}
//ɾ��һ����
function delhisbook(aid, obj){
	for(var i = 0; i < bookary.length; i++){
		if(bookary[i].articleid == aid){
			bookary.splice(i, 1);
			hiscookievalue = JSON.stringify(bookary);
			Cookie.set(hiscookiename, hiscookievalue, 365);
			obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode);
			break;
		}
	}
}