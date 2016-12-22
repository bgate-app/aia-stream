var exec = require('cordova/exec');

var AiaStream = function () {

}

AiaStream.prototype.init = function () {
	exec(null, null, "AiaStream", "init", []);
};

AiaStream.prototype.startCameraPreview = function () {
	return new Promise((resolve,reject)=>{
		exec(function(){resolve();}, function(){reject();}, "AiaStream", "startCameraPreview", []);
	});	
};

AiaStream.prototype.stopCameraPreview = function (options) {
	return new Promise((resolve,reject)=>{
		exec(function(){resolve();}, function(){reject();}, "AiaStream", "stopCameraPreview", []);
	});	
};

AiaStream.prototype.startBroadcast = function (options) {
	options = options || {};
	exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "startBroadcast", []);
};

AiaStream.prototype.stopBroadcast = function (options) {
	options = options || {};
	exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "stopBroadcast", []);
};

AiaStream.prototype.switchCamera = function (options) {
	options = options || {};
	exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "switchCamera", []);
};

AiaStream.prototype.setVideoQuality = function (options) {
	options = options || {};
	exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "setVideoQuality", [options]);
};

AiaStream.prototype.setBroadcastConfig = function (options) {
	options = options || {};
	exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "setBroadcastConfig", [options]);
};

AiaStream.prototype.setAudioEnable = function (options) {
	options = options || {};
	exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "setAudioEnable", [options]);
};

AiaStream.prototype.isAudioEnable = function (options) {
	options = options || {};
	exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "isAudioEnable", [options]);
};

AiaStream.prototype.isBroadcasting = function (options) {
	options = options || {};
	exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "isBroadcasting", [options]);
};

AiaStream.prototype.getCameraDirection = function (options) {
	options = options || {};
	exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "getCameraDirection", [options]);
};


var aiaStream = new AiaStream();

if (!window.plugins) {
	window.plugins = {};
}
window.plugins.aiaStream = aiaStream;

module.exports = aiaStream;
