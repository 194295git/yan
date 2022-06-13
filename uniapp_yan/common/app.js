const globalData = {
	// 'url': 'https://www.yanzhan.xyz/'
	// 'url': 'http://39.106.77.15:8082/'
	'url': 'http://127.0.1:8999/',
	'ws':'ws://127.0.1:8082/',
	'nettyws':'ws://127.0.1:8888/',
	'action':'http://127.0.1:8082//eduoss/fileoss'
};  
const now = Date.now || function () {  
    return new Date().getTime();  
};  
const isArray = Array.isArray || function (obj) {  
    return obj instanceof Array;  
};  

export default {  
    globalData,  
    now,  
    isArray  
}