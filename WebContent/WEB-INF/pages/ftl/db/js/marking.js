var  checkPaperViewAllMap = {} ;
var partNum=0,sectionNum=0,questionNum=0,partMsg={},countStudentScore=0,countStandardScore=0,
	letter=new Array("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
var question = null;
function getExamMarkingHtml(data){
		question =data.question;
		if(question !=null && question !=""){
		var examTypeCode = data.examTypeCode;
		var questionType = question.questionType;
		var answerType = questionType.answerType;
		var resource = {};
		resource.resourcePath = question.resourcePath;
		if(resource.resourcePath!=null && resource.resourcePath!="" &&examTypeCode==0) {
			$('#answerType_resource').tmpl(resource).appendTo('#div_look_part_');
		}
	// 复合题型展示
		if(questionType.isComplex=="1"){
			if(question.title.indexOf("##1##")!=-1 || question.title.indexOf("####")!=-1){
				for(var i = 1 ; i <=question.subQuestionArray.length;i++){
					var inputContext = "<u>&nbsp;&nbsp;<b>"+(questionNum+i)+"</b>&nbsp;&nbsp;</u>";
					if( question.title.indexOf("##"+i+"##")!=-1){	//如果是完型填空题
						question.title = question.title.replace("##"+i+"##", inputContext);
					}else if(question.title.indexOf("####")!=-1){
						question.title = question.title.replace("####", inputContext);
					}
				}
			}
			//var script = {};
			//question.title = question.title;
			if(question.title&&question.resourcePath){
				$('#answerType_script').tmpl(question).appendTo('#div_look_part_');
			}else{
				$('#answerType_title').tmpl(question).appendTo('#div_look_part_');
			}
			for(var x in question.subQuestionArray ){
				if(!question.subQuestionArray){	continue;}
				childQuestion = question.subQuestionArray[x];
				questionType = question.questionType;
				answerType = questionType.answerType;
				questionId = childQuestion.id;
				var checkPaperView = checkPaperViewAllMap[questionId];
				if(!checkPaperView){
					checkPaperView={};
					checkPaperView.score = 0;
					checkPaperView.answer = null;
				}
				countStudentScore+=checkPaperView.score;//countStandardScore+=checkPaperView.standardScore;
				getSimpleQuestionHtml(checkPaperView ,childQuestion , question ,answerType);
				//角色扮演循环一次就结束---因为题型特殊在，没个句子就是一个子题，循环会把一个角色扮演分开成多个
				if("282153bec60d468cb6abb3886cef6cf8"==question.questionType.id){
					break;
				}
			}
		}else{
			getSimpleQuestionHtml( null , question , null , answerType);
		}
	}	
}


function getSimpleQuestionHtml(checkPaperView , question , parentQuestion, answerType){
	if("3"!=answerType||examTypeCode==1){
		questionNum++;
		question.questionNum = questionNum;
	}
	
	if(question.attribute && question.attribute.length>0){
		for(var i = 0 ; i<question.attribute.length ; i++){
			if(question.attribute[i].code=="comment"){
				question.attributeValue = question.attribute[i].value;
			}
		}
	}
	if ("0"==answerType || "1"==answerType || "9"==answerType) {
		getAnswerOptionArray0(question);
	
		if(isNaN(parseInt(question.answer))){
			question.answer = letter[parseInt(question.answer)];
			//question.title = delHtmlTag(question.title);
			question.title = question.title.replace(/&nbsp;/ig, " "); 
			$('#answerType0_1').tmpl(question).appendTo('#div_look_part_');
		}
		
	}else if("3"==answerType){
		if(parentQuestion!=null) {
			$('#answerType3_1').tmpl(question).appendTo('#div_look_part_');
		} else {
			$('#answerType3_1').tmpl(question).appendTo('#div_look_part_');
		}
		question=null;
	}else if(answerType=="2"){
		var temp_groupCount1 = question.answerOptionArray.toString().split("<input").length-1;
		temp_groupCount = temp_groupCount1;
		if(temp_groupCount1 == 0){
			temp_groupCount1 = question.answerOptionArray.toString().split("<INPUT").length-1;
		}
		if (temp_groupCount1 == 1) {
			getOptionArray2_1(question);
			$('#answerType2_1').tmpl(question).appendTo('#div_look_part_');
		}else {
			if(parentQuestion&&parentQuestion.answerOptionArray){
				question.answerOptionArray = parentQuestion.answerOptionArray.toString().replace(/<table/g , "<table style='width:100%;'");
			}
			var answerArr=question.answer.split("@!@") , completion_input_text=null;
			for(var i = 0 ; i < answerArr.length ;i++){
				completion_input_text = "<u>&nbsp;&nbsp;<b>("+(questionNum+i)+")"+answerArr[i]+"</b>&nbsp;&nbsp;</u>";
				question.answerOptionArray = question.answerOptionArray.toString().replace(/<\/?input[^>]*>/,completion_input_text);
				question.answerOptionArray = question.answerOptionArray.toString().replace(/<\/?INPUT[^>]*>/,completion_input_text);
			}
			//var script = {};
			question.title = question.answerOptionArray.toString();
			if(question.title&&question.resourcePath){
				$('#answerType_script').tmpl(question).appendTo('#div_look_part_');
			}else{
				$('#answerType_title').tmpl(question).appendTo('#div_look_part_');
			}
			//选词填空
			var buildBankFlag = false, aitems=[];
			if(question.attribute&&question.attribute.length>0) {
				var qas = question.attribute;
				for(var i = 0 ; i<qas.length ; i++) {
					if("bank"==qas[i].code) {
						buildBankFlag=true;
						aitems = buildBank(qas[i]);
						break;
					}
				}
			}
			if(buildBankFlag){
				getBankOptionArray2(question , aitems);
			}else{
				//听写填空判断答案是否正确
				/*getAnswerOptionArray2(question );*/
			}
			questionNum+=(answerArr.length-1);
		}
	}else if(examTypeCode){
		//是新角色扮演题型
		if( parentQuestion && parentQuestion.questionType && "282153bec60d468cb6abb3886cef6cf8"==parentQuestion.questionType.id){

//			var juesebanyanAnswerMap = getJuesebanyanAnswerMap(parentQuestion.id);//获取角色扮演，学生答案和子题ID对应的MAP
//			角色扮演循环一次就结束---因为题型特殊在，没个句子就是一个子题，循环会把一个角色扮演分开成多个
			var assignRoles = "" , data_juesebanyanArray = new Array();//系统角色
			if(parentQuestion.attribute && parentQuestion.attribute.length>0 ){
				for(var i = 0 ; i< parentQuestion.attribute.length ; i++){
					if(parentQuestion.attribute[i].code=="assignRoles"){
						assignRoles = parentQuestion.attribute[i].value;
						break;
					}
					/*if(parentQuestion.attribute[i].code=="comment"){
						data_juesebanyan.attributeValue = parentQuestion.attribute[i].value;
					}*/
				}
			}
			var playList = new Array();//播放列表
			//循环角色扮演子题
			for(var i = 0 ;i < parentQuestion.subQuestionArray.length ; i++){
				var subQuestion = parentQuestion.subQuestionArray[i];
				var data_juesebanyan = {};
				var isMe = false;
				if(subQuestion.attribute && subQuestion.attribute.length>0 ){
					for(var j = 0 ; j< subQuestion.attribute.length ; j++){
						if(subQuestion.attribute[j].code=="role"){
							if(subQuestion.attribute[j].value == assignRoles){
								isMe = true;
							}
							data_juesebanyan.role = subQuestion.attribute[j].value; //话术角色
						}
						break;
					}
				}
				data_juesebanyan.isMe = isMe; //是不是我
				data_juesebanyan.index = i;
				data_juesebanyan.assignRoles = assignRoles;
				data_juesebanyan.lastFlag = (parentQuestion.subQuestionArray.length == i+1)?true:false;
				data_juesebanyan.title = subQuestion.title;
				data_juesebanyan.resourcePath = subQuestion.resourcePath;
//				data_juesebanyan.answer = juesebanyanAnswerMap[subQuestion.id];
				data_juesebanyan.questionId = subQuestion.id;
				data_juesebanyanArray.push(data_juesebanyan);
				
				data_juesebanyan = {};
				data_juesebanyan.title = subQuestion.resourcePath;
				data_juesebanyan.mp3 = window.staticURL + "upload/media/" + subQuestion.resourcePath; // + "beep.mp3";//subQuestion.resourcePath;
				playList.push(data_juesebanyan);
			}
			
//			console.log(JSON.stringify(playList));
			var resource = {};
			resource.jqid = "jquery_jplayer_"+parentQuestion.id;
			resource.jqconid = "jp_container_"+parentQuestion.id;
			$('#answerType_resource_new').tmpl(resource).appendTo('#div_look_part_');
			
			var myPlaylist = new jPlayerPlaylist({
                jPlayer: "#jquery_jplayer_" + parentQuestion.id,
                cssSelectorAncestor: "#jp_container_" + parentQuestion.id
			}, [
	        ], {
	           playlistOptions: {
	               enableRemoveControls: true
	           },
	           swfPath: "Scripts",
	           supplied: "mp3",
	           wmode: "window"
			});
			
			for(var i = 0 ; i < playList.length ; i++ ){
				myPlaylist.add({
					 title: playList.title,
				   //artist: mArtist,
					 free: true,
					 mp3: playList[i].mp3
//					 mp3: "http://test.fifedu.com/static/itest/upload/media/20160222/538964105844148.mp3"
				});
			}
			
			$('#div_look_part_').append('<div class="view_role"><ul id="look_part_role_1"></ul></div>');
			$('#div_look_part_').append('</ul></div>');
			$('#spokenType_juesebanyan').tmpl(data_juesebanyanArray).appendTo('#look_part_role_1');
			$('#div_look_part_').append('<div class="mt20">学生角色：<b>'+assignRoles+'</b></div>');
		}else{
			question.title = delHtmlTag(question.title);//question.title.replace(/\|/g , "");
			question.attributeValue = null;
			if(question.attribute && question.attribute.length>0){
				question.attributeValue = question.attribute[0].value;
			}
			getSpokenArray_1(question);
			$('#spokenType_1').tmpl(question).appendTo('#div_look_part_');
		}
	}
}
function getSpokenArray_1(question , checkPaperView){
		question.isRight = 1;
	}

function getAnswerOptionArray0(question ){
	var answerOptionArray = question.answerOptionArray;
	if(!answerOptionArray)return;
	var referenceAnwer = parseInt(question.answer) ;
	for(var i = 0 ; i < answerOptionArray.length ; i++){
		if(i==referenceAnwer){
			answerOptionArray[i] = "<dd class=\"true\">"+letter[i]+") "+answerOptionArray[i] +"</dd>";
		}else{
			answerOptionArray[i] = "<dd>"+letter[i]+") "+answerOptionArray[i] +"</dd>";
		}
	}
	question.answerOptionArray = answerOptionArray;
}

function getBankOptionArray2(question , checkPaperView , aitems){
	if(!question.analyse)question.analyse="";
	var analyse = question.analyse.replace(/\d{1,2}\.【解析】([A-Z][\s]*[.|)|）]。*)+/g,"@&@");
	if(analyse.indexOf("@&@")==-1){
		analyse="@&@"+analyse;
	}
	analyse=analyse.split("@&@");
	var answerOptionArray = new Array();
	var referenceAnwer=checkPaperView.referenceAnwer , answer=checkPaperView.answer;
	referenceAnwer = referenceAnwer.split("@!@");
	answer=answer.split("@!@");
	for(var i = 0 ; i < referenceAnwer.length ;i++){
		var index1 = trace(aitems , referenceAnwer[i]);
		var index2 = trace(aitems , answer[i]);
		var arr = {};
		arr.answer= letter[index1] + ") " + referenceAnwer[i];
		arr.questionNum=questionNum+i;
		if(analyse[i+1]){
			arr.analyse = analyse[i+1];
		}
		if(null==answer[i]||""==answer[i]||"未作答"==answer[i]){
			arr.studentAnwer="未作答";
			//arr.isRight = 3;
		}else if(caseInsensitive&&(referenceAnwer[i].toLocaleLowerCase())==($.trim(answer[i]).toLocaleLowerCase())){
			arr.studentAnwer =letter[index2] + ") " +answer[i];
			//arr.isRight = 1;
		}else if(!caseInsensitive&&referenceAnwer[i]==$.trim(answer[i])){
			arr.studentAnwer =letter[index2] + ") " +answer[i];
			//arr.isRight = 1;
		}else{
			arr.studentAnwer =letter[index2] + ") "+ answer[i];
			//arr.isRight = 2;
		}
		answerOptionArray.push(arr);
	}
	$('#option_answerType_2_Bank').tmpl(answerOptionArray).appendTo('#div_look_part_');
}


