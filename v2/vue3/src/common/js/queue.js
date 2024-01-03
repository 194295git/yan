// 定义队列类
export default class Queue {
  constructor() {
    this._elementData = [];
  }

  // 添加元素
  offer(item) {
    this._elementData[this._elementData.length] = item;
  }

  // 返回第一个元素，并在队列中删除
  poll() {
    if (this._elementData) {
      return this._elementData.splice(0, 1);
    } else {
      return null;
    }
  }

  // 返回第一个元素
  peek() {
    if (this._elementData) {
      return this._elementData[0];
    } else {
      return null;
    }
  }

  // 获取元素个数
  size() {
    return this._elementData.length;
  }

  // 获取数据
  getData() {
    return this._elementData;
  }

  // 清空
  clear() {
    this._elementData = [];
  }
}
