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

var bookindex = -1; //��ǰ�����ǲ����Ѿ�����
for(var i = 0; i < bookary.length; i++){
	if(bookary[i].articleid == article_id){
		bookindex = i;
		break;
	}
}
if(bookindex < 0){
	//�µ�������Ķ���¼
	//��ʷ��¼�ﵽ���ֵ��ɾ��һ��
	if(bookary.length >= hisbookmax){
		bookary.shift();
	}
	bookary.push({articleid:article_id, articlename:article_name, chapterid:chapter_id, chaptername:chapter_name});
	hiscookievalue = JSON.stringify(bookary);
	Cookie.set(hiscookiename, hiscookievalue, 365);
}else if(chapter_id > 0){
	//���Ѿ����ڣ��ж��½��Ƿ���Ҫ����
	bookary[bookindex].chapterid = chapter_id;
	bookary[bookindex].chaptername = chapter_name;
	hiscookievalue = JSON.stringify(bookary);
	Cookie.set(hiscookiename, hiscookievalue, 365);
}


//����������
function resizefbar(){
	var leftfbar = $_('leftfbar');
	var rightfbar = $_('rightfbar');
	var apos = $_('cread').getPosition();
	//leftfbar.style.top = apos.y + 'px';
	leftfbar.style.left = (apos.x - parseInt(leftfbar.offsetWidth)) + 'px';
	rightfbar.style.left = (apos.x + parseInt($_('cread').offsetWidth)) + 'px';
}
resizefbar();
addEvent(window, 'load', resizefbar);
addEvent(window, 'resize', resizefbar);