function getOptionArray2_1(question , checkPaperView){
	var completion_input_text = "<u>&nbsp;&nbsp;<b>"+question.answer+"</b>&nbsp;&nbsp;</u>";
	question.answerOptionArray = question.answerOptionArray.toString().replace(/<\/?input[^>]*>/g,completion_input_text);
	question.answerOptionArray = question.answerOptionArray.toString().replace(/<\/?INPUT[^>]*>/g,completion_input_text);

}

function playerResourceStop(thisObj){//试题内容停止播放音频
	
		thePlayer = jwplayer('myplayer').setup({
			flashplayer: window.staticURL+"/toeflielts/content/js/audioplay/player.swf",
			file: streamurl ,
			controlbar:"none",
			width: "1px",
			height: "1px",
	        events: {
	        	onPlay: function (){},
	        	onPause: function (){finished(obj);thePlayer=null;},
	        	onComplete:function() {finished(obj);thePlayer=null; }
	        }
		});
		var msg=null,state=null,obj = null;
		obj = $(".playing");
			state="PLAYING";
		switch(state){
			case 'PLAYING':
				msg='Playing';
				thePlayer.stop();
				obj.siblings(".start").show();
		        obj.hide();
		        thePlayer=null;
		        msg=null;
				
				break;
			
		}
	
}


