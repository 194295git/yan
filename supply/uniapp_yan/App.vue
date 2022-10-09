<script>
	import http from 'supply/uniapp_yan/common/http.js'
	
	export default {
		
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	@import "uview-ui/index.scss";
	
	@import "common/vuetify";
	/*每个页面公共css */
	
	/**app.wxss**/
	
	.container {
	  height: 100%;
	  display: flex;
	  flex-direction: column;
	  align-items: center;
	  justify-content: space-between;
	  /*padding: 200rpx 0;*/
	  box-sizing: border-box;
	  background: #F0F4F3;
	  /* background: green;
	  z-index: 8; */
	}
	
	.container.withtab{
	  margin: 105rpx 0 0 0;
	  /*top: 105rpx;*/
	}
	.flex-wrp{
	  display: flex;
	}
	.flex-tab{
	  flex-flow: row nowrap;
	  justify-content: space-around;
	  align-items: stretch;
	}
	.flex-item{
	  flex-grow: 1;
	  text-align: center;
	}
	.top-tab{
	  width: 750rpx;
	  height: 100rpx;
	  color: white;
	  font-size: 28rpx;
	  line-height: 100rpx;
	  margin: 0 0 8rpx 0;
	  position: fixed;
	  top: 0;
	  z-index: 9999;
	}
	.toptab.active{
	  color: #ffffff;
	  border-bottom: solid 1rpx #ffffff;
	}

	
	
	
	.sr-only {
	  position: absolute;
	  width: 1px;
	  height: 1px;
	  padding: 0;
	  margin: -1px;
	  overflow: hidden;
	  clip: rect(0, 0, 0, 0);
	  border: 0;
	}
	.sr-only-focusable:active,
	.sr-only-focusable:focus {
	  position: static;
	  width: auto;
	  height: auto;
	  margin: 0;
	  overflow: visible;
	  clip: auto;
	}
	
	/* .container {
	  display: flex;
	  flex-direction: column;
	  align-items: center;
	  box-sizing: border-box;
	}  */
	
	button {
	  background: initial;
	}
	
	button:focus{
	  outline: 0;
	}
	
	button::after{
	  border: none;
	}
	
	
	page {
	  background: #f6f6f6;
	  display: flex;
	  flex-direction: column;
	  justify-content: flex-start;
	}
	
	.userinfo, .uploader, .tunnel {
	  margin-top: 40rpx;
	  height: 140rpx;
	  width: 100%;
	  background: #fff;
	  border: 1px solid rgba(0, 0, 0, 0.1);
	  border-left: none;
	  border-right: none;
	  display: flex;
	  flex-direction: row;
	  align-items: center;
	  transition: all 300ms ease;
	}
	
	.userinfo-avatar {
	  width: 100rpx;
	  height: 100rpx;
	  margin: 20rpx;
	  border-radius: 50%;
	  background-size: cover;
	  background-color: white;
	}
	
	.userinfo-avatar:after {
	  border: none;
	}
	
	.userinfo-nickname {
	  font-size: 32rpx;
	  color: #007aff;
	  background-color: white;
	  background-size: cover;
	}
	
	.userinfo-nickname::after {
	  border: none;
	}
	
	.tunnel {
	  padding: 0 0 0 40rpx;
	}
	
	.tunnel-text {
	  position: relative;
	  color: #222;
	  display: flex;
	  flex-direction: row;
	  align-content: center;
	  justify-content: space-between;
	  box-sizing: border-box;
	  border-top: 1px solid rgba(0, 0, 0, 0.1);
	}
	
	.tunnel-text:first-child {
	  border-top: none;
	}
	
	.tunnel-switch {
	  position: absolute;
	  right: 20rpx;
	  top: -2rpx;
	}
	
	.disable {
	  color: #888;
	}
	
	.service {
	  position: fixed;
	  right: 40rpx;
	  bottom: 40rpx;
	  width: 140rpx;
	  height: 140rpx;
	  border-radius: 50%;
	  background: linear-gradient(#007aff, #0063ce);
	  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
	  display: flex;
	  align-content: center;
	  justify-content: center;
	  transition: all 300ms ease;
	}
	
	.service-button {
	  position: absolute;
	  top: 40rpx;
	}
	
	.service:active {
	  box-shadow: none;
	}
	
	.request-text {
	  padding: 20rpx 0;
	  font-size: 24rpx;
	  line-height: 36rpx;
	  word-break: break-all;
	}

</style>
