export default class SocketService {
  static instance = null;
  static get Instance() {
    if (!this.instance) {
      this.instance = new SocketService();
    }
    return this.instance;
  }
  // 和服务端连接的socket对象
  ws = null;
  // 存储回调函数
  callBackMapping = {};
  // 标识是否连接成功
  connected = false;
  // 记录重试的次数
  sendRetryCount = 0;
  // 重新连接尝试的次数
  connectRetryCount = 0;
  //  定义连接服务器的方法
  connect(token) {
    // 连接服务器
    if (!window.WebSocket) {
      return console.log('您的浏览器不支持WebSocket');
    }
    // let token = $.cookie('123');
    // let token = '4E6EF539AAF119D82AC4C2BC84FBA21F';
    let url = 'ws://127.0.1:88/websocket?token='+token;
    this.ws = new WebSocket(url);
    // 连接成功的事件
    this.ws.onopen = () => {
      console.log('【IM日志】连接服务端成功了');
      this.connected = true;
      // 重置重新连接的次数
      //连接成功并且返回正确结果 才能重置连接次数
      // this.connectRetryCount = 0;
      console.log( "onopen",this.connectRetryCount )
    };
    // 1.连接服务端失败
    // 2.当连接成功之后, 服务器关闭的情况
    this.ws.onclose = () => {
      console.log('【IM日志】连接服务端失败');
      this.connected = false;
      this.connectRetryCount++;
      console.log( "onclose",this.connectRetryCount )
      if( this.connectRetryCount ==5 ){
        return;
      }
      setTimeout(() => {
        this.connect();
      }, 500 * this.connectRetryCount);
    };
    // 得到服务端发送过来的数据
    // this.ws.onmessage = msg => {
    //   // console.log(msg.data, '从服务端获取到了数据');
    // };
  }
  // 回调函数的注册
  registerCallBack(socketType, callBack) {
    this.callBackMapping[socketType] = callBack;
  }
  // 取消某一个回调函数
  unRegisterCallBack(socketType) {
    this.callBackMapping[socketType] = null;
  }
  // 发送数据的方法
  send(data) {
    // 判断此时此刻有没有连接成功
    if (this.connected) {
      this.sendRetryCount = 0;
      try {
        this.ws.send(JSON.stringify(data));
      } catch (e) {
        this.ws.send(data);
      }
    } else {
      this.sendRetryCount++;
      setTimeout(() => {
        this.send(data);
      }, this.sendRetryCount * 500);
    }
  }
}