var _playerState = 0;
var playerObj =null , objp=null ;
function spxPlayer(thisObj){
	playerResourceStop("playing");
	if((streamurl!=$(thisObj).attr("resource") && (_playerState==1))||null!=thePlayer ){
		
		return playerObj;
	}else{
		
	var obj = $(thisObj);
	objp = obj;
	this.finished = function(obj2){
		playerObj =null;
	};
	this.finishedPlaying = function(obj2){
        playerObj =null;
	};
	this.playing = function(obj2){
	};
	streamurl = obj.attr("resource");
	if(0==_playerState&&null!=streamurl&&""!=streamurl){
		_playerState = 1;
			_recorder.loadAudio(streamurl, true);
		this.playing(obj);
	}else if(_playerState==1){
		_recorder.stopAudio();
		_playerState =0;
		this.finishedPlaying(obj);
	}
		playerObj = this;
	}
	 
	return playerObj;
}

function spxPlayer2(thisObj){
	if((streamurl!=$(thisObj).attr("resource") && (_playerState==1))||null!=thePlayer ){
		return playerObj;
	}else{
		
	var obj = $(thisObj);
	objp = obj;
	this.finished = function(obj2){
		obj2.siblings(".playing").hide();
		obj2.show();
		playerObj =null;
	};
	this.finishedPlaying = function(obj2){
		obj2.siblings(".start").show();
        obj2.hide();
        playerObj =null;
	};
	this.playing = function(obj2){
		obj2.hide();
		obj2.siblings(".playing").show();
	};
	streamurl = obj.attr("resource");
	if(0==_playerState&&null!=streamurl&&""!=streamurl){
		_playerState = 1;
		obj.siblings(".playing").attr("onclick","javascript:return;");
		_recorder.loadAudio(streamurl, true);
		this.playing(obj);
	}else if(_playerState==1){
		_recorder.stopAudio();
		_playerState =0;
		this.finishedPlaying(obj);
	}
		playerObj = this;
	}
	return playerObj;
}

