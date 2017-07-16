var hiscookiename = 'jieqiHistoryBooks'; //cookie名字
var hisbookmax = 10;  //最多保留几条阅读记录
var hiscookievalue = Cookie.get(hiscookiename); //取cookie
//把cookie解析成阅读记录数组，需要加载 /scripts/json2.js

var bookary = [];
try{
	bookary = JSON.parse(hiscookievalue);
	if(!bookary) bookary = [];
}catch(e){
}

var bookindex = -1; //当前的书是不是已经存在
for(var i = 0; i < bookary.length; i++){
	if(bookary[i].articleid == article_id){
		bookindex = i;
		break;
	}
}
if(bookindex < 0){
	//新的书加入阅读记录
	//历史记录达到最大值，删除一条
	if(bookary.length >= hisbookmax){
		bookary.shift();
	}
	bookary.push({articleid:article_id, articlename:article_name, chapterid:chapter_id, chaptername:chapter_name});
	hiscookievalue = JSON.stringify(bookary);
	Cookie.set(hiscookiename, hiscookievalue, 365);
}else if(chapter_id > 0){
	//书已经存在，判断章节是否需要更新
	bookary[bookindex].chapterid = chapter_id;
	bookary[bookindex].chaptername = chapter_name;
	hiscookievalue = JSON.stringify(bookary);
	Cookie.set(hiscookiename, hiscookievalue, 365);
}


//浮动条处理
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