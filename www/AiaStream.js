var exec = require('cordova/exec');

var AiaStream = function() {

}

AiaStream.prototype.init = function() {
    exec(null, null, "AiaStream", "init", []);
};

AiaStream.prototype.startCameraPreview = function(configs) {
    return new Promise((resolve, reject) => {
        exec(function(data) {
            resolve(data);
        }, function(error) {
            reject(error);
        }, "AiaStream", "startCameraPreview", [configs]);
    });
};

AiaStream.prototype.stopCameraPreview = function(options) {
    return new Promise((resolve, reject) => {
        exec(function() { resolve(); }, function() { reject(); }, "AiaStream", "stopCameraPreview", []);
    });
};

AiaStream.prototype.startBroadcast = function(configs) {
    return new Promise((resolve, reject) => {
        exec(function(data) {
            resolve(data);
        }, function(error) {
            reject(error);
        }, "AiaStream", "startBroadcast", [configs]);
    });
};

AiaStream.prototype.stopBroadcast = function(configs) {
    return new Promise((resolve, reject) => {
        exec(function(data) {
            resolve(data);
        }, function(error) {
            reject(error);
        }, "AiaStream", "stopBroadcast", [configs]);
    });
};

AiaStream.prototype.switchCamera = function(options) {
    options = options || {};
    exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "switchCamera", []);
};

AiaStream.prototype.setVideoQuality = function(options) {
    options = options || {};
    exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "setVideoQuality", [options]);
};

AiaStream.prototype.setBroadcastConfig = function(options) {
    options = options || {};
    exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "setBroadcastConfig", [options]);
};

AiaStream.prototype.setAudioEnable = function(options) {
    options = options || {};
    exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "setAudioEnable", [options]);
};

AiaStream.prototype.isAudioEnable = function(options) {
    options = options || {};
    exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "isAudioEnable", [options]);
};

AiaStream.prototype.isBroadcasting = function(options) {
    options = options || {};
    exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "isBroadcasting", [options]);
};

AiaStream.prototype.getCameraDirection = function(options) {
    options = options || {};
    exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "getCameraDirection", [options]);
};


AiaStream.prototype.initVideoPlayer = function() {
    return new Promise((resolve, reject) => {
        exec(() => { resolve(); }, () => { reject(); }, "AiaStream", "initVideoPlayer", []);
    });
};

AiaStream.prototype.stopVideoPlayer = function() {
    return new Promise((resolve, reject) => {
        exec(() => { resolve(); }, () => { reject(); }, "AiaStream", "stopVideoPlayer", []);
    });
};

AiaStream.prototype.reloadVideo = function() {
    return new Promise((resolve, reject) => {
        exec(() => { resolve(); }, () => { reject(); }, "AiaStream", "reloadVideo", []);
    });
};

AiaStream.prototype.playVideo = function(url) {
    return new Promise((resolve, reject) => {
        exec(() => { resolve(); }, () => { reject(); }, "AiaStream", "playVideo", [{
            url: url
        }]);
    });
};

var aiaStream = new AiaStream();

if (!window.plugins) {
    window.plugins = {};
}
window.plugins.aiaStream = aiaStream;

module.exports = aiaStream;