function onPlayState(e){
	if (e.newstate === "PLAYING" && e.oldstate !== "PLAYING") {
//        LogUtil.info("Play Begin");
        _playerState = 1;
        objp.siblings(".playing").attr("onclick","spxPlayer(this);");
    } else if (e.newstate === "PAUSE" && e.oldstate === "PLAYING") {
//        LogUtil.info("Play Pause");
    } else if (e.newstate === "IDLE" && e.oldstate !== "IDLE") {
    	if("playing"!=objp.attr("class")){
	    	playerObj.finished(objp);
	        _playerState = 0;
	        playerObj =null;
    	}
//        LogUtil.info("Play Stop");
    }
};

var _recorder = null;
function loadSpxPlay(){
	//console.log(window.staticURL);
	if(window.staticURL){
		SpeechRecorderSettings.Root = window.staticURL + "toeflielts/content/js/speechRecorder/";
	}
	var swfUrl = SpeechRecorderSettings.Root + "SpeechRecorderIat.swf";
    if (SpeechRecorderSettings.IsDebug === true) {
        swfUrl += "?r=" + Math.random();
    }
    var recordId = "recorder-panel";
    var recordOptios = {
        swf: swfUrl,
        width: "100%",
        height: "100%",
        expressInstall: SpeechRecorderSettings.Root + "expressInstall.swf",
        flashVars: {
            skin: SpeechRecorderSettings.Root + "Skins/sound.swf",
            recorderId: recordId,
            enableWebLog: false,
            mspServer:"gz.voicecloud.cn",
            logLvl: 0,
            mscLogLvl:-1
        }
    };
    _recorder = new SpeechRecorder(recordId, recordOptios);
    _recorder.addEventListener(SpeechRecorderEvent.PLAY_STATE, onPlayState);
    return _recorder;
}

