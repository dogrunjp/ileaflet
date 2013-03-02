var epub = {
	gblDataDir : "",
	zip : null,
	pageList : new Array(),
	dataList : {},
	
	init : function (url,callback,error) {
		$.ajax({
			url: url,
			dataType: 'zip',
			success: function(data){
				epub.zip = data;
				if ( !epub.createEpubData() ) {
					error();
				} else {
					callback();
				}
			}
		});
	},

	// 目次がクリックされたら対応するページを表示
	getHTML : function(id) {

   		var text  = epub.getData(epub.dataList[id]);
   		var xhtml = Utf8.decode(text);
   		var dom   = epub.changeDom(xhtml);

   		// 画像の展開
   		var imgTags = dom.getElementsByTagName("img");
   		for ( var cnt = 0; cnt < imgTags.length; ++cnt ) {
	   		var imgTag = imgTags[cnt];
	   		var imgFileName = imgTag.getAttribute("src");

	   		var imgData = epub.getImage(imgFileName);
	   		imgTag.src = imgData;
   		}

   		// cssの読み込み
   		var linkTags = dom.getElementsByTagName("link");
   		for ( var cnt = 0; cnt < linkTags.length; ++cnt ) {
	   		var linkTag = linkTags[cnt];
	   		var cssFileName = linkTag.getAttribute("href");
	   		var cssFileData = epub.getData(cssFileName);
	
	   		// 現在のHTMLに適用
	   		var styleTag = document.createElement("style");
	   		var cssData = document.createTextNode(cssFileData);
	   		styleTag.appendChild(cssData);
	   		var parent = linkTag.parentElement;
	   		parent.appendChild(styleTag);

   		}
   		//リンクタグの削除
   		for ( var cnt = linkTags.length; cnt > 0; --cnt ) {
	   		var linkTag = linkTags[cnt-1];
	   		// リンクタグは削除
	   		linkTag.parentElement.removeChild(linkTag);
   		}
   		
   		var scriptTags = dom.getElementsByTagName("script");
   		for ( var cnt = 0; cnt < scriptTags.length; ++cnt ) {
	   		var scriptTag = scriptTags[cnt];
	   		var scriptFileName = scriptTag.getAttribute("src");
	   		if ( scriptFileName == null ) continue;

	   		scriptTag.setAttribute("src","");
	   		var scriptFileData = epub.getData(scriptFileName);
	   		
	   		var scriptData = document.createTextNode(scriptFileData);

	   		scriptTag.appendChild(scriptData);
   		}
   		
   		
   		
   		

   		var serializer = new XMLSerializer();
   		var newXhtml = serializer.serializeToString(dom);
   		return newXhtml
	}, 

	//展開したデータをファイル名から取得
	getData :function (fileName) {
		return epub.zip.files[epub.gblDataDir+fileName].inflate();
	},

	trim : function (string){
		return string.replace(/^[ 　]*/gim, "").replace(/[ 　]*$/gim, "").replace(/[\n]*$/gim, "").replace(/[\r\n]*$/gim, "");
	},

	createDom : function (fileName) {
		var data = epub.zip.files[epub.gblDataDir + fileName].inflate();
		return epub.changeDom(data);
	},

	changeDom : function (data) {
		var xmldom = new DOMParser();
    	var dom = xmldom.parseFromString( data, "application/xml" );
		return dom;
	},

	/**
 	 * 画像の作成
 	 * @param mime
 	 * @param fileName
 	 * @returns {String}
 	 */
 	getImage: function (fileName) {

 		var ext = "";
    	var index = fileName.lastIndexOf(".");
    	if ( index != -1 ) {
    		ext = fileName.substring(index + 1);
    	}
	
		var mime = "";
		if ( ext == "gif" ) {
			mime = "image/gif";
		} else if ( ext == "jpg" || ext == "jpeg" ) {
			mime = "image/jpeg";
		} else if ( ext == "png" ) {
			mime = "image/png";
		} else if ( ext == "svg" ) {
			mime = "image/svg-xml";
		} else {
			console.log(fileName);
		}
		
  		var fileData = epub.getData(fileName);

  		var prefix = "data:" + mime + ";base64,";
  		var enc = base64["encode"]( fileData , false);
  		return prefix + enc;
	},

	createEpubData : function () {

		var type = epub.zip.files["mimetype"];
    	if ( type === undefined ) {
    		return false;
    	}

    	var val = type.inflate();
    	val = epub.trim(val);
    	if ( val != "application/epub+zip" ) {
    		return false;
    	}

    	//container.xmlの解析
    	var containerXml = epub.zip.files["META-INF/container.xml"];
    	if ( containerXml === undefined ) {
  	  		return false;
    	}

    	var data = containerXml.inflate();
    	var dom = epub.changeDom(data);
	
    	var rootFileTags = dom.getElementsByTagName("rootfile");
    	if ( rootFileTags.length != 1 ) {
    		return false;
    	}
    	var rootFile = rootFileTags[0].getAttribute("full-path");
    	if ( rootFile === undefined ) {
  	  		return false;
    	}

    	var fileName = rootFile;
    	var index = rootFile.lastIndexOf("/");
   
    	//ディレクトリ指定があった場合
    	if ( index != -1 ) {
      		fileName = rootFile.substring(index + 1);
      		//指定するディレクトリを設定
      		epub.gblDataDir = rootFile.substring(0,index + 1);
    	}
    	//抜き出したOPFファイルを設定する
    	epub.analysisOPF(fileName);

    	return true;
	},

	/**
 	 * OPFファイルを解析して文字列データを作成する
 	 * @param fileName
 	 * @returns {Array}
 	 */
	analysisOPF : function (fileName){

  		//ファイル名からDomを作成 
  		var dom = epub.createDom(fileName);
  		// EPUB表示に必要なファイル項目を取得
  		var itemTag = dom.getElementsByTagName("manifest")[0].getElementsByTagName("item");
  		// 表示順を示すitemrefタグを取得
  		var indexItem = dom.getElementsByTagName("spine")[0].getElementsByTagName("itemref");

  		// ページの表示順番を取得（IDからファイル名を取得）  
  		for(var i=0; i<indexItem.length; i++){
    		var ID = indexItem[i].getAttribute("idref");
    		for(var j=0; j<itemTag.length; j++){
      			var cID = itemTag[j].getAttribute("id");
      			if (ID == cID){
      				epub.pageList.push(ID);
      				epub.dataList[ID] = itemTag[j].getAttribute("href");
        			break; 
      			}
    		}
  		}
	}
}
