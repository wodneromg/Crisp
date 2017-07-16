//显示阅读工具

var ReadTools = {
    bgcolor: ["#ffffff", "#fdf7f7", "#ffebcd", "#e7f4fe", "#000000"],
    fontcolor: ["#613b26", "#000000", "#000000", "#000000", "#999999"],
    fontsize: ["12px", "14px", "16px", "20px", "24px", "32px"],
	fontfamily: ["Microsoft YaHei", "SimSun", "KaiTi"],
    fontfname: ["雅黑", "宋体", "楷体"],
    colorid: 0,
    fontsid: 3,
	fontfid: 0,
    bgid: "cread",
    contentid: "ccontent",
	setid: "mrset",
    SetColor: function (id) {
        document.getElementById(this.bgid).style.backgroundColor = this.bgcolor[id];
        document.getElementById(this.contentid).style.color = this.fontcolor[id];
        if (ReadTools.colorid != id) this.SetCookies("colorid", id);
        ReadTools.colorid = id;
        var lis = document.getElementById('fontcolor').getElementsByTagName('li');
        for (i = 0; i < lis.length; i++){
            if(i == id) lis[i].className='selected';
            else lis[i].className='';
        }
    },
    SetFamily: function (id) {
        document.getElementById(this.contentid).style.fontFamily = this.fontfamily[id];
        if (ReadTools.fontfid != id) this.SetCookies("fontfid", id);
        ReadTools.fontfid = id;
        var lis = document.getElementById('fontfamily').getElementsByTagName('li');
        for (i = 0; i < lis.length; i++){
            if(i == id) lis[i].className='selected';
            else lis[i].className='';
        }
    },
    SetFont: function (id) {
        document.getElementById(this.contentid).style.fontSize = this.fontsize[id];
        if (ReadTools.fontsid != id) this.SetCookies("fontsid", id);
        ReadTools.fontsid = id;
    },
    FontSmall: function () {
        if (ReadTools.fontsid > 0) {
            ReadTools.fontsid--;
            document.getElementById(this.contentid).style.fontSize = this.fontsize[ReadTools.fontsid];
            this.SetCookies("fontsid", ReadTools.fontsid);
        }
    },
    FontBig: function () {
        if (ReadTools.fontsid < this.fontsize.length - 1) {
            ReadTools.fontsid++;
            document.getElementById(this.contentid).style.fontSize = this.fontsize[ReadTools.fontsid];
            this.SetCookies("fontsid", ReadTools.fontsid);
        }
    },
    
    Show: function () {
        var output = "";
        output += '<div><span>阅读主题：</span><ul id="fontcolor" class="fontcolor cf">';
        for (i = 0; i < this.bgcolor.length; i++) {
            output += ' <li style="background: ' + this.bgcolor[i] + ';color: ' + this.fontcolor[i] + '" onclick="ReadTools.SetColor(' + i + ')">Aa</li>';
        }
        output += '    </ul></div>\
		<div><span>正文字体：</span><ul id="fontfamily" class="fontfamily cf">';
		for (i = 0; i < this.fontfamily.length; i++) {
            output += ' <li style="font-family: ' + this.fontfamily[i] + ';" onclick="ReadTools.SetFamily(' + i + ')">'+this.fontfname[i]+'</li>';
        }
		output += '    </ul></div>\
		<div><span>字体大小：</span><ul class="fontsize cf">\
                    <li onclick="ReadTools.FontSmall();">Aa-</li>\
                    <li onclick="ReadTools.FontBig();">Aa+</li>\
                </ul></div>';
        document.getElementById(this.setid).innerHTML = output;
    },
    SetCookies: function (cookieName, cookieValue, expirehours) {
        var today = new Date();
        var expire = new Date();
        expire.setTime(today.getTime() + 3600000 * 356 * 24);
        document.cookie = cookieName + '=' + escape(cookieValue) + ';expires=' + expire.toGMTString() + '; path=/';
    },
    ReadCookies: function (cookieName) {
        var theCookie = '' + document.cookie;
        var ind = theCookie.indexOf(cookieName);
        if (ind == -1 || cookieName == '') return '-1';
        var ind1 = theCookie.indexOf(';', ind);
        if (ind1 == -1) ind1 = theCookie.length;
        return unescape(theCookie.substring(ind + cookieName.length + 1, ind1));
    },
    SaveSet: function () {
        this.SetCookies("colorid", ReadTools.colorid);
        this.SetCookies("fontsid", ReadTools.fontsid);
		this.SetCookies("fontfid", ReadTools.fontfid);
    },
    LoadSet: function () {
        var id = 0;

        id = parseInt(this.ReadCookies("colorid"));
		if(id >= 0 && id < this.bgcolor.length) ReadTools.colorid = id;
        this.SetColor(ReadTools.colorid);

        id = parseInt(this.ReadCookies("fontsid"));
        if(id >= 0 && id < this.fontsize.length) ReadTools.fontsid = id;
        this.SetFont(ReadTools.fontsid);

		id = parseInt(this.ReadCookies("fontfid"));
        if(id >= 0 && id < this.fontfamily.length) ReadTools.fontfid = id;
        this.SetFamily(ReadTools.fontfid);
    }
};

ReadTools.Show();
ReadTools.LoadSet();