var thePlayer=null ,streamurl=null;
function playerResource(thisObj){//resource
	this.finished = function(obj){
		obj.siblings(".playing").hide();
        obj.show();
        thePlayer=null;
        msg=null;
	};
	this.finishedPlaying = function(obj){
		obj.siblings(".start").show();
        obj.hide();
        thePlayer=null;
        msg=null;
	};
	this.playing = function(obj){
		obj.hide();
	    obj.siblings(".playing").show();
	};
	stop();
	var msg=null,state=null,obj = null;
	obj = $(thisObj);
	if(null!=thePlayer || _playerState==1){
		if((streamurl!=obj.attr("resource"))){
			return;
		}
		state=thePlayer.getState();
		
		switch(state){
			case 'BUFFRING':
				msg='Buffering';
				break;
			case 'PLAYING':
				msg='Playing';
				thePlayer.stop();
				this.finishedPlaying(obj);
				thePlayer=null;
				break;
			case 'PAUSED':
				msg='Pause';
				break;
			case 'IDLE':
				msg='Stop';
				break;
		}
	}else{
		streamurl = obj.attr("resource");
		thePlayer = jwplayer('myplayer').setup({
			flashplayer: window.staticURL+"/toeflielts/content/js/audioplay/player.swf",
			file: streamurl ,
			controlbar:"none",
			width: "1px",
			height: "1px",
	        events: {
	        	onPlay: function (){},
	        	onPause: function (){finished(obj);thePlayer=null;},
	        	onComplete:function() {finished(obj);thePlayer=null; }
	        }
		});
		
		thePlayer.play();
		this.playing(obj);
	}
	
	
	function stop(){//学生答案停止播放音频
		var obj = $(".playing");
		var streamurl = obj.attr("resource");
		_recorder.loadAudio(streamurl, true);
		
			_recorder.stopAudio();
			_playerState =0;
			
		
	}
	
	
}

function delHtmlTag(str){	if(str){		str = str.replace(/\|/g , "");		str = $.trim(str);		str = str.replace(/&nbsp;/g,"");		str = str.replace(/<[^>]+>/g,"");	}	return str;};

/**
 * 获取角色扮演，学生答案和子题ID对应的MAP
 * @param questionId
 * @returns {___anonymous31889_31890}
 */
function getJuesebanyanAnswerMap(answer){
	var juesebanyanAnswerMap = {};
	if(!answer){
		return juesebanyanAnswerMap;
	}
	var answerArray = answer.split(";");
	//55b98b5e18d94576b5cd3380b65ffff4/10152054/B_0_ab460b9d34a443c8bb8f5694093351a4_61ea1dd95a7d47b6819312b7986c3479.spx;55b98b5e18d94576b5cd3380b65ffff4/10152054/B_1_ab460b9d34a443c8bb8f5694093351a4_bb154c32a7fc4f9fb4f82c891fd11b65.spx;
	for(var i = 0 ; i<answerArray.length ; i++){
		var value = answerArray[i];
		if(!value || value.length == 0 ){
			continue;
		}
		var key = value.replace(/.spx/g , "").substring( value.lastIndexOf("_")+1 );
		juesebanyanAnswerMap[key] = value;
	}
	return juesebanyanAnswerMap;
}

/*
 * 生成角色扮演展示数据
 */
function createData_role(question , answer){
	var data_juesebanyanArray = new Array();
	var assignRoles = "";
	var juesebanyanAnswerMap = getJuesebanyanAnswerMap(answer);//获取角色扮演，学生答案和子题ID对应的MAP
	
	//找出选中角色
	if(question.attribute && question.attribute.length>0 ){
		for(var i = 0 ; i< question.attribute.length ; i++){
			if(question.attribute[i].code=="assignRoles"){
				assignRoles = question.attribute[i].value;
				break;
			}
		}
	}
	//循环角色扮演子题
	for(var i = 0 ;i < question.subQuestionArray.length ; i++){
		var subQuestion = question.subQuestionArray[i];
		var data_juesebanyan = {};
		var isMe = false;
		if(subQuestion.attribute && subQuestion.attribute.length>0 ){
			for(var j = 0 ; j< subQuestion.attribute.length ; j++){
				if(subQuestion.attribute[j].code=="role"){
					if(subQuestion.attribute[j].value == assignRoles){
						isMe = true;
					}
					data_juesebanyan.role = subQuestion.attribute[j].value; //话术角色
				}
				break;
			}
		}
		data_juesebanyan.isMe = isMe; //是不是我
		data_juesebanyan.index = i;
		data_juesebanyan.lastFlag = (question.subQuestionArray.length == i+1)?true:false;
		data_juesebanyan.title = subQuestion.title;
		data_juesebanyan.resourcePath = subQuestion.resourcePath;
		data_juesebanyan.answer = juesebanyanAnswerMap[subQuestion.id];
		data_juesebanyan.questionId = subQuestion.id;
		data_juesebanyanArray.push(data_juesebanyan);
	}
	return data_juesebanyanArray;
}

//自适阅卷
function winAll_yuejuan(){
    winWidth = $(window).width();
    winHeight = $(window).height();
    //阅卷高度统一 评分垂直居中显示
    $(".yuejuan-pingfen").each(function(){
        $(this).css({"padding-top":($(this).prev().height()+8-22-54)/2})
    });
    $(".yuejuan_box_more").each(function(){
        var thisHeight = 0;
        for(i=0;i<$(this).children("div").length;i++){
            if(thisHeight < $(this).children("div").eq(i).height())
                thisHeight = $(this).children("div").eq(i).height();
        };
        for(i=0;i<$(this).children("div").length;i++){
            $(this).children("div").eq(i).css({"padding-top":thisHeight/2-$(this).children("div").eq(i).height()/2+2,"padding-bottom":thisHeight/2-$(this).children("div").eq(i).height()/2+2});
        };
    });